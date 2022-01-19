package com.xxx.dao;

import com.xxx.pojo.Department;
import com.xxx.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //模拟员工数据
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private  DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "AA", "A1251694436@qq.com", 0, new DepartmentDao().getDepartmentById(101)));
        employees.put(1002, new Employee(1002, "BB", "B1251694445@qq.com", 0, new DepartmentDao().getDepartmentById(102)));
        employees.put(1003, new Employee(1003, "CC", "C1251694478@qq.com", 0, new DepartmentDao().getDepartmentById(103)));
        employees.put(1004, new Employee(1004, "DD", "D12516944752@qq.com", 0, new DepartmentDao().getDepartmentById(104)));
        employees.put(1005, new Employee(1005, "EE", "E1251694498@qq.com", 0, new DepartmentDao().getDepartmentById(105)));

    }

    //主见自增
    private static Integer initId = 1006;

    //数据库的操作
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //获取全部员工的信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    //通过id查询
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //删除
    public void delete(Integer id) {
        employees.remove(id);
    }
}
