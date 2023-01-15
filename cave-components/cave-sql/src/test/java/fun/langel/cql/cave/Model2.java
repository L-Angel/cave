/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fun.langel.cql.cave;

import fun.langel.cql.annotation.CField;

import java.util.Date;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2023/1/15 22:05
 **/
public class Model2 {
    private static final long serialVersionUID = 6758275197398258361L;

    @CField(alias = "a_id")
    private String activityId;

    @CField(alias = "a_type")
    private Long activityType;

    @CField(alias = "a_sub_type")
    private Short subType;

    @CField(alias = "csi_title")
    private String scItemName;

    @CField(alias = "csi_sku_code")
    private String scItemId;

    @CField(alias = "a_priority")
    private String scItemProperty;

    @CField(alias = "a_bottom_limit")
    private String bottomLimit;

    @CField(alias = "a_top_limit")
    private String topLimit;

    @CField(alias = "a_status")
    private Short isEnable;

    @CField(alias = "a_gmt_modified")
    private Date gmtModify;

    @CField(alias = "a_gmt_create")
    private Date gmtCreate;

    @CField(alias = "a_modifier_id")
    private String modifierId;
//    @CField(alias = "a_feature")
//    private Map<String,String> feature;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Long getActivityType() {
        return activityType;
    }

    public void setActivityType(Long activityType) {
        this.activityType = activityType;
    }

    public Short getSubType() {
        return subType;
    }

    public void setSubType(Short subType) {
        this.subType = subType;
    }

    public String getScItemName() {
        return scItemName;
    }

    public void setScItemName(String scItemName) {
        this.scItemName = scItemName;
    }

    public String getScItemId() {
        return scItemId;
    }

    public void setScItemId(String scItemId) {
        this.scItemId = scItemId;
    }

    public String getScItemProperty() {
        return scItemProperty;
    }

    public void setScItemProperty(String scItemProperty) {
        this.scItemProperty = scItemProperty;
    }

    public String getBottomLimit() {
        return bottomLimit;
    }

    public void setBottomLimit(String bottomLimit) {
        this.bottomLimit = bottomLimit;
    }

    public String getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(String topLimit) {
        this.topLimit = topLimit;
    }

    public Short getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Short isEnable) {
        this.isEnable = isEnable;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }
}
