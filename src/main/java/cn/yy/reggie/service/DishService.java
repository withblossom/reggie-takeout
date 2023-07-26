package cn.yy.reggie.service;

import cn.yy.reggie.bean.Dish;
import cn.yy.reggie.common.R;
import cn.yy.reggie.dto.DishDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {
    R<String> saveWithFlavor(DishDto dishDto);

    R<DishDto> get(Long id);

    R<String> updateWithFlavor(DishDto dishDto);
}
