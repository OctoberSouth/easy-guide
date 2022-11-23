package com.lp.easy.thirdparty;

import com.aliyun.oss.OSS;
import com.lp.easy.thirdparty.aliyun.OssSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThirdPartyApplicationTests {


    @Test
    void contextLoads() throws Exception {

//        SendSmsResponse send = MessagesSingleton.getSingleton().send("18711158914", "{\"code\":\"1234\"}", "SMS_154950909", "阿里云短信测试");
//        System.out.println(JSONUtil.toJsonStr(send));

//        System.out.println(JSONUtil.toJsonStr(OssSingleton.getSingleton().getPolicy()));


        OSS ossClient3 = OssSingleton.getSingleton().getOssClient();
        OSS ossClient4 = OssSingleton.getSingleton().getOssClient();

        System.out.println(ossClient3 == ossClient4);
        System.out.println(ossClient3.equals(ossClient4));
    }

}
