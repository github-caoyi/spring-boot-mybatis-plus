package com.study.mybatisplus.framework;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;

/**
 * @Author cy
 * @Date 2020/7/1 15:17
 * @Description
 **/
public class MyPage<T> extends Page<T> {

    //筛选条件
    private HashMap<String,Object> condition;


    public HashMap<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(HashMap<String, Object> condition) {
        this.condition = condition;
    }
}
