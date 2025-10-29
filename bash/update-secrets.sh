#!/bin/bash

export GH_TOKEN="" 

# Args
ORGANIZATIONS='["my-actions-mc-1","my-actions-mc-2"]'
VISIBILITY="${VISIBILITY:-all}"
APP="${APP:-actions}"
JSON_STRING='{"MY_SECRET_TEST_7":"your-secret-value-7","MY_SECRET_TEST_8":"your-secret-value-8"}'

echo "Starting secret update at $(date)"

function update_secret() {
    local ORGANIZATIONS=$1
    local APP=$2
    local VISIBILITY=$3
    local JSON_STRING=$4

    # Create tab-separated pairs and read them safely
    pairs=$(jq -r '
        to_entries[]
        | [.key, (.value|tojson)]     # tojson keeps types/escaping intact
        | @tsv                         # tab-separated
    ' <<< "$JSON_STRING")

    # Parse organisation array string
    mapfile -t orgs < <(jq -r '.[]' <<< "$ORGANIZATIONS")

    # Loop through secrets
    while IFS=$'\t' read -r SECRET_NAME value_json; do

        # Strip surrounding JSON quotes when value is a string
        SECRET_VALUE=$(jq -r '.' <<< "$value_json")

        #Pass secret via anonymous file descriptor (not visible in 'ps')
        exec 3<<<"$SECRET_VALUE"

        # Loop through organizations
        for ORG in "${orgs[@]}"; do
            gh secret set "$SECRET_NAME" \
                --org "$ORG" \
                --app "$APP" \
                --visibility "$VISIBILITY" <&3
        done

        # Close FD carrying the secret
        exec 3<&-
        
    done <<< "$pairs"
    
}

# Execute function
update_secret "$ORGANIZATIONS" "$APP" "$VISIBILITY" "$JSON_STRING"

echo "🔚 Script completed at $(date)"
