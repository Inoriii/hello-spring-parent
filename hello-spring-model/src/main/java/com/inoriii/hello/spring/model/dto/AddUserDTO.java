package com.inoriii.hello.spring.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sakura
 * @date: 2021/8/1 16:15
 * @description:
 */
@Data
public class AddUserDTO {
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}