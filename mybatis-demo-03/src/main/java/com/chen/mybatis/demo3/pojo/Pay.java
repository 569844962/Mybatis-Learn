package com.chen.mybatis.demo3.pojo;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author chenmingjun
 * @create 2020.3.1
 * @company
 */
public class Pay {

    private Integer payId;

    private BigDecimal price;

    private String payWay;

    private Integer blogId;


    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pay.class.getSimpleName() + "[", "]")
                .add("payId=" + payId)
                .add("price=" + price)
                .add("payWay='" + payWay + "'")
                .add("blogId=" + blogId)
                .toString();
    }
}
