package org.taru.lanqiao.model;

import org.taru.lanqiao.util.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class Detail {
    private String detailId;

    private String detailProductId;

    private Integer detailProductCount;

    private String detailProductUnit;

    private BigDecimal detailProductPrice;

    private BigDecimal detailTotalPrice;

    private String detailComment;

    private Byte detailStatus;

    private String detailOrderId;
    private String detailUserId;
    private Date detailDateTime;
    private  Product  product;

    public String getDetailUserId() {
        return detailUserId;
    }

    public void setDetailUserId(String detailUserId) {
        this.detailUserId = detailUserId;
    }



    public String getDetailDateTime() {
           return DateUtils.toString(detailDateTime);
    }

    public void setDetailDateTime(Date detailDateTime) {
        this.detailDateTime = detailDateTime;
    }



    public void setProduct(Product  product){
        this.product=product;
    }

    public Product  getProduct(){
        return this.product;
    }
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
    }

    public String getDetailProductId() {
        return detailProductId;
    }

    public void setDetailProductId(String detailProductId) {
        this.detailProductId = detailProductId == null ? null : detailProductId.trim();
    }

    public Integer getDetailProductCount() {
        return detailProductCount;
    }

    public void setDetailProductCount(Integer detailProductCount) {
        this.detailProductCount = detailProductCount;
    }

    public String getDetailProductUnit() {
        return detailProductUnit;
    }

    public void setDetailProductUnit(String detailProductUnit) {
        this.detailProductUnit = detailProductUnit == null ? null : detailProductUnit.trim();
    }

    public BigDecimal getDetailProductPrice() {
        return detailProductPrice;
    }

    public void setDetailProductPrice(BigDecimal detailProductPrice) {
        this.detailProductPrice = detailProductPrice;
    }

    public BigDecimal getDetailTotalPrice() {
        return detailTotalPrice;
    }

    public void setDetailTotalPrice(BigDecimal detailTotalPrice) {
        this.detailTotalPrice = detailTotalPrice;
    }

    public String getDetailComment() {
        return detailComment;
    }

    public void setDetailComment(String detailComment) {
        this.detailComment = detailComment == null ? null : detailComment.trim();
    }

    public Byte getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(Byte detailStatus) {
        this.detailStatus = detailStatus;
    }

    public String getDetailOrderId() {
        return detailOrderId;
    }

    public void setDetailOrderId(String detailOrderId) {
        this.detailOrderId = detailOrderId == null ? null : detailOrderId.trim();
    }
}