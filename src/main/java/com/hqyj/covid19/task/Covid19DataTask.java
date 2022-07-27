package com.hqyj.covid19.task;

import com.hqyj.covid19.service.Covid19DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class Covid19DataTask {
@Autowired
    private Covid19DataService covid19DataService;

    /**
     * 每1分钟爬取一次数据
     * 格式：秒 分 小时 日期 月份 星期几（0或7表示星期天）
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void getDataFromQQ() {
        covid19DataService.saveCovid19Data();
        log.info("每1分钟爬取一次数据");
    }
}
