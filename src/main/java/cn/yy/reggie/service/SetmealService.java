package cn.yy.reggie.service;

import cn.yy.reggie.bean.Setmeal;
import cn.yy.reggie.common.R;
import cn.yy.reggie.dto.SetmealDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    R<String> saveWithDish(SetmealDto setmealDto);

    R<String> deleteWithDish(List<Long> ids);
}
