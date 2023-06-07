package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;



    public static  Result success(){
        return new Result("200","success",null);
    }
    public static Result success(Object data){
        return new Result("200","success",data);
    }
    public static Result error(){
        return new Result("0","error",null);
    }

    public static Result error(String code, String msg){
        return new Result(code,msg,null);
    }
}
