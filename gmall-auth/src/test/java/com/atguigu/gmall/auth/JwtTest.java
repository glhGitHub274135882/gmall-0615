package com.atguigu.gmall.auth;

import com.atguigu.core.utils.JwtUtils;
import com.atguigu.core.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTest {
    private static final String pubKeyPath = "F:\\project\\temp\\rsa.pub";

    private static final String priKeyPath = "F:\\project\\temp\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "gaole^^**&&");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "11");
        map.put("username", "liuyan");
        // 生成token
        String token = JwtUtils.generateToken(map, privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6IjExIiwidXNlcm5hbWUiOiJsaXV5YW4iLCJleHAiOjE1NzM2OTc2NDZ9.ndenFEHiqupNCkoofmkUd4ndLcZg9bTwjRC_QlKTY2IISXwGwOUk5Xc8fbaPNQULr-axriBxVoVIkuAOF-C3hdwDOa5k0dKw_GVFUc1ZYweOs3iyHcxL64dwR9vBFtDTR_6rqhJ_FFFwK2fudqK3LhtUz6i-09OY1s1UaD2p2wK5az_QsZRXbVHLukFt-5OHRMLnj04CNHEHY-w8JStagiSvjwLCHynpR7nxbPFqgdqKv0xI_b3ep_alCODDskLanBlzXzWLuA2ycpCYBjadwDyIPNHRPAst4ccpSoE-Y-Bo5VN846tCekVHU5L3n-RgEPsjG032fAU5XS74mogzQA";

        // 解析token
        Map<String, Object> map = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + map.get("id"));
        System.out.println("userName: " + map.get("username"));
    }
}