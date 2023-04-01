package com.yang.lottery.domain.award.model.vo;

/**
 * @description: 实物商品送货四级地址
 * @author：杨超
 * @date: 2023/3/31
 * @Copyright：
 */
public class ShippingAddress {
    private  String name;

    /** 一级地址ID */
    private String provinceId;
    private String provinceName;

    /** 二级地址ID */
    private String cityId;
    private String cityName;

    /** 三级地址ID*/
    private String countryId;
    private String countryName;

    /** 四级地址ID*/
    private String townId;
    private String townName;

    /** 详细地址*/
    private String address;

    /** 手机号*/
    private String phone;

    /** 邮箱*/
    private String email;

    /** 备注*/
    private String remark;

    public ShippingAddress(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
