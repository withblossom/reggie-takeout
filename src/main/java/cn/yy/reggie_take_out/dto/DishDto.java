package cn.yy.reggie_take_out.dto;


import cn.yy.reggie_take_out.bean.Dish;
import cn.yy.reggie_take_out.bean.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
