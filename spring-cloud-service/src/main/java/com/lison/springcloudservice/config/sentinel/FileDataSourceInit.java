package com.lison.springcloudservice.config.sentinel;


import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @className: com.lison.springcloudservice.config.sentinel-> FilePersistence
 * @description: 规则持久化
 * @author: 李村 200900681
 * @createDate: 2024-02-20 10:50
 */
public class FileDataSourceInit implements InitFunc {

    private static final String RULE_FILE_PATH = System.getProperty("user.home") + File.separator;

    private static final String FLOW_RULE_FILE_NAME = "FlowRule.json";

    @Override
    public void init() throws Exception {

        //处理流控规则逻辑
        dealFlowRules();
    }


    private void dealFlowRules() throws FileNotFoundException {
        String ruleFilePath = RULE_FILE_PATH + FLOW_RULE_FILE_NAME;

        //创建流控规则的可读数据源
        FileRefreshableDataSource flowRuleRDS = new FileRefreshableDataSource(
                ruleFilePath, source -> JSON.parseObject((String) source,
                new TypeReference<List<FlowRule>>() {
                })
        );

        // 将可读数据源注册至FlowRuleManager 这样当规则文件发生变化时，就会更新规则到内存
        FlowRuleManager.register2Property(flowRuleRDS.getProperty());

        WritableDataSource<List<FlowRule>> flowRuleWDS = new FileWritableDataSource<>(
                ruleFilePath, this::encodeJson
        );

        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerFlowDataSource(flowRuleWDS);
    }


    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }

}




