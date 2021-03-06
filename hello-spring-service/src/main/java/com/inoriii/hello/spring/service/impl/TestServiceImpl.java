package com.inoriii.hello.spring.service.impl;

import com.inoriii.hello.spring.api.dto.AddUserDTO;
import com.inoriii.hello.spring.api.dto.FetchUserDTO;
import com.inoriii.hello.spring.api.service.TestService;
import com.inoriii.hello.spring.api.vo.FetchUserVO;
import com.inoriii.hello.spring.api.vo.Pager;
import com.inoriii.hello.spring.common.annotation.DataSource;
import com.inoriii.hello.spring.common.utils.PagerHelper;
import com.inoriii.hello.spring.common.utils.TestUtil;
import com.inoriii.hello.spring.dao.mapper.UserTestMapper;
import com.inoriii.hello.spring.model.entity.UserTest;
import com.inoriii.hello.spring.model.enums.DataSourceName;
import com.inoriii.hello.spring.service.RedisService;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author sakura
 * @date: 2022/6/8 22:02
 * @description:
 */
@Service
@Log
public class TestServiceImpl implements TestService {
    @Autowired
    private RedisService redisService;
    @Resource
    private UserTestMapper userTestMapper;

    @Override
    public void printMessage(String message) {
        log.info("Received message : " + message);
        log.info(TestUtil.addTestString(message));
    }

    @Override
    @DataSource(DataSourceName.MASTER)
    public void addUser(AddUserDTO addUserDTO) {
        UserTest userTest = new UserTest();
        BeanUtils.copyProperties(addUserDTO, userTest);
        userTestMapper.insertSelective(userTest);
    }

    @Override
    @DataSource(DataSourceName.MASTER)
    public void addUserList(List<AddUserDTO> addUserDTOList) {
        userTestMapper.insertUserDTOList(addUserDTOList);
    }

    @Override
    @DataSource(DataSourceName.SLAVE)
    public List<FetchUserVO> fetchUser(FetchUserDTO addFetchUserDTO) {
        List<UserTest> userTestList = userTestMapper.selectByUserName(addFetchUserDTO.getUsername());
        return userTestList.stream().map(userTest -> FetchUserVO.builder().username(userTest.getUsername()).sex(userTest.getSex()).birthday(userTest.getBirthday()).address(userTest.getAddress()).build()).collect(Collectors.toList());
    }


    @Override
    public Object getKey(String key) {
        return redisService.get(key);
    }

    @Override
    @DataSource(DataSourceName.SLAVE)
    public Pager<FetchUserVO> getUserByAddresses(List<String> address, long page, long size) {
        long offset = PagerHelper.fetchOffset(page, size);
        List<UserTest> userTestList = userTestMapper.selectByAddress(address, offset, size);
        List<FetchUserVO> fetchUserVOList = Optional.ofNullable(userTestList).orElseGet(ArrayList::new).
                stream().map(userTest -> FetchUserVO.builder().username(userTest.getUsername()).sex(userTest.getSex()).birthday(userTest.getBirthday()).address(userTest.getAddress()).build()).collect(Collectors.toList());
        long l = userTestMapper.selectByAddressCount(address);
        return Pager.<FetchUserVO>builder().total(l).page(page).size(size).data(fetchUserVOList).build();
    }
}
