package org.hepx.jgt.showcase.domain;

import java.math.BigDecimal;

/**
 * 产品
 * @author: Koala
 * @Date: 14-8-8 下午5:08
 * @Version: 1.0
 */
public class Product {

    public Product() {
    }

    public Product(Long id, String category, String name, String url, String img, BigDecimal price, String description) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.url = url;
        this.img = img;
        this.price = price;
        this.description = description;
    }

    private Long id;

    private String category;

    private String name;

    private String url;

    private String img;

    private BigDecimal price;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
