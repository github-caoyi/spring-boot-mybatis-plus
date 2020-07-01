package com.study.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cy
 * @since 2020-06-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * @Author cy
     * @Date 2020/7/1 10:29
     * @Description
     * @Param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @return 分页对象
     **/
    IPage<User> selectPageVo(Page<?> page);
 }
