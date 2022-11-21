package com.lp.easy.mysqlmybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.easy.mysqlmybatis.dao.UserMapper;
import com.lp.easy.mysqlmybatis.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 添加
     *
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(User user) {
        this.userMapper.insert(user);
        this.userMapper.insert(user);
    }

    /**
     * 分页使用
     *
     * @return
     */
    public PageInfo<User> page() {
        PageHelper.offsetPage(1, 10);
        List<User> list = this.userMapper.selectList(new User());
        //用PageInfo对结果进行包装
        PageInfo page = new PageInfo(list);
        return page;
    }

}
