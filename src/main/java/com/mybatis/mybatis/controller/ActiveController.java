package com.mybatis.mybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mybatis.mybatis.common.R;
import com.mybatis.mybatis.entity.Active;
import com.mybatis.mybatis.entity.UserLog;
import com.mybatis.mybatis.service.ActiveService;
import com.mybatis.mybatis.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wjy
 * @since 2022-08-17
 */
@RestController
@RequestMapping("/active")
public class ActiveController {
    @Autowired
    ActiveService activeService;
    @Autowired
    UserLogService userLogService;

    @PostMapping("/activesave")
    public String activesave(@RequestBody Active active, HttpServletRequest request) {

        activeService.save(active);
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" 添加了"+active.getTime()+"日程表");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return "添加日程成功";
    }

    @PutMapping("/select/{day}")
    public R<List<Active>> activeselect(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day, HttpServletRequest request) {

        QueryWrapper<Active> queryWrapper = new QueryWrapper();
        queryWrapper.eq("time", day);
        queryWrapper.orderByAsc("starttime");
        List<Active> active1 = activeService.list(queryWrapper);
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" 查看了"+day+"日程表");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));
        return R.success(active1);

    }
    @PutMapping("/supdate/{id}")
    public R<Active> update(@PathVariable Integer id,HttpServletRequest request){
        Active active=activeService.getById(id);
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" 查看了详细日程表");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));
        return R.success(active);
    }
    @PostMapping("update")
    public R<String> update1(@RequestBody Active active,HttpServletRequest request){
        activeService.updateById(active);
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" 修改了"+active.getTime()+"日程表");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));
        return R.success("修改日程成功");
    }
    @DeleteMapping("/delete/{id}")
    public R<String> delete(@PathVariable Integer id,HttpServletRequest request){
        activeService.removeById(id);
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" 删除了编号为:"+id+"日程表");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));
        return R.success("成功");
    }
    @PostMapping()
    public R<List<Active>> list(HttpServletRequest request){
        List<Active> activeList=activeService.list();
        List<UserLog> userLog=userLogService.selectid();
        String behavior=userLog.get(0).getBehavior().concat(" 查看了用户日程表");
        userLog.get(0).setBehavior(behavior);
        userLogService.updateById(userLog.get(0));

        return R.success(activeList);

    }
}