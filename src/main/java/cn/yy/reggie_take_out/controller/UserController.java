package cn.yy.reggie_take_out.controller;

import cn.yy.reggie_take_out.bean.User;
import cn.yy.reggie_take_out.common.R;
import cn.yy.reggie_take_out.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String,Object> map, HttpServletRequest request){
        String phone = (String) map.get("phone");
        if (!StringUtils.hasLength(phone)){
            return R.error("电话号码为空");
        }
        log.info("用户登录 {}",phone);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>().eq(User::getPhone, phone);
        User user = userService.getOne(lambdaQueryWrapper);
        if (user == null) {
            user = new User();
            user.setPhone(phone);
            user.setStatus(1);
            userService.save(user);
        }
        request.getSession().setAttribute("user",user.getId());
        return R.success(user);
    }
}
