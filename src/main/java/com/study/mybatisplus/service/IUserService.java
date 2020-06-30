package com.study.mybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cy
 * @since 2020-06-24
 */
public interface IUserService extends IService<User> {

    IPage<User> selectUserPage(Page<User> page);
}
