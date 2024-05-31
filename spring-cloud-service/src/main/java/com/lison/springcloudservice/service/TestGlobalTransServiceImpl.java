package com.lison.springcloudservice.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: com.lison.springcloudservice.service-> TestGlobalTransService
 * @description:
 * @author: Lison
 * @createDate: 2024-05-27
 */
@Service
public class TestGlobalTransServiceImpl {
    @Autowired
    private ITestService iTestService;
    @GlobalTransactional
    public void testTrans() throws Exception {
        // 分支事务添加用户信息
        iTestService.insertUser();
        // 分支事务添加订单信息
        iTestService.insertTest();
        // 抛出异常，事务回滚
     //   throw new Exception("test exception");
    }
}
