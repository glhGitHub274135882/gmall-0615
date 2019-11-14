package com.atguigu.core.exception;

/**
 * @author shkstart
 * @create 2019-11-13 0:00
 */
public class GmallException extends  RuntimeException{

    public GmallException(){

    }
    public GmallException(String message){
        super(message);
    }
}
