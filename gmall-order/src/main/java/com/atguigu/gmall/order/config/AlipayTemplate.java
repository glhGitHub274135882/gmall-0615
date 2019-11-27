package com.atguigu.gmall.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.gmall.order.entity.vo.PayVo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2016101600701505";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbT2iRspWBJfpiyRD2eTFrJeA23jgMdcAhYeLZd8UBRXiagwLZY2r7x1nUD/mXXCrjy6dVCVv31INIZ6kJJbRjew+7Tp5JJQHac2wUzm7XIum7tq51KbdmvwNa5NCsZISAdmXB8XqWJghwYbfqditbfByHMEaC95ZjIMn5syKeFG6UODwEakGrVHPecEI7OArjeaDM6s9PoFt7JUnpbEZ/j7LOtS7Xky/+A0pgOepVZD3u/GAWX5aPdJTXYPqphIRPwy98psAwdRpx7npZXJ8NJmZPQ6PREFxusDwoKIEzki6eIL5+8cw9+3BeKg0KQnwq1n1q2QI+/qGlriarxDMFAgMBAAECggEAaUdd4g3FjIM7BIW5iupCVpiZMXhXVBLpx3c8OCAZGjCGfW67mrLqzXun57lgfnrK1Bmz90/d2NRkncsu6XV2n0zHMDgt0fVVGUjOhhgdSJZxDH7cKa26ldp7oryiu4lzzET1248egvOLGusSqcuqvlwWVomh8pt0hQOwBiSpHbA7D0wH+phILb5buXluJeOWk7SlN+HOP2X9A4N8ynO2xQbVmBQJOBoIOokozNsSMoDM3niu4uW0EtNe7VINoim4KbPrDD3LZPgW16RP1WFT8TgLj589Q4oaD/mhOgsSFk5MV43KBUgLq+HxXZ35C+dHmvPuMJKvdRMlRZpXm63poQKBgQDU6P+OlTfuvw0JoXUopQH/wyr1vhM6CeIjyQxfsBiCu0+UmgNc8ElJ1BScoj2oiK63z1mLWVmhDHbpdRJ1cz9vuug1GsX89Mi7zs+DdyysH72UI7GgaL9RMve9a6tj71IEdZIRsnmQChDHsvSJVcRwpX7Q4bWqoMIwPNEQJ/wEbQKBgQC6vh83keWgRexkraeQF1bV1SyctM12ntMR2yh9QUhHWvLMdY9HFWMr7WgGHzA2LF9plIr7z4jsjEQYO+q7Jd+Zu83FlFXky55Jg2ooIzjhBSO8QdrtWY6A4uA7dHkIoPwTpp+M0+0NouNCI4dNyqc0KZ+DtgpTtOzplqSwXkJZ+QKBgHPPSTMFd6clxLG4YUH6f1vfjT6eZVbsiVVtxo1F/A8gU+lA3RNwoGzIL24D0gYaUu8omkxyDY7MeOIEDEZWqercf/Ji3c76VzXRCI86A9KccUJjJ/tY/rfsQVWHe3KgerxyN0HpEraGwxnc/aa2QGQFoTV9kJN/XiFe81kWZr8BAoGBAJOPbAdzJ+mvlw4vEjsP1jP90NS7Iafb9JklficLkNuhiD37nw3iXoz1eb9soaINCg/ZqsEzytRwOdaUcfUUBk3qUr0Pa5fxNdCNITM6MdXNQ+GcgCwRGkPKArm4XXNuEgJNJcIYLDccQUuf22ii++CCC2P+Usa2Load3ODoerGxAoGAf6sirzVaIJxpQ7Od9pYFwaw0WRVVZ8iqaQeMEMKEAcXK+aRkWIOrBJNCAvYmTuRtpS4Nf7yyLtXAcVmw2jFIqHE4Cpi3DZhqYBDJHUbRAf5QPxJWV08sKCjjnnCDnA3It/5wVnI4zDyuOC/FkZS/oJCtrA7jaUkz/xdopPpl/sY=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsyp9bORFE73VuTI/l0uVk9zTcEWJZWEGPqhKXN+tUm7PMsvQcqFV9VQZr02PzSHGAPjWi9QFjrE8U1m0BA9gSVwkTMuJplH58tOrSxrtHptrDBcDEs86J1siKoNdxY5ljB/3zLdzbHjyDU8VaQBfmQy1ugp9zEDG85p+mE8dVWYqo7tekRAsEgG/d91bgQ1EuqFnf76N9yyrPTYZCDu9gcUzUGgs91RNCafyoa7jq3fbVpB4s52dRCB+kfTdGaklEywjjNr5Q2H7zfDuV/AYthikPQfz6O1ZKnr1cDE7hYhdAA5Gu7hfQs3fKQD2mBHh/3xE2ja/fGRtHM/QAfCcKQIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url= "http://localhost:8888/api/order/pay/success";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url =null;

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
