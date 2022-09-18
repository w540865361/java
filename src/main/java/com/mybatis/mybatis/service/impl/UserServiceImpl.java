package com.mybatis.mybatis.service.impl;

import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.mapper.UserMapper;
import com.mybatis.mybatis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
