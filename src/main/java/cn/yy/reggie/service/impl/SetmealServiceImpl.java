package cn.yy.reggie.service.impl;

import cn.yy.reggie.bean.Setmeal;
import cn.yy.reggie.bean.SetmealDish;
import cn.yy.reggie.common.CustomRuntimeException;
import cn.yy.reggie.common.R;
import cn.yy.reggie.dto.SetmealDto;
import cn.yy.reggie.mapper.SetmealMapper;
import cn.yy.reggie.service.SetmealDishService;
import cn.yy.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl
        extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    public R<String> saveWithDish(SetmealDto setmealDto) {
        save(setmealDto);
        Long setMealId = setmealDto.getId();
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map(setmealDish -> {
            setmealDish.setSetmealId(setMealId);
            return setmealDish;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
        return R.success("保存成功");
    }

    @Override
    @Transactional
    public R<String> deleteWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<Setmeal>()
                .in(Setmeal::getId, ids)
                .eq(Setmeal::getStatus, 1);
        long count = count(lambdaQueryWrapper);
        if (count > 0){
            throw new CustomRuntimeException("套餐还在售卖中，不能删除");
        }
        removeByIds(ids);
        LambdaQueryWrapper<SetmealDish> dishLambdaQueryWrapper = new LambdaQueryWrapper<SetmealDish>()
                .in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(dishLambdaQueryWrapper);
        return R.success("删除成功");
    }
}
