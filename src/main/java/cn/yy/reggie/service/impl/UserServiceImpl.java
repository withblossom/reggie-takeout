package cn.yy.reggie.service.impl;

import cn.yy.reggie.bean.User;
import cn.yy.reggie.mapper.UserMapper;
import cn.yy.reggie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
