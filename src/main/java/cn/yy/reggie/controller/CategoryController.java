package cn.yy.reggie.controller;

import cn.yy.reggie.bean.Category;
import cn.yy.reggie.bean.Dish;
import cn.yy.reggie.bean.Setmeal;
import cn.yy.reggie.common.CustomRuntimeException;
import cn.yy.reggie.common.R;
import cn.yy.reggie.service.CategoryService;
import cn.yy.reggie.service.DishService;
import cn.yy.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("新增菜品分类 {}",category);
        categoryService.save(category);
        return R.success("新增菜品分类成功");
    }

    @GetMapping("/page")
    public R<Page<Category>> page(int page,int pageSize){
        log.info("菜品分页查询 page {} pageSize {}",page,pageSize);
        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<Category>().orderByAsc(Category::getSort);
        categoryService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> delete(Long ids){
        log.info("删除菜品分类 {}",ids);

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<Dish>().eq(Dish::getCategoryId, ids);
        long count = dishService.count(dishLambdaQueryWrapper);

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<Setmeal>().eq(Setmeal::getCategoryId, ids);
        long count1 = setmealService.count(setmealLambdaQueryWrapper);
        if (count1 + count > 0){
            throw new CustomRuntimeException("删除失败：该分类下还有项目");
        }
        categoryService.removeById(ids);
        return R.success("删除成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("菜品分类修改 {}",category);
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Integer type){
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<Category>()
                .eq(type != null,Category::getType, type)
                .orderByAsc(Category::getSort)
                .orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(lambdaQueryWrapper);
        return R.success(list);
    }
}
