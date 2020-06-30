package com.study.mybatisplus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatisplus.entity.User;
import com.study.mybatisplus.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cy
 * @since 2020-06-24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private Map<String, Object> result;
    @Autowired
    private IUserService iUserService;


    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> getList() {
        result = new HashMap<>();
        try {
            logger.info("#用户信息列表--------");
            List<User> userList = iUserService.list();
            result.put("userList", userList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;

    }

    @ResponseBody
    @RequestMapping({"/save", "/update"})
    public void saveOrUpdate(@RequestBody User user) {
        result = new HashMap<>();
        try {
            logger.info("#保存或更新用户信息--------");
            String result = iUserService.saveOrUpdate(user) ? "success" : "fail";
            logger.info("#保存或更新用户信息:" + result + "--------");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public void delete(@RequestBody User user) {
        result = new HashMap<>();
        try {
            logger.info("#删除用户信息--------");
            String result = iUserService.removeById(user.getId()) ? "success" : "fail";
            logger.info("#删除用户信息:" + result + "--------");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }

    /**
     * 分页查询实现
     * Page(current,size)
     * current:当前页,long类型
     * size:每页显示的数量,long类型
     * 可参考其构造方法
     */
    @ResponseBody
    @RequestMapping("/list/page")
    public Map<String, Object> getListPage(@RequestBody Page page) {
        result = new HashMap<>();
        try {
            logger.info("#用户信息列表分页--------");
            IPage<User> iPage = iUserService.selectUserPage(page);
            result.put("userList", iPage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;

    }
}
