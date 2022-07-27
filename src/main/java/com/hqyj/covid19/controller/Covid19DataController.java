package com.hqyj.covid19.controller;

import com.hqyj.covid19.dto.Covid19DataQueryDTO;
import com.hqyj.covid19.entity.Covid19Data;
import com.hqyj.covid19.service.Covid19DataService;
import com.hqyj.covid19.service.impl.Covid19DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author guojing
 * @Date:2022/7/2 14:22
 */
@RestController
@RequestMapping("/covid19")
public class Covid19DataController {
    @Autowired
    private Covid19DataService covid19DataService;
   @GetMapping("/getProvinces")
    public List<String> getProvinces(){
        return this.covid19DataService.getProvinces();
    }

    @PostMapping("/selectCovid19Data")
    public  List<Covid19Data> selectCovid19Data(@RequestBody Covid19DataQueryDTO covid19DataQueryDTO){
       return this.covid19DataService.selectCovid19Data(covid19DataQueryDTO);
    }
}
