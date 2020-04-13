package com.tsfeng.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.tsfeng.job.MyElasticJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author tsfeng
 * @date 2020/4/13 3:35 下午
 */
@Configuration
public class SimpleJobConfig {
    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Bean
    public SimpleJob simpleJob() {
        return new MyElasticJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob,
                                           @Value("${simpleJob.cron}") final String cron,
                                           @Value("${simpleJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${simpleJob.shardingItemParameters}") final String shardingItemParameters,
                                           @Value("${simpleJob.jobParameters}") final String jobParameters) {
        return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), cron,
                shardingTotalCount, shardingItemParameters, jobParameters));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron,
                                                         final int shardingTotalCount, final String shardingItemParameters,
                                                         final String jobParameters) {
        // Core：JobCoreConfiguration
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters).jobParameter(jobParameters).build();

        // Type：JobTypeConfiguration
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClass.getCanonicalName());

        // Lite：LiteJobConfiguration
        return LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();
    }
}
