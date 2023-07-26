package cn.yy.reggie_take_out.dto;

import cn.yy.reggie_take_out.bean.Setmeal;
import cn.yy.reggie_take_out.bean.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
