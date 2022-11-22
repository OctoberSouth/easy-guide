package com.lp.easy.thirdparty.aliyun;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class OssSingleton {

    private static OSS ossClient;

    private static AliConfig access;

    private volatile static OssSingleton singleton;

    private OssSingleton() {

    }

    public static OssSingleton getSingleton() {
        if (singleton == null) {
            synchronized (MessagesSingleton.class) {
                if (singleton == null) {
                    singleton = new OssSingleton();
                    access = SpringUtil.getBean(AliConfig.class);
                    String accessKeyId = access.getAccessKeyId();
                    String secretAccessKey = access.getAccessKeySecret();
                    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
                    String endpoint = access.getOssEndpoint();
                    // 填写Bucket名称，例如examplebucket。
                    String bucket = access.getBucket();
                    // 填写Host地址，格式为https://bucketname.endpoint。
                    String host = "https://" + bucket + "." + endpoint;
                    // 创建ossClient实例。
                    ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
                }
            }
        }
        return singleton;
    }

    /**
     * 获取签名信息
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String, String> getPolicy() throws UnsupportedEncodingException {
        // 填写Host地址，格式为https://bucketname.endpoint。
        String host = "https://" + access.getBucket() + "." + access.getOssEndpoint();
        String dir = LocalDateTimeUtil.format(LocalDate.now(), DatePattern.NORM_DATE_PATTERN);
        Map<String, String> respMap = new LinkedHashMap<String, String>();

        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);
        respMap.put("accessId", access.getAccessKeyId());
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }

}
