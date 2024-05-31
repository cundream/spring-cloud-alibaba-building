package com.lison.springcloudservice.service.impl;

import com.lison.springcloudservice.mapper.TestMapper;
import com.lison.springcloudservice.mapper.UserMapper;
import com.lison.springcloudservice.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: com.lison.springcloudservice.service.impl-> TestServiceImpl
 * @description:
 * @author: Lison
 * @createDate: 2024-05-27
 */
@Service
public class TestServiceImpl  implements ITestService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestMapper testMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertUser() {
        userMapper.insert();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertTest() {
        testMapper.insert();
    }





}
