package com.lp.easy.thirdparty.aliyun;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("aliyun.oss.sms")
@Component
@RefreshScope
public class AliConfig {

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 访问的域名
     */
    private String msmEndpoint;

    /**
     * oss访问的域名
     */
    private String ossEndpoint;

    /**
     * oss Bucket
     */
    private String bucket;
}
