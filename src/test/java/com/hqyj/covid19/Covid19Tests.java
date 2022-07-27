package com.hqyj.covid19;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guojing
 * @Date:2022/7/1 15:18
 */
@SpringBootTest

@Component
public class Covid19Tests {
    @Test
    public void josn() {
        String json = "{\n" +
                "  \"provinces\": [\n" +
                "    {\n" +
                "      \"province\": \"广东\",\n" +
                "      \"confirm\": 2000,\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"province\": \"深圳\",\n" +
                "          \"confirm\": 1000\n" +
                "        },\n" +
                "        {\n" +
                "          \"province\": \"广州\",\n" +
                "          \"confirm\": 300\n" +
                "        },\n" +
                "        {\n" +
                "          \"province\": \"珠海\",\n" +
                "          \"confirm\": 50\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"province\": \"广西\",\n" +
                "      \"confirm\": 1200,\n" +
                "      \"children\": [\n" +
                "        \n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"province\": \"湖南\",\n" +
                "      \"confirm\": 1000,\n" +
                "      \"children\": [\n" +
                "        \n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray provinces = jsonObject.getJSONArray("provinces");


        JSONObject province = provinces.getJSONObject(0);
        JSONArray children = province.getJSONArray("children");
        int size = children.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject1 = children.getJSONObject(i);
            String province1 = jsonObject1.getString("province");
            Integer confirm1 = jsonObject1.getInteger("confirm");

            System.out.println(province1 + confirm1);

        }

    }

    // 这个cron表达式代表从0秒开始，每过3秒执行一次。
    @Scheduled(cron = "0/3 * * * * ?")
    @Test
    public void simpleDate() {

        // 大家可以根据公司的业务来实现，更希望是@Autowrite注入service层的接口，业务逻辑写在service层中。
        // 这里测试我就输出当前系统时间了...
        Date date = new Date(System.currentTimeMillis());
        String rule = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(rule);
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }


}
