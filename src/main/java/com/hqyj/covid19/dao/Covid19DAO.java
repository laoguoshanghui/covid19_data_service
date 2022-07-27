package com.hqyj.covid19.dao;

import com.hqyj.covid19.entity.Covid19Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author guojing
 * @Date:2022/7/2 9:01
 */
@Repository
public interface Covid19DAO extends JpaRepository<Covid19Data,Integer> {
    @Query("select provinceName from Covid19Data  where areaType=2")
    public List<String> getProvinces();
 }
