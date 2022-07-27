package com.hqyj.covid19;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hqyj.covid19.service.Covid19DataService;
import org.apache.logging.log4j.util.StringBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class Covid19ApplicationTests {
	@Autowired
private Covid19DataService covid19DataService;

	@Test
	public void testGetCovid19DataFromTencent(){
         this.covid19DataService.getCovid19DataFromQQ();
	}

	@Test
	public void josn(){
		String json ="{\n" +
				"  \"provinces\": [\n" +
				"    {\n" +
				"      \"province\": \"广东\",\n" +
				"      \"confirm\": 2000\n" +
				"    },\n" +
				"    {\n" +
				"      \"province\": \"广西\",\n" +
				"      \"confirm\": 1200\n" +
				"    },\n" +
				"    {\n" +
				"      \"province\": \"湖南\",\n" +
				"      \"confirm\": 1000\n" +
				"    }\n" +
				"  ]\n" +
				"}";

		JSONObject jsonObject = JSON.parseObject(json);
		JSONArray provinces = jsonObject.getJSONArray("provinces");

		//遍历provinces  json数组
		int size = provinces.size();
		for(int i = 0;i < size;i++){
			JSONObject province = provinces.getJSONObject(i);

			String provinceName = province.getString("province");
			Integer confirm = province.getInteger("confirm");

			System.out.println("省名："+provinceName + ",确诊人数："+confirm);

		}



	}
	@Test
	public void testGetCovid19Data(){
		this.covid19DataService.saveCovid19Data();
	}

	@Test
	public void testString(){
/*		String s1="I";
		String s2=" am";
		String s3=s1+s2;
		System.out.println(s3);*/

		StringBuilder sb=new StringBuilder();
		sb.append("I");
		sb.append(" am");
		System.out.println(sb.toString());
	}
}
