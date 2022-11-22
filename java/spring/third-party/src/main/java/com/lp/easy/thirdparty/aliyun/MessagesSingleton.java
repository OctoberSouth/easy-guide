package com.lp.easy.thirdparty.aliyun;

import cn.hutool.extra.spring.SpringUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.Data;

/**
 * 阿里云发送短信，采用单例模式降低降低资源消耗
 */
@Data
public class MessagesSingleton {

    private static Client client;

    private volatile static MessagesSingleton singleton;

    private MessagesSingleton() {

    }

    public static MessagesSingleton getSingleton() throws Exception {
        if (singleton == null) {
            synchronized (MessagesSingleton.class) {
                if (singleton == null) {
                    singleton = new MessagesSingleton();
                    //方便动态获取值
                    AliConfig access = SpringUtil.getBean(AliConfig.class);
                    Config config = new Config()
                            // 必填，您的 AccessKey ID
                            .setAccessKeyId(access.getAccessKeyId())
                            // 必填，您的 AccessKey Secret
                            .setAccessKeySecret(access.getAccessKeySecret())
                            // 访问的域名
                            .setEndpoint(access.getMsmEndpoint());
                    client = new Client(config);
                }
            }
        }
        return singleton;
    }

    /**
     * @param phone         短信发送手机号
     * @param templateParam 发送内容，json格式
     * @param templateCode  模板CODE
     * @param signName      短信签名名称
     * @return
     */
    public SendSmsResponse send(String phone, String templateParam, String templateCode, String signName) throws Exception {
        SendSmsRequest sendSmsRequest = new SendSmsRequest().setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phone)
                .setTemplateParam(templateParam);
        RuntimeOptions runtime = new RuntimeOptions();
        return client.sendSmsWithOptions(sendSmsRequest, runtime);
    }
}
