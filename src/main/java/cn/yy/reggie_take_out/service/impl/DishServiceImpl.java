package cn.yy.reggie_take_out.service.impl;

import cn.yy.reggie_take_out.bean.Dish;
import cn.yy.reggie_take_out.mapper.DishMapper;
import cn.yy.reggie_take_out.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl
        extends ServiceImpl<DishMapper, Dish> implements DishService {
}
