package cn.yy.reggie.service;

import cn.yy.reggie.bean.Category;
import cn.yy.reggie.common.R;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CategoryService extends IService<Category> {
    R<String> delete(Long id);
}
