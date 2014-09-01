package org.hepx.jgt.showcase.service;

import org.hepx.jgt.showcase.domain.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟商品
 * @author: Koala
 * @Date: 14-8-8 下午5:04
 * @Version: 1.0
 */
public class ProductData {
    private static Map<Long,Product> products;

    private static void initProducts(){
        if(products==null){
            products=new HashMap<Long,Product>();
        }
        products.put(1L,new Product(1L,"垃圾桶","EKO创意时尚脚踏式不锈钢垃圾桶","http://www.ejushang.com/product/1389",
                "http://boss.ejushang.com/productPictures/89/1389_main_1_10.jpg",new BigDecimal(126),"1.经典款式，质量上乘； 2.内胆配置，使用方便； 3.脚踏设计，便捷实用； 4" +
                ".液压缓冲，环保静音") );
        products.put(2L,new Product(2L,"凳子","爱东爱西时尚创意凳子","http://www.ejushang.com/product/948","http://img01.ejushang.com/948_main_9_10.jpg",
                new BigDecimal(49),
                "1.高档pp胶材质，承重力更强 2.双色无缝衔接先进技术，时尚美观 3.安装方便，节省空间") );
        products.put(3L,new Product(3L,"锅","法兰西宫廷系列双耳珐琅瓷蒸锅","http://www.ejushang.com/product/1601","http://img01.ejushang.com/1601_main_1_10.jpg",
                new BigDecimal(498),
                "1、珐琅瓷抗酸抗碱，保证食物健康 2、欧式厨房时尚典范") );
        products.put(4L,new Product(4L,"锅","英国恩腾黑金系列可拆卸手柄不粘无油烟铝合金炒锅","http://www.ejushang.com/product/1258",
                "http://boss.ejushang.com/productPictures/58/1258_main_3_10.jpg",new BigDecimal(298),"不粘锅 少油烟") );
    }

    public static Product getProductById(Long id){
        if(products==null){
            initProducts();
        }
        return products.get(id);
    }

}
