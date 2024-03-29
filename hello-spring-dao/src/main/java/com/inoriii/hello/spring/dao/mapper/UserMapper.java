package com.inoriii.hello.spring.dao.mapper;

import com.inoriii.hello.spring.api.vo.FetchUserRoleVO;
import com.inoriii.hello.spring.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    FetchUserRoleVO selectFetchUserRoleVOByUsername(String username);

    int updatePasswordByUserName(@Param("username") String username, @Param("password") String password);
}