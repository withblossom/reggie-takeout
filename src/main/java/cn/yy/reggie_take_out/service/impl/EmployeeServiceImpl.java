package cn.yy.reggie_take_out.service.impl;

import cn.yy.reggie_take_out.bean.Employee;
import cn.yy.reggie_take_out.mapper.EmployeeMapper;
import cn.yy.reggie_take_out.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl
        extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
