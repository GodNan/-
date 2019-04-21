package com.css.bdpfnew.util;

import com.css.bdpfnew.Message;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @Auther: GodNan
 * @Date: 2018/5/30 11:30
 * @Version: 1.0
 * @Description:
 *  校验参数封装错误信息
 */
public class ValidatorUtil {

    private static Message MESSAGE = null;


    public static Message message(){
        return MESSAGE;
    }

    public static Boolean validity(BindingResult result){
        StringBuffer buffer = new StringBuffer();
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                buffer.append(error.getDefaultMessage()+";");
            }
            MESSAGE = Message.error(buffer.toString(),Message.ERROR_CODE_PARAM);
            return true;
        }
        return false;
    }

}
