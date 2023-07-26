package cn.yy.reggie_take_out.service.impl;

import cn.yy.reggie_take_out.bean.Dish;
import cn.yy.reggie_take_out.bean.DishFlavor;
import cn.yy.reggie_take_out.common.R;
import cn.yy.reggie_take_out.dto.DishDto;
import cn.yy.reggie_take_out.mapper.DishMapper;
import cn.yy.reggie_take_out.service.DishFlavorService;
import cn.yy.reggie_take_out.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    public R<DishDto> get(Long id) {
        DishDto dishDto = new DishDto();
        Dish dish = getById(id);
        BeanUtils.copyProperties(dish,dishDto);
        Long dishId = dish.getId();
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<DishFlavor>().eq(DishFlavor::getDishId, dishId);
        List<DishFlavor> flavors = dishFlavorService.list(lambdaQueryWrapper);
        dishDto.setFlavors(flavors);
        return R.success(dishDto);
    }

    @Override
    @Transactional
    public R<String> updateWithFlavor(DishDto dishDto) {
        updateById(dishDto);
        Long dishId = dishDto.getId();
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<DishFlavor>().eq(DishFlavor::getDishId, dishId);
        dishFlavorService.remove(lambdaQueryWrapper);
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(dishFlavor -> {
            dishFlavor.setDishId(dishId);
            return dishFlavor;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);

        return R.success("修改成功");
    }

    @Override
    @Transactional
    public R<String> saveWithFlavor(DishDto dishDto) {
        save(dishDto);
        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(dishFlavor -> {
            dishFlavor.setDishId(dishId);
            return dishFlavor;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
        return R.success("添加成功");
    }

}
