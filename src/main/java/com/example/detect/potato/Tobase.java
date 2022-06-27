package com.example.detect.potato;

import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author SHUN
 * @date 2022/6/26 Sunday
 */
public class Tobase {
    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片地址
     * @return
     */
    public static String getImgBase(String imgFile) {

        // 将图片文件转化为二进制流
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 图片头
        //String imghead = "data:image/jpeg;base64,";
        return Base64.encodeBase64String(data);
    }

}
