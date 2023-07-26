package cn.yy.reggie_take_out.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R<String> exceptionHandler(Exception e){
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
        e.printStackTrace();
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");
        return R.error(e.getMessage());
    }
}
