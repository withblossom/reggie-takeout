package cn.yy.reggie.service.impl;

import cn.yy.reggie.bean.Category;
import cn.yy.reggie.bean.Dish;
import cn.yy.reggie.bean.Setmeal;
import cn.yy.reggie.common.R;
import cn.yy.reggie.mapper.CategoryMapper;
import cn.yy.reggie.service.CategoryService;
import cn.yy.reggie.service.DishService;
import cn.yy.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl
        extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public R<String> delete(Long id) {

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<Dish>().eq(Dish::getCategoryId, id);
        long count = dishService.count(dishLambdaQueryWrapper);

        if (count > 0){
            return R.error("删除失败，该分类下存有菜品！！！");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<Setmeal>().eq(Setmeal::getCategoryId, id);
        long count1 = setmealService.count(setmealLambdaQueryWrapper);

        if (count1 > 0){
            return R.error("删除失败，该分类下存有套餐！！！");
        }

        removeById(id);
        return R.success("删除成功！！！");
    }
}
