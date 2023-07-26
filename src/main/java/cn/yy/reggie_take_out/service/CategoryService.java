package cn.yy.reggie_take_out.service;

import cn.yy.reggie_take_out.bean.Category;
import cn.yy.reggie_take_out.common.R;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CategoryService extends IService<Category> {
    R<String> delete(Long id);
}
