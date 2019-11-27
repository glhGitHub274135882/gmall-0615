package com.atguigu.gmall.ums.api;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.ums.entity.MemberEntity;
import com.atguigu.gmall.ums.entity.MemberReceiveAddressEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-12 23:30
 */
public interface GmallUmsApi {

    @GetMapping("ums/member/query")
    public Resp<MemberEntity> queryUser(@RequestParam("username")String userName, @RequestParam("password")String password);

    @GetMapping("ums/memberreceiveaddress/{userId}")
    public Resp<List<MemberReceiveAddressEntity>> queryAddressByuserId(@PathVariable("userId")Long userId);

    //("详情查询")
    @GetMapping("ums/member/info/{id}")
    public Resp<MemberEntity> queryUserById(@PathVariable("id") Long id);
}
