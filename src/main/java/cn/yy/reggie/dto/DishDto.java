package cn.yy.reggie.dto;


import cn.yy.reggie.bean.Dish;
import cn.yy.reggie.bean.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
