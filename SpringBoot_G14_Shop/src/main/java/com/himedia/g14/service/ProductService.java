package com.himedia.g14.service;

import com.himedia.g14.dao.IProductDao;
import com.himedia.g14.dto.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    IProductDao pdao;

    public HashMap<String, Object> getBestNewList() {
        HashMap<String, Object> map = new HashMap<>();

        // List<ProductVO> bList = pdao.getBestList();
        // List<ProductVO> nList = pdao.getNewList();
        // map.put("bestList", bList);
        // map.put("newList", nList);
        map.put("bestList", pdao.getBestList());
        map.put("newList", pdao.getNewList());

        return map;
    }
}
