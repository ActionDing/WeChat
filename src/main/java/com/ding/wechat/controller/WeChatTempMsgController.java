package com.ding.wechat.controller;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Actionding
 * @create 2022-04-22 16:12
 */
@RestController
public class WeChatTempMsgController {

    @Inject
    protected WxMpService wxService;

    @GetMapping("/push")
    public void push() throws WxErrorException {
        // 1、配置
        WxMpDefaultConfigImpl wxStorage = new WxMpDefaultConfigImpl();
        wxStorage.setAppId("wx5c5522993f42cb82"); // appID
        wxStorage.setSecret("c1b64da623e81d6c4f58dddf8421ecb7"); // appsecret
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        // 2、推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oEQpt5_f253f6xYJ0UsN0n6_K9SA") // 要推送的用户openid
                .templateId("aYa3UsJYRMB8yC04HpvyKQtA-yzBFbJu5uWrXPi9UuQ") // 模板ID
                .build();
        // 3、模版消息
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        templateMessage.addData(new WxMpTemplateData("time", dateFormat.format(new Date()), "#FF00FF"));
        // 4、发起推送
        try {
            String msg = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("推送成功：" + msg);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
