package cn.yy.reggie_take_out.service;

import cn.yy.reggie_take_out.bean.Dish;
import cn.yy.reggie_take_out.common.R;
import cn.yy.reggie_take_out.dto.DishDto;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {
    R<String> saveWithFlavor(DishDto dishDto);

    R<DishDto> get(Long id);

    R<String> updateWithFlavor(DishDto dishDto);
}
