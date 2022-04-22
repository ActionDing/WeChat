package com.ding.wechat.controller;

import com.ding.wechat.util.WeChatUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Actionding
 * @create 2022-04-22 11:30
 */
@RestController
public class WeChatController {
    @RequestMapping(value = "/wx", method = {RequestMethod.GET, RequestMethod.POST})
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 参数
        String signature = request.getParameter("signature");   // 微信加密签名
        String timestamp = request.getParameter("timestamp");   // 时间戳
        String nonce = request.getParameter("nonce");           // 随机数
        String echostr = request.getParameter("echostr");       // 随机字符串
        PrintWriter out = null;
        // 验证消息
        if (WeChatUtil.checkSignature(signature, timestamp, nonce)) {
            if (echostr != null) {
                System.out.println(echostr);
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.write(echostr);
            }
        }
    }
}
