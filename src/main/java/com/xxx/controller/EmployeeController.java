package com.xxx.controller;

import com.xxx.dao.DepartmentDao;
import com.xxx.dao.EmployeeDao;
import com.xxx.pojo.Department;
import com.xxx.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

//员工管理
@Controller

public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //add页面
    @RequestMapping("/emp")
    public String toAddPage(Model model){
        //查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    //add提交页面
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("employee："+employee);
        //添加操作
        employeeDao.save(employee);//调用底层业务方法保存员工信息
        return "redirect:/emps";
    }
    //修改页面
    @RequestMapping("/toUpdate/{id}")
    public String toUpdateEmp(@PathVariable("id")Integer id, Model model){
        System.out.println("toUpdate："+id);
        //查询当前用户信息
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee",employee);
        //查出所有部门信息
        //查出所有部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }
    //修改提交
    @PostMapping("/updateUser")
    public String updateUser(Employee employee){
        System.out.println("updateUser："+employee);
        //修改操作
        employeeDao.save(employee);//调用底层业务方法保存员工信息
        return "redirect:/emps";
    }

    //删除
    @RequestMapping("/delemp/{id}")
    public String delEmp(@PathVariable("id")Integer id){
        System.out.println("delemp："+id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
