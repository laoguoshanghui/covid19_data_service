package com.hqyj.covid19.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "covid19_data")
public class Covid19Data implements Serializable {
    /**
     * 主键ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    /**
     * 国家名称
     */
    @Column(name = "country_name")
    private String countryName;
    /**
     * 省名称
      */
    @Column(name = "province_name")
    private String provinceName;
    /**
     *  市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 死亡人数
     */
    @Column(name = "dead")
    private Integer dead;

    /**
     * 累计确诊人数
     */
    @Column(name = "confirm")
    private Integer confirm;

    /**
     * 现存确诊人数
     */
    @Column(name = "now_onfirm")
    private Integer nowConfirm;

    /**
     *
     */
    @Column(name = "heal")
    private Integer heal;

    /**
     * 区域类型
     * 1:国家 2:省 3：市
     */
    @Column(name = "area_type")
    private Integer areaType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getNowConfirm() {
        return nowConfirm;
    }

    public void setNowConfirm(Integer nowConfirm) {
        this.nowConfirm = nowConfirm;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    @Override
    public String toString() {
        return "Covid19Data{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", dead=" + dead +
                ", confirm=" + confirm +
                ", nowConfirm=" + nowConfirm +
                ", heal=" + heal +
                ", areaType=" + areaType +
                '}';
    }
}
