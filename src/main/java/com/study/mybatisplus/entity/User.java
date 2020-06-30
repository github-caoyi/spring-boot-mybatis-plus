package com.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author cy
 * @since 2020-06-24
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * IdType.AUTO：数据库ID自增
     * 注意：数据库表字段是bigint(20)类型
     *       表设计要勾选自动递增
     */
    @TableId(value = "id" , type = IdType.AUTO)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
