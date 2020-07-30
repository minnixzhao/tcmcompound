package com.tcm.tcmcompound.service.impl;

import com.tcm.tcmcompound.dao.HerbDao;
import com.tcm.tcmcompound.dao.IngredientDao;
import com.tcm.tcmcompound.pojo.Herb;
import com.tcm.tcmcompound.pojo.HerbName;
import com.tcm.tcmcompound.pojo.Ingredient;
import com.tcm.tcmcompound.service.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HerbServiceImpl implements HerbService {
    @Autowired
    private HerbDao herbDao;
    @Autowired
    private IngredientDao ingredientDao;
    @Override
    public String getNamebyId(int id){
        HerbName herbName=herbDao.getHerbName(id);
        return herbName.getName();
    }
    @Override
    public String getIngredientsbyId(int id){
        return herbDao.getIngredients(id);
    }
    @Override
    public Herb getById(int id){
        return herbDao.findById(id);
    }
    @Override
    public Map<Integer, String> getIngredientById(Integer id){
        Map<Integer, String> allName = new LinkedHashMap<>();
        String ss=herbDao.getIngredients(id);
        if(ss==null)return allName;
        String []iids=ss.trim().split("\\s+");
        for(String item:iids){
            int iid=Integer.parseInt(item);
            String name=ingredientDao.getIngredientName(iid);
            allName.put(iid,name);
        }
        return allName;
    }
}