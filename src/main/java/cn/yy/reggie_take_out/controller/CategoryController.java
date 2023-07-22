package cn.yy.reggie_take_out.controller;

import cn.yy.reggie_take_out.bean.Category;
import cn.yy.reggie_take_out.common.R;
import cn.yy.reggie_take_out.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> add(@RequestBody Category category){

        categoryService.save(category);
        return R.success("添加成功！！！");
    }


    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){

        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper =
                new LambdaQueryWrapper<Category>().orderByAsc(Category::getSort);
        categoryService.page(pageInfo,lambdaQueryWrapper);

        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> delete(Long ids){

        return categoryService.delete(ids);
    }

}
