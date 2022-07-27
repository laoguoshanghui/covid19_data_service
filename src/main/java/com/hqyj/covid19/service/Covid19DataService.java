package com.hqyj.covid19.service;

import com.hqyj.covid19.dto.Covid19DataQueryDTO;
import com.hqyj.covid19.entity.Covid19Data;

import java.util.List;

public interface Covid19DataService {
     /**
      * 从腾讯网获取（爬）数据
      *@Param:
      *@Return:
      */
     public List<Covid19Data> getCovid19DataFromQQ();
      /**
       * 保存爬取过来的数据
       *@Param:
       *@Return:
       */
      public void saveCovid19Data();
       /**
        * 获取省的数据
        *@Param:
        *@Return:
        */
       public List<String> getProvinces();
        /**
         * 根据查询条件检索数据
         *@Param:
         *@Return:
         */

       public  List<Covid19Data> selectCovid19Data(Covid19DataQueryDTO covid19DataQueryDTO);
}
