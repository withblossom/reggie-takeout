package cn.yy.reggie_take_out.service.impl;

import cn.yy.reggie_take_out.bean.Setmeal;
import cn.yy.reggie_take_out.mapper.SetmealMapper;
import cn.yy.reggie_take_out.service.SetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl
        extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
