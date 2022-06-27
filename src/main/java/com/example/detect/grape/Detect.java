package com.example.detect.grape;


import com.example.detect.potato.Tobase;
import com.example.gateway.demo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SHUN
 * @date 2022/6/26 Sunday
 */
@Controller
@RequestMapping("/grape")
public class Detect {
    @GetMapping("/detect")
     @ResponseBody
    public String result(@RequestParam("pic") String pic) {
        String host = "https://grape.market.alicloudapi.com";
        String path = "/ai_image_single_classification/ai_grape/grape/v1";
        String method = "POST";
        String appcode = "f8ef416aad254d07a7f5ea08933d626c";
        String base64= Tobase.getImgBase("src/main/resources/test1.png");

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("IMAGE", "data:image/jpg;base64,"+ pic);

        String result="";
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //System.out.println(response.toString());
            //获取response的body

            result = EntityUtils.toString(response.getEntity());

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
