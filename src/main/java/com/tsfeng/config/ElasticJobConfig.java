package com.tsfeng.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tsfeng
 * @date 2020/4/13 11:30 上午
 */
@Configuration
@ConditionalOnExpression("'${elastic-job.serverList}'.length() > 0")
public class ElasticJobConfig {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter(@Value("${elastic-job.serverList}") final String serverList,
                                             @Value("${elastic-job.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

}
