package cn.yy.reggie.dto;

import cn.yy.reggie.bean.Setmeal;
import cn.yy.reggie.bean.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
