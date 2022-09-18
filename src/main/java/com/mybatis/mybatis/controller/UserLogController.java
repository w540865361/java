package com.mybatis.mybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.entity.UserLog;
import com.mybatis.mybatis.service.UserLogService;
import com.mybatis.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@Slf4j
@RestController
@RequestMapping("/user-log")
public class UserLogController {
    @Autowired
    UserLogService service;
    @Autowired
    UserService userService;
    @GetMapping("select")
    public Page userlog(Integer page, Integer pagesize, String name){
       Page<UserLog> pageInfo=new Page<>(page,pagesize);
        QueryWrapper<UserLog> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.like(StringUtils.isNotEmpty(name),"user",name);
        queryWrapper1.orderByDesc("create_time");
        service.page(pageInfo,queryWrapper1);
        return pageInfo;
    }
    @PostMapping("/save")
    public String save(@RequestBody UserLog userLog, HttpServletRequest request){
        String user1=userLog.getUser();
        String password=userLog.getPassword();
        userLog.setBehavior(LocalTime.now()+"尝试登录");
        userLog.setCreateTime(LocalDateTime.now());
        String userAgent = request.getHeader("sec-ch-ua-platform");
        userLog.setUseragent(userAgent);
        QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("user",user1);
        queryWrapper1.eq("password",password);
        User user2=userService.getOne(queryWrapper1);
        if(user2==null){
            userLog.setStatus(0);
        }else {
            userLog.setStatus(1);
        }
        service.save(userLog);
        log.info("id为{}",userLog.getId());
        userLog.setBy1(userLog.getId());
        service.updateById(userLog);
        return "成功";
    }
}
