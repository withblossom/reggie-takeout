package cn.yy.reggie_take_out.service.impl;

import cn.yy.reggie_take_out.bean.User;
import cn.yy.reggie_take_out.mapper.UserMapper;
import cn.yy.reggie_take_out.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
