package com.hqyj.covid19.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hqyj.covid19.dao.Covid19DAO;
import com.hqyj.covid19.dto.Covid19DataQueryDTO;
import com.hqyj.covid19.entity.Covid19Data;
import com.hqyj.covid19.service.Covid19DataService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author guojing
 * @Date:2022/7/1 16:40
 */
@Data
@Slf4j
@Service
public class Covid19DataServiceImpl implements Covid19DataService {
    @Autowired
    private Covid19DAO covid19DAO;
    @Autowired
    private EntityManager entityManager;

    private List<Covid19Data> listJvmCovid19Data = new ArrayList<>();

    @Override
    public List<Covid19Data> getCovid19DataFromQQ() {
        //存放疫情数据列表的集合
        List<Covid19Data> resultList = new ArrayList<>();
        //拉取数据url地址
        String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=localCityNCOVDataList,diseaseh5Shelf";
        //通过httputil拉取疫情数据
        String body = HttpUtil.createPost(url).execute().body();
        //System.out.println(body);
//        将body转换成json对象
        JSONObject jsonObject = JSON.parseObject(body);
        //将areaTree数据读出来
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONObject("diseaseh5Shelf")
                .getJSONArray("areaTree");

        int sizeCountry = jsonArray.size();
        //遍历国家数据
        for (int i = 0; i < sizeCountry; i++) {
            JSONObject jsonObjectCountry = jsonArray.getJSONObject(i);
            //国家名称
            String countryName = jsonObjectCountry.getString("name");
            //省名称
            String provinceName = "";
            //市名称
            String cityName = "";
            //区域类型 1：国家 2：省  3：地区（市）
            Integer areaType = 1;

            JSONObject totalCountry = jsonObjectCountry.getJSONObject("total");
            //死亡数据
            Integer dead = totalCountry.getInteger("dead");
            //累计确诊
            Integer confirm = totalCountry.getInteger("confirm");
            //现有确诊
            Integer nowConfirm = totalCountry.getInteger("nowConfirm");
            //治愈人数
            Integer heal = totalCountry.getInteger("heal");

            Covid19Data covid19Data = new Covid19Data();
            covid19Data.setCountryName(countryName);
            covid19Data.setProvinceName(provinceName);
            covid19Data.setCityName(cityName);
            covid19Data.setAreaType(areaType);
            covid19Data.setDead(dead);
            covid19Data.setConfirm(confirm);
            covid19Data.setHeal(heal);
            covid19Data.setNowConfirm(nowConfirm);
            //把covid19Data对象放到结果集合resultList里面
            resultList.add(covid19Data);

            //遍历省的数据
            JSONArray childrenProvince = jsonObjectCountry.getJSONArray("children");
            int sizeProvince = childrenProvince.size();
            for (int j = 0; j < sizeProvince; j++) {
                JSONObject jsonObjectProvince = childrenProvince.getJSONObject(j);
                provinceName = jsonObjectProvince.getString("name");

                //区域类型 1：国家 2：省  3：地区（市）
                areaType = 2;

                JSONObject totalProvince = jsonObjectProvince.getJSONObject("total");
                //死亡数据
                dead = totalProvince.getInteger("dead");
                //累计确诊
                confirm = totalProvince.getInteger("confirm");
                //现有确诊
                nowConfirm = totalProvince.getInteger("nowConfirm");
                //治愈人数
                heal = totalProvince.getInteger("heal");

                covid19Data = new Covid19Data();
                covid19Data.setCountryName(countryName);
                covid19Data.setProvinceName(provinceName);
                covid19Data.setAreaType(areaType);
                covid19Data.setDead(dead);
                covid19Data.setConfirm(confirm);
                covid19Data.setHeal(heal);
                covid19Data.setNowConfirm(nowConfirm);
                //把covid19Data对象放到结果集合resultList里面
                resultList.add(covid19Data);

                //遍历市的数据
                JSONArray childrenCities = jsonObjectProvince.getJSONArray("children");
                int sizeCities = childrenCities.size();
                for (int n = 0; n < sizeCities; n++) {
                    JSONObject jsonObjectCity = childrenCities.getJSONObject(n);
                    cityName = jsonObjectCity.getString("name");

                    //区域类型 1：国家 2：省  3：地区（市）
                    areaType = 3;

                    JSONObject totalCity = jsonObjectCity.getJSONObject("total");
                    //死亡数据
                    dead = totalCity.getInteger("dead");
                    //累计确诊
                    confirm = totalCity.getInteger("confirm");
                    //现有确诊
                    nowConfirm = totalCity.getInteger("nowConfirm");
                    //治愈人数
                    heal = totalCity.getInteger("heal");

                    covid19Data = new Covid19Data();
                    covid19Data.setCountryName(countryName);
                    covid19Data.setProvinceName(provinceName);
                    covid19Data.setCityName(cityName);
                    covid19Data.setAreaType(areaType);
                    covid19Data.setDead(dead);
                    covid19Data.setConfirm(confirm);
                    covid19Data.setHeal(heal);
                    covid19Data.setNowConfirm(nowConfirm);
                    //把covid19Data对象放到结果集合resultList里面
                    resultList.add(covid19Data);

                }

            }

        }
//        System.out.println(resultList);
        //log.info("爬取过来的新冠数据"+resultList);

        return resultList;
    }

