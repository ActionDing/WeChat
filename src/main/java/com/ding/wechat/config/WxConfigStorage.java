package com.ding.wechat.config;

import lombok.Data;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Actionding
 * @create 2022-04-22 22:54
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxConfigStorage extends WxMpDefaultConfigImpl {
    private String openid;
    private String kfAccount;
    private String qrconnectRedirectUrl;
    private String templateId;
    private String keyPath;
}
