package cn.yy.reggie.service.impl;

import cn.yy.reggie.bean.Employee;
import cn.yy.reggie.mapper.EmployeeMapper;
import cn.yy.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl
        extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
