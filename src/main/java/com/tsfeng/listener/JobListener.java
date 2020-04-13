package com.tsfeng.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tsfeng
 * @date 2020/4/13 2:38 下午
 */
@Slf4j
public class JobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info(shardingContexts.toString());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info(shardingContexts.toString());
    }
}
