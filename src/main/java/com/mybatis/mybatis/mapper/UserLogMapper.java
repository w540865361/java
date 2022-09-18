package com.mybatis.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.mybatis.mybatis.entity.UserLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
public interface UserLogMapper extends BaseMapper<UserLog> {
    public List<UserLog> selectid();
}
