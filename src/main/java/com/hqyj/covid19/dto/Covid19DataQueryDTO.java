package com.hqyj.covid19.dto;
public class Covid19DataQueryDTO {

    /**
     * 国家名称
     */
    private String countryName;
    /**
     * 省名称
      */
    private String provinceName;
    /**
     *  市名称
     */
    private String cityName;

    /**
     * 区域类型
     * 1:国家 2:省 3：市
     */
    private Integer areaType;
    /**
     * 1：按死亡排序
     * 2：按确诊排序
     * 3：按治愈人数排序
     */
    private Integer orderType;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }
}
