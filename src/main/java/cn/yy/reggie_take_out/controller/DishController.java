package cn.yy.reggie_take_out.controller;

import cn.yy.reggie_take_out.bean.Category;
import cn.yy.reggie_take_out.bean.Dish;
import cn.yy.reggie_take_out.bean.DishFlavor;
import cn.yy.reggie_take_out.common.R;
import cn.yy.reggie_take_out.dto.DishDto;
import cn.yy.reggie_take_out.service.CategoryService;
import cn.yy.reggie_take_out.service.DishFlavorService;
import cn.yy.reggie_take_out.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        log.info("添加菜品 {}",dishDto);
        return dishService.saveWithFlavor(dishDto);
    }

    @GetMapping("/page")
    public R<Page<DishDto>> page(int page, int pageSize, String name){
        log.info("菜品分页查询 page {} pageSize {} name {}",page,pageSize,name);
        Page<DishDto> dishDtoPage = new Page<>(page, pageSize);
        Page<Dish> dishPage = new Page<>(page, pageSize);

        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<Dish>()
                .like(name != null, Dish::getName, name)
                .orderByDesc(Dish::getSort);
        dishService.page(dishPage, lambdaQueryWrapper);

        BeanUtils.copyProperties(dishPage,dishDtoPage,"records");
        List<Dish> dishList = dishPage.getRecords();
        List<DishDto> dishDtoList = dishList.stream().map(dish -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish,dishDto);
            Long categoryId = dish.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                dishDto.setCategoryName(category.getName());
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(dishDtoList);
        return R.success(dishDtoPage);
    }

    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        return dishService.get(id);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        return dishService.updateWithFlavor(dishDto);
    }

    @GetMapping("/list")
    public R<List<DishDto>> list(Long categoryId){
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<Dish>()
                .eq(Dish::getCategoryId, categoryId)
                .eq(Dish::getStatus, 1)
                .orderByAsc(Dish::getSort)
                .orderByDesc(Dish::getUpdateTime);
        List<Dish> list = dishService.list(lambdaQueryWrapper);
        List<DishDto> dishDtos = list.stream().map(dish -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish, dishDto);
            Long dishId = dish.getId();
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<DishFlavor>()
                    .eq(DishFlavor::getDishId, dishId);
            List<DishFlavor> dishFlavors = dishFlavorService.list(queryWrapper);
            dishDto.setFlavors(dishFlavors);
            return dishDto;
        }).collect(Collectors.toList());
        return R.success(dishDtos);

    }
}
