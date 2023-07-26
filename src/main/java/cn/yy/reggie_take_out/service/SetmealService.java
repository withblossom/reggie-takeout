package cn.yy.reggie_take_out.service;

import cn.yy.reggie_take_out.bean.Setmeal;
import cn.yy.reggie_take_out.common.R;
import cn.yy.reggie_take_out.dto.SetmealDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    R<String> saveWithDish(SetmealDto setmealDto);

    R<String> deleteWithDish(List<Long> ids);
}
