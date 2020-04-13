package com.tsfeng.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tsfeng
 * @date 2020/4/13 2:40 下午
 */
@Slf4j
public class MyElasticJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info(shardingContext.toString());
    }
}
