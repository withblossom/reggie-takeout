package cn.yy.reggie.service.impl;


import cn.yy.reggie.bean.DishFlavor;
import cn.yy.reggie.mapper.DishFlavorMapper;
import cn.yy.reggie.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
