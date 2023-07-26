package cn.yy.reggie_take_out.controller;

import cn.yy.reggie_take_out.bean.Employee;
import cn.yy.reggie_take_out.common.R;
import cn.yy.reggie_take_out.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){

        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<Employee>()
                .eq(Employee::getUsername, employee.getUsername());
        Employee one = employeeService.getOne(lambdaQueryWrapper);

        if (one == null) {
            return R.error("用户不存在!!!");
        }

        String password = employee.getPassword();
        String asHex = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!one.getPassword().equals(asHex)) {
            return R.error("密码错误！！！");
        }

        if (one.getStatus() == 0){
            return R.error("账号已禁用！！！");
        }

        request.getSession().setAttribute("employee",one.getId());
        return R.success(one);
    }

    @PostMapping("logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！！！");
    }

    @PostMapping()
    public R<String> add(@RequestBody Employee employee){

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employeeService.save(employee);
        return R.success("添加成功！！！");
    }

    @GetMapping("page")
    public R<Page<Employee>> page(int page,int pageSize,String name){

        Page<Employee> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Employee> lambdaQueryWrapper =
                new LambdaQueryWrapper<Employee>()
                        .like(StringUtils.hasLength(name), Employee::getName, name)
                        .orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, lambdaQueryWrapper);

        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(@RequestBody Employee employee){

        employeeService.updateById(employee);
        return R.success("更新成功！！！");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable("id") Long id){

        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }
}
