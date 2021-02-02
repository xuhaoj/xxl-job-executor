package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author jack xu
 */
@Component
public class ShardingJobHandler {
    private static Logger logger = LoggerFactory.getLogger(ShardingJobHandler.class);

    /**
     * 分片广播任务
     */
    @XxlJob("shardingJobHandler")
    public ReturnT<String> shardingJobHandler(String param) throws Exception {

        // 分片参数，包括总分片数（执行器个数），当前分片序号
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());
        //sql
        String sql = "select id,name,password from student where status=0 and mod(id,"
                + shardingVO.getTotal() + ")=" + shardingVO.getIndex() + " order by id desc limit 1000;";
        //List<Student> list = getStudent(sql);
        //获取到这台机器需要执行的数据，执行业务逻辑
        // TODO:
        return ReturnT.SUCCESS;
    }

}