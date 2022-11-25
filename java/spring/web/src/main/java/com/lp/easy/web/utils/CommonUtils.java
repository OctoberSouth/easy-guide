package com.lp.easy.web.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtils {

    // 密码算法
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
}
