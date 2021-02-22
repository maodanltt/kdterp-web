package com.tywh.kdterp.inventory.controller;

import com.tywh.kdterp.inventory.service.ItemService;
import com.tywh.kdterp.pojo.Condition;
import com.tywh.kdterp.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/toQueryItem")
    public String toQueryItem() {
        return "item";
    }

    @RequestMapping("/queryItemList")
    public String queryItemList(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String bjbmmc = request.getParameter("bjbmmc");
        String xsbmmc = request.getParameter("xsbmmc");
        String tsfljc = request.getParameter("tsfljc");
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        Condition condition = new Condition();
        condition.setBjbmmc(bjbmmc);
        condition.setXsbmmc(xsbmmc);
        condition.setTsfljc(tsfljc);
        condition.setStartdate(startdate);
        condition.setEnddate(enddate);
        request.setAttribute("condition",condition);
        Map<String,Object> retMap = itemService.queryItem(condition);
        List<Item> itemList = (List<Item>) retMap.get("itemList");
        Integer zxscs = (Integer) retMap.get("zxscs");
        String zkczzl = (String) retMap.get("zkczzl");
        Integer zqckc = (Integer) retMap.get("zqckc");
        Integer zqmkc = (Integer) retMap.get("zqmkc");
        request.setAttribute("itemList", itemList);
        request.setAttribute("zxscs", zxscs);
        request.setAttribute("zkczzl", zkczzl);
        request.setAttribute("zqckc", zqckc);
        request.setAttribute("zqmkc", zqmkc);
        return "item";
    }
}
