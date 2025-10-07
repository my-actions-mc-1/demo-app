using EmployeeManage.Models;
using Microsoft.EntityFrameworkCore;

namespace EmployeeManage.Data;

public class EMDBContext : DbContext
{
    public EMDBContext(DbContextOptions options) : base(options)
    {
    }

    public DbSet<Employee> Employee { get; set; }
}
