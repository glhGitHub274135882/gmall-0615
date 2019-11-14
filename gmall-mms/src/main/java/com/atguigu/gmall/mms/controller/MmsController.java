package com.atguigu.gmall.mms.controller;

import com.atguigu.gmall.mms.service.MmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shkstart
 * @create 2019-11-14 13:07
 */
@RestController
@RequestMapping("mms")
public class MmsController {
    @Autowired
    private MmsService mmsService;

    @RequestMapping("message")
    public String message(){
        this.mmsService.message();
        return null;
    }
}
