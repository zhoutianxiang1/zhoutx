package com.visionet.ztx.dto;

import java.io.Serializable;

import com.visionet.ztx.domain.JingDong;

public class JingDongDto extends JingDong implements Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "JingDongDto [getSalePlat()=" + getSalePlat() + ", getCustomerCode()=" + getCustomerCode()
				+ ", getOrderId()=" + getOrderId() + ", getThrOrderId()=" + getThrOrderId() + ", getSenderName()="
				+ getSenderName() + ", getSenderAddress()=" + getSenderAddress() + ", getSenderTel()=" + getSenderTel()
				+ ", getSenderMobile()=" + getSenderMobile() + ", getSenderPostcode()=" + getSenderPostcode()
				+ ", getReceiveName()=" + getReceiveName() + ", getReceiveAddress()=" + getReceiveAddress()
				+ ", getProvince()=" + getProvince() + ", getCity()=" + getCity() + ", getCounty()=" + getCounty()
				+ ", getTown()=" + getTown() + ", getProvinceId()=" + getProvinceId() + ", getCityId()=" + getCityId()
				+ ", getCountyId()=" + getCountyId() + ", getTownId()=" + getTownId() + ", getSiteType()="
				+ getSiteType() + ", getSiteId()=" + getSiteId() + ", getSiteName()=" + getSiteName()
				+ ", getReceiveTel()=" + getReceiveTel() + ", getReceiveMobile()=" + getReceiveMobile()
				+ ", getPostcode()=" + getPostcode() + ", getPackageCount()=" + getPackageCount() + ", getWeight()="
				+ getWeight() + ", getVloumLong()=" + getVloumLong() + ", getVloumWidth()=" + getVloumWidth()
				+ ", getVloumHeight()=" + getVloumHeight() + ", getVloumn()=" + getVloumn() + ", getDescription()="
				+ getDescription() + ", getCollectionValue()=" + getCollectionValue() + ", getCollectionMoney()="
				+ getCollectionMoney() + ", getGuaranteeValue()=" + getGuaranteeValue() + ", getGuaranteeValueAmount()="
				+ getGuaranteeValueAmount() + ", getSignReturn()=" + getSignReturn() + ", getAging()=" + getAging()
				+ ", getTransType()=" + getTransType() + ", getRemark()=" + getRemark() + ", getGoodsType()="
				+ getGoodsType() + ", getOrderType()=" + getOrderType() + ", getShopCode()=" + getShopCode()
				+ ", getOrderSendTime()=" + getOrderSendTime() + ", getWarehouseCode()=" + getWarehouseCode()
				+ ", getAreaProvId()=" + getAreaProvId() + ", getAreaCityId()=" + getAreaCityId()
				+ ", getShipmentStartTime()=" + getShipmentStartTime() + ", getShipmentEndTime()="
				+ getShipmentEndTime() + ", getIdNumber()=" + getIdNumber() + ", getAddedService()=" + getAddedService()
				+ ", getExtendField1()=" + getExtendField1() + ", getExtendField2()=" + getExtendField2()
				+ ", getExtendField3()=" + getExtendField3() + ", getExtendField4()=" + getExtendField4()
				+ ", getExtendField5()=" + getExtendField5() + ", getSenderCompany()=" + getSenderCompany()
				+ ", getReceiveCompany()=" + getReceiveCompany() + ", getGoods()=" + getGoods() + ", getGoodsCount()="
				+ getGoodsCount() + ", getPromiseTimeType()=" + getPromiseTimeType() + ", getFreight()=" + getFreight()
				+ ", getPickUpStartTime()=" + getPickUpStartTime() + ", getPickUpEndTime()=" + getPickUpEndTime()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