    @Override
    public void saveCovid19Data() {
        //1、获取爬取过来的疫情数据
        List<Covid19Data> covid19DataFromQQ = this.getCovid19DataFromQQ();
        listJvmCovid19Data = covid19DataFromQQ;
        //2、先清理表数据
        this.covid19DAO.deleteAll();
        //2、保存爬取过来的数据
        this.covid19DAO.saveAll(covid19DataFromQQ);
    }

    @Override
    public List<String> getProvinces() {
        return this.covid19DAO.getProvinces();
    }

    @Override
    public List<Covid19Data> selectCovid19Data(Covid19DataQueryDTO covid19DataQueryDTO) {
        //判断listJvmCovid9Data是否为空，如果不为空就走listJvmCovid9Data，否则查Mysql
        List resultList = null;
        if (CollectionUtil.isNotEmpty(listJvmCovid19Data)) {//执行缓存查询
            resultList = getCovid19DataFromJVM(covid19DataQueryDTO);
        } else {//执行数据库查询
            resultList = getCovid19DataFromMySQL(covid19DataQueryDTO);
        }


        return resultList;
    }

    private List getCovid19DataFromJVM(Covid19DataQueryDTO covid19DataQueryDTO) {
        //采用list.steam方式实现查询
        //先处理省名为空的情况
        List resultList;
        String provinceName = covid19DataQueryDTO.getProvinceName();
        if (StringUtils.isBlank(provinceName)) {
            resultList = listJvmCovid19Data.stream().filter(a -> a.getAreaType() == covid19DataQueryDTO.getAreaType()).collect(Collectors.toList());

        } else {
            Comparator<Covid19Data> reversed = Comparator.comparing(Covid19Data::getHeal).reversed();
            Integer orderTye = covid19DataQueryDTO.getAreaType();
            if (1 == orderTye) {
                //按治愈人数倒排
                reversed = Comparator.comparing(Covid19Data::getDead).reversed();
            }
            //按确诊人数排序
            reversed = Comparator.comparing(Covid19Data::getConfirm).reversed();
            resultList = listJvmCovid19Data.stream().filter(a -> a.getAreaType() == covid19DataQueryDTO.getAreaType() &&
                    a.getProvinceName().equals(provinceName) &&
                    !a.getCityName().equals("境外输入")).sorted(reversed).collect(Collectors.toList()).subList(0, 5);
        }
        return resultList;
    }

    private List getCovid19DataFromMySQL(Covid19DataQueryDTO covid19DataQueryDTO) {
        List resultList;
        //定义存放动态sql语句 StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM covid19_data t WHERE 1=1");
        //分两段逻辑，查询条件中的省名为空或不为空
        //areaType不为空才能构建查询条件
        if (covid19DataQueryDTO.getAreaType() != null) {
            sb.append(" and t.area_type=" + covid19DataQueryDTO.getAreaType());
        }
        String provinceName = covid19DataQueryDTO.getProvinceName();
        //判断provinceName)是否为空，省名不为空做后面三个的查询逻辑
        if (org.apache.commons.lang3.StringUtils.isNotBlank(provinceName)) {
            //非空
            sb.append(" and t.province_name='");
            sb.append(provinceName);
            sb.append("' and t.city_name !='境外输入' and t.city_name !='地区待确认' ");
            /**
             * 1、按死亡排序
             * 2、按确诊排序
             * 3、按治愈人数排序
             *@Param:
             *@Return:
             */
            Integer orderTye = covid19DataQueryDTO.getAreaType();
            String sortField = "heal";
            sortField = orderTye == 1 ? "dead" : (orderTye == 2 ? "confirm" : "heal");
            sb.append(" order by ");
            sb.append(sortField);
            sb.append(" DESC limit 5");
        }
        //发起查询
        Query query = this.entityManager.createNativeQuery(sb.toString(), Covid19Data.class);
        //获得查询结果
        resultList = query.getResultList();
        return resultList;
    }


}
