package cn.yy.reggie_take_out.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "xxxxx")
public class CustomRuntimeException extends RuntimeException{

    public CustomRuntimeException(String message){
        super(message);
    }
}
