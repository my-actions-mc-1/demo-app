using EmployeeManage.Data;
using EmployeeManage.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Diagnostics;

namespace EmployeeManage.Controllers;


public class HomeController : Controller
{
    private readonly EMDBContext dbContext;

    public HomeController(EMDBContext dbContext)
    {
        this.dbContext = dbContext;
    }

    [HttpGet("/")]
    public IActionResult Index()
    {
        //var employees = dbContext.Employee.FromSql($"SELECT * FROM dbo.Employee").ToList();
        List<Employee> employees = dbContext.Employee.ToList();
        ViewBag.employees = employees;
        return View();
    }

    [HttpGet("/addemployee")]
    public IActionResult AddEmployee()
    {
        return View();
    }

    [HttpPost]
    public IActionResult SaveEmployee(Employee post)
    {
        dbContext.Add(post);
        dbContext.SaveChanges();
        return RedirectToAction("Index");
    }

    [HttpGet("/editemployee/{id}")]
    public IActionResult EditEmployee(Guid id)
    {
        var obj = dbContext.Employee.FirstOrDefault(x => x.Id == id);
        return View(obj);
    }

    [HttpPost]
    public IActionResult UpdateEmployee(Employee post)
    {
        var obj = dbContext.Employee.FirstOrDefault(x => x.Id == post.Id);
        if (obj != null)
        {
            obj.FirstName = post.FirstName;
            obj.LastName = post.LastName;
            obj.Email = post.Email;
            dbContext.SaveChanges();
        }
        return RedirectToAction("Index");
    }

    [HttpPost]
    public IActionResult DeleteEmployee(Guid id)
    {
        var obj = dbContext.Employee.FirstOrDefault(x => x.Id == id);
        if (obj != null)
        {
            dbContext.Remove(obj);
            dbContext.SaveChanges();
        }
        return RedirectToAction("Index");
    }

    [HttpGet]
    public IActionResult Privacy()
    {
        return View();
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}