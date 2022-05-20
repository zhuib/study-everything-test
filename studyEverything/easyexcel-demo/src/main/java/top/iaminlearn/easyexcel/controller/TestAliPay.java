package top.iaminlearn.easyexcel.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2022/3/2 20:19
 */

@Controller
@RequestMapping("/order")
public class TestAliPay {

    @RequestMapping("/pay")
    @ResponseBody
    public String getForm(HttpServletRequest httpRequest,
                          HttpServletResponse httpResponse) throws AlipayApiException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2021000117637105",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKaw2ea20yjv9c0FzOq0EgGKfG5/SMkgc7MwGxZOtuXp1StyEpSeLrOyg93nkh4yvfOaToMQ2mRcsKyPyEUvLoWqYl+KsQGb14HmTFtkIClcX+BPPFbpuujkcWniow23xUh5/cU+HZwhk5isSjUCr9/cfNPIl1gBtOH34qq8ddb/IYOdjRA5I3BaykYPWhtrWr/Yb7vAXjS/Fvp0Ai4BG0sea3p9h8aQsli72kVVRn/wsntb26/iwlQhQxYmZi2sgseInNmgKp/pRQlkVwHGt0t2So3qii+QMuhdScpzcyY2/40aPuxt788kQ6orKncJFNtv+SVKx0Tjz7AdyDRW0HAgMBAAECggEAW45F+tSJ7RhC9sxnptgGAsnsKBgDXPu2RsUNSJ49T8rLewvgpmPV8cw2aP3/15dgzI1fc6oGokWl8ONSi+AmBeT340PhOowcFBjehRxx+y3AQa5a4+uulD5N7Tr/I0P6sBrpdZ4SVKwbbUFJodCEL26uuyVgstTiusD//g+TlkxZ7tabuiTnxO8UecfI5nOoou9icy+3LopCxMdsRIun75P7PBjC+J/yxA+qwnhw53vkpv05SrQO2ZuThQ2VhCRtTTN+OXt1sKajj9VdS0iiDfrbvb8vjQddQ/740stclb+q8nSGz5MOf18C9fUrcujQ9H2+l9EgN6cnW23Dcc1gIQKBgQDfxj9YOsND/WGnFBQ5fL25hbINBYhYbLK9+4zSnAEvm/081QLiAPNAkxg1cxys2NiJmZd4pg3lvUP2SkRIocxY0ZzR1Yp/dkod39zy1q9U6sZfPUG8kiabg1jp7lI58y1O882hCo8eSz0fvv/8M8OyzChr6mBJ4RV6YZvooEeecQKBgQCeWgfPYVz6QUcJS/uTpgwMLf7cgR+LGm2ECNzzYeP3DExzCLewXoR4X2CioXGEVT3hJKhUDAHezRTaOjDEvcRvi8yA3srmlgBA2bsnElA1Nu75PVHa1oLL2rO0y022G6ED9SaKHcewPR97XyDyjuLyeJCGbiKN8+S8Xw55cwdu9wKBgASdymC/lZepeoI/38rvz9or7+hk3NM2KtnQfq85mKyqwyamHlEbQ+lpH2662TSXpJ/yEzXh9xduOCC0l4QIEncim+GYTisi/ZRwMdawpx1RWpZMZTbGrRU4OHVa/UdExQKD9RIFbb5qPvCQuCczyi2+GHdhdmsPwamsqdK6u7+BAoGBAJKtzoI4K8nb4ZMpANYZfxthF6zlJnTRfBRLqW8AhAt5Ju9/abdDNe3SW08JwH7V/sWoEQwZOzpIOUKTmE0FgwnYMwxEUyoYVA++H2/ntMg2JFJWjJXrEakG4mNb1pYTLaje0l4pi6kiVSEQUxKbbHkxWJ5NMlDPVFdwrZWuCXqtAoGAa9K0hwB1kUIVBIkiqyVidjp72W4fDiY1sEsY1ntkTdoBOeAAcf+9g0BNDrzdLmmz5XlU2hXQG02jpXOjsk6cQaWBr7BZNkGQiwjprFDjlxp3YpUbD0uZOi3iuhlAMin6Wpb+zsvWz3tQ/7gXcg0NJVS4ORn+xZkTg0D66k3tO8I=",
                "json", "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlfPO0BgeUS9q7BjYqhokGMNZ2nXL/IHr1FKaOCpJLjjcYzI88uussaoHZylxUh07bF6hJuj4nl0tGGHyOzKLwbRFbWC0+auRCXvpcfJaWEQ+cqdzvplcVg/qwXlfcjPH5aMoMhzE+hvlOANkerL2QrCQEl4aOsR+vEQIdPRP+2gSq0/ejL6YAcmyl16bkdXy9W9+tU2749JzN3MJGD5JGbkyZvGC/L7HPzA/UK1CCcR/9TeXSbmqiDMYWI8/2TghCdopD6yDBiPwyqWLjwVbfcVSiX9c4lt0RNz6PpXS4PTCqyEQpEi7K3quO/JRXYDrcT0oS39RtjBPWRa/+gNBqQIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:4949/mall#/");
        alipayRequest.setNotifyUrl("http://a2a3-112-96-227-191.ngrok.io/order/aliPayNotify");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"201502333433010101002\"," +
                " \"total_amount\":\"88.88\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_WAY\"" +
                " }");//填充业务参数

        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
        return form;
    }

    @RequestMapping("/aliPayNotify")
    public void aliPayNotify(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        parameterMap.forEach((s, strings) -> {
            paramsMap.put(s, strings[0]);
        });
        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlfPO0BgeUS9q7BjYqhokGMNZ2nXL/IHr1FKaOCpJLjjcYzI88uussaoHZylxUh07bF6hJuj4nl0tGGHyOzKLwbRFbWC0+auRCXvpcfJaWEQ+cqdzvplcVg/qwXlfcjPH5aMoMhzE+hvlOANkerL2QrCQEl4aOsR+vEQIdPRP+2gSq0/ejL6YAcmyl16bkdXy9W9+tU2749JzN3MJGD5JGbkyZvGC/L7HPzA/UK1CCcR/9TeXSbmqiDMYWI8/2TghCdopD6yDBiPwyqWLjwVbfcVSiX9c4lt0RNz6PpXS4PTCqyEQpEi7K3quO/JRXYDrcT0oS39RtjBPWRa/+gNBqQIDAQAB",
                "utf-8",
                "RSA2"); //调用SDK验证签名
        if(signVerified){
        // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            System.out.println(paramsMap);
        }else{
        // TODO 验签失败则记录异常日志，并在response中返回failure.
        }
    }
}
