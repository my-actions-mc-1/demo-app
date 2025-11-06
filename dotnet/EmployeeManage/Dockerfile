# Build stage
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src

# Copy csproj and restore dependencies
COPY ["EmployeeManage.csproj", "./"]
RUN dotnet restore

# Copy the rest of the code
COPY . .

# Build the application
RUN dotnet publish "EmployeeManage.csproj" -c Release -o /app/publish /p:UseAppHost=false --no-restore

# Runtime stage
FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS final
WORKDIR /app

# Copy the published files from build stage
COPY --from=build /app/publish .

# Configure the application
ENV ASPNETCORE_URLS=http://0.0.0.0:80
ENV ASPNETCORE_ENVIRONMENT=Development

# Expose port 80
EXPOSE 80

# Set the entry point
ENTRYPOINT ["dotnet", "EmployeeManage.dll"]