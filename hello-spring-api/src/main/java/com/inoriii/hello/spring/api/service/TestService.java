package com.inoriii.hello.spring.api.service;

import com.inoriii.hello.spring.api.dto.AddUserDTO;
import com.inoriii.hello.spring.api.dto.FetchUserDTO;
import com.inoriii.hello.spring.api.vo.FetchUserVO;
import com.inoriii.hello.spring.api.vo.Pager;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/6/8 21:38
 * @description:
 */
public interface TestService {
    /**
     * 打印message
     *
     * @param message message
     */
    void printMessage(String message);

    /**
     * 插入测试用户
     *
     * @param addUserDTO
     */
    void addUser(AddUserDTO addUserDTO);

    /**
     * 插入测试用户
     *
     * @param addUserDTOList
     */
    void addUserList(List<AddUserDTO> addUserDTOList);

    List<FetchUserVO> fetchUser(FetchUserDTO addFetchUserDTO);

    Object getKey(String key);

    Pager<FetchUserVO> getUserByAddresses(List<String> address, long page, long size);
}
