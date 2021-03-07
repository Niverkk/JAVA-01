package info.niverkk.course14.work02.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户订单表(JOrder)实体类
 *
 * @author makejava
 * @since 2021-03-07 13:51:34
 */
public class JOrder implements Serializable {
    private static final long serialVersionUID = -48863991621552093L;
    /**
    * 订单id
    */
    private Object id;
    /**
    * 收货信息
    */
    private String harvestInfo;
    /**
    * 订单总价
    */
    private Double totalprice;
    /**
    * 订单状态，1-待付款，2-待发货，3-待收货，4-待评价，5-退款/售后
    */
    private String status;
    /**
    * 付款时间
    */
    private Date paymentTime;
    /**
    * 发货时间
    */
    private Date shipTime;
    /**
    * 成交时间
    */
    private Date dealTime;
    /**
    * 创建时间
    */
    private Date createdTime;
    /**
    * 创建人
    */
    private String createdBy;
    /**
    * 更新时间
    */
    private Date updaedTime;
    /**
    * 更新人
    */
    private String updateBy;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getHarvestInfo() {
        return harvestInfo;
    }

    public void setHarvestInfo(String harvestInfo) {
        this.harvestInfo = harvestInfo;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdaedTime() {
        return updaedTime;
    }

    public void setUpdaedTime(Date updaedTime) {
        this.updaedTime = updaedTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

}