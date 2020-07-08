package com.study.mybatisplus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatisplus.entity.User;
import com.study.mybatisplus.framework.MyPage;
import com.study.mybatisplus.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @RequestMapping(path = "/list/page",method = RequestMethod.GET)
    public Map<String, Object> getListPage(@RequestBody MyPage page) {
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

    /**
     * @Author cy
     * @Date 2020/7/3 17:33
     * @Description URI模板使用
     * @Param HashMap:通过@PathVariable读取到@RequestMapping注解中的path中的所有变量
     * @return path中的所有变量
     **/
    @RequestMapping(path = "/list/params")
    public Map<String, Object> getParams(@RequestBody String id) {
        result = new HashMap<>();
        try {
            logger.info("#id = " + id);
            result.put("id",id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;

    }

    @RequestMapping("/something")
    public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity,@ModelAttribute String defaultAdminName ) throws UnsupportedEncodingException {
        String Content_Type = requestEntity.getHeaders().getFirst("Host");
        byte[] requestBody = requestEntity.getBody();

        System.out.println(Content_Type);
        System.out.println(new String(requestBody));
        System.out.println(defaultAdminName);
        // do something with request header and body

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
    }

    @ModelAttribute
    public void getDefautData(Model model){
        String defaultAdminName = "admin";
        String defaultAdminPassword = "admin123";
        model.addAttribute(defaultAdminName);
        model.addAttribute(defaultAdminPassword);
    }

    @RequestMapping("getheader")
    public Map<String,Object> getHeader(@RequestHeader Map<String,String> paramMap){
        result = new HashMap<>();
        try {
            if (!paramMap.isEmpty()){
                result.putAll(paramMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;
    }

    /**
     * @Author cy
     * @Date 2020/7/7 10:29
     * @Description
     * @Param
     * @return
     **/
    @RequestMapping("bindertest")
    public Map<String,Object> binderTest(Date date){
        result = new HashMap<>();
        try {
            if (date != null){
                result.put("date",date);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
