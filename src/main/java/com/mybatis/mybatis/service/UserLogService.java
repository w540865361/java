package com.mybatis.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.mybatis.mybatis.entity.UserLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
public interface UserLogService extends IService<UserLog> {
 public List<UserLog> selectid();
}
