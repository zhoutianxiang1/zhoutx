package com.visionet.ztx.domain;

import java.util.Date;

/**
 * 京东实体类
 *
 * @author: zhoutx
 * @date: 2019-07-02
 */
public class JingDong {
	private String salePlat; // 销售平台 
	private String customerCode; // 商家编码 
	private String orderId; // 订单号 
	private String thrOrderId; // 销售平台订单号(例如京东订单号或天猫订单号等等。总长度不要超过100。如果有多个单号，用英文,间隔。例如：7898675,7898676) 
	private String senderName; // 寄件人姓名 
	private String senderAddress; // 寄件人地址 
	private String senderTel; // 寄件人电话 
	private String senderMobile; // 寄件人手机(寄件人电话、手机至少有一个不为空) 
	private String senderPostcode; // 寄件人邮编 
	private String receiveName; // 收件人名称 
	private String receiveAddress; // 收件人地址 
	private String province; // 收件人省 
	private String city; // 收件人市 
	private String county; // 收件人县 
	private String town; // 收件人镇 
	private Integer provinceId; // 收件人省编码 
	private Integer cityId; // 收件人市编码 
	private Integer countyId; // 收件人县编码 
	private Integer townId; // 收件人镇编码 
	private Integer siteType; // 站点类型 
	private Integer siteId; // 站点编码 
	private String siteName; // 站点名称 
	private String receiveTel; // 收件人电话 
	private String receiveMobile; // 收件人手机号(收件人电话、手机至少有一个不为空) 
	private String postcode; // 收件人邮编 
	private Integer packageCount; // 包裹数(大于0，小于1000) 
	private Double weight; // 重量(单位：kg，保留小数点后两位) 
	private Integer vloumLong; // 包裹长(单位：cm,保留小数点后两位) 
	private Integer vloumWidth; // 包裹宽(单位：cm，保留小数点后两位) 
	private Integer vloumHeight; // 包裹高(单位：cm，保留小数点后两位) 
	private Double vloumn; // 体积(单位：cm3，保留小数点后两位) 
	private String description; // 商品描述 
	private Integer collectionValue; // 是否代收货款(是：1，否：0。不填或者超出范围，默认是0) 
	private Integer collectionMoney; // 代收货款金额(保留小数点后两位) 
	private Integer guaranteeValue; // 是否保价(是：1，否：0。不填或者超出范围，默认是0) 
	private Integer guaranteeValueAmount; // 保价金额(保留小数点后两位) 
	private Integer signReturn; // 签单返还(签单返还类型：0-不返单，1-普通返单，2-校验身份返单，3-电子签返单，4-电子返单+普通返单) 
	private Integer aging; // 时效(普通：1，工作日：2，非工作日：3，晚间：4。O2O一小时达：5。O2O定时达：6。不填或者超出范围，默认是1) 
	private Integer transType; // 运输类型(陆运：1，航空：2。不填或者超出范围，默认是1) 
	private String remark; // 运单备注（不超过20个字）,打印面单时备注内容也会显示在快递面单上 
	private Integer goodsType; // 配送业务类型（
								// 1:普通，3:填仓，4:特配，6:控温，7:冷藏，8:冷冻，9:深冷）默认是1 
	private Integer orderType; // 运单类型。(普通外单：0，O2O外单：1)默认为0 
	private String shopCode; // 门店编码(只O2O运单需要传，普通运单不需要传) 
	private String orderSendTime; // 预约配送时间（格式：yyyy-MM-dd HH:mm:ss。例如：2014-09-18
									// 08:30:00） 
	private String warehouseCode; // 发货仓编码 
	private Integer areaProvId; // 接货省ID 
	private Integer areaCityId; // 接货市ID 
	private Date shipmentStartTime; // 配送起始时间 
	private Date shipmentEndTime; // 配送结束时间 
	private String idNumber; // 身份证号 
	private String addedService; // 扩展服务 
	private String extendField1; // 扩展字段1 
	private String extendField2; // 扩展字段2 
	private String extendField3; // 扩展字段3 
	private Integer extendField4; // 扩展字段4 
	private Integer extendField5; // 扩展字段5 
	private String senderCompany; // 寄件人公司 
	private String receiveCompany; // 收件人公司 
	private String goods; // 托寄物名称（为避免托运物品在铁路、航空安检中被扣押，请务必下传商品类目或名称，例如：服装、3C等） 
	private Integer goodsCount; // 寄托物数量 
	private Integer promiseTimeType; // 产品类型（5：同城即日） 
	private Integer freight; // 运费 
	private Date pickUpStartTime; // 预约取件开始时间 
	private Date pickUpEndTime; // 预约取件结束时间 

	public String getSalePlat() {
		return salePlat;
	}

	public void setSalePlat(String salePlat) {
		this.salePlat = salePlat;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getThrOrderId() {
		return thrOrderId;
	}

	public void setThrOrderId(String thrOrderId) {
		this.thrOrderId = thrOrderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderTel() {
		return senderTel;
	}

	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}

	public String getSenderMobile() {
		return senderMobile;
	}

	public void setSenderMobile(String senderMobile) {
		this.senderMobile = senderMobile;
	}

	public String getSenderPostcode() {
		return senderPostcode;
	}

	public void setSenderPostcode(String senderPostcode) {
		this.senderPostcode = senderPostcode;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public Integer getTownId() {
		return townId;
	}

	public void setTownId(Integer townId) {
		this.townId = townId;
	}

	public Integer getSiteType() {
		return siteType;
	}

	public void setSiteType(Integer siteType) {
		this.siteType = siteType;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getReceiveTel() {
		return receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Integer getPackageCount() {
		return packageCount;
	}

	public void setPackageCount(Integer packageCount) {
		this.packageCount = packageCount;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getVloumLong() {
		return vloumLong;
	}

	public void setVloumLong(Integer vloumLong) {
		this.vloumLong = vloumLong;
	}

	public Integer getVloumWidth() {
		return vloumWidth;
	}

	public void setVloumWidth(Integer vloumWidth) {
		this.vloumWidth = vloumWidth;
	}

	public Integer getVloumHeight() {
		return vloumHeight;
	}

	public void setVloumHeight(Integer vloumHeight) {
		this.vloumHeight = vloumHeight;
	}

	public Double getVloumn() {
		return vloumn;
	}

	public void setVloumn(Double vloumn) {
		this.vloumn = vloumn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCollectionValue() {
		return collectionValue;
	}

	public void setCollectionValue(Integer collectionValue) {
		this.collectionValue = collectionValue;
	}

	public Integer getCollectionMoney() {
		return collectionMoney;
	}

	public void setCollectionMoney(Integer collectionMoney) {
		this.collectionMoney = collectionMoney;
	}

	public Integer getGuaranteeValue() {
		return guaranteeValue;
	}

	public void setGuaranteeValue(Integer guaranteeValue) {
		this.guaranteeValue = guaranteeValue;
	}

	public Integer getGuaranteeValueAmount() {
		return guaranteeValueAmount;
	}

	public void setGuaranteeValueAmount(Integer guaranteeValueAmount) {
		this.guaranteeValueAmount = guaranteeValueAmount;
	}

	public Integer getSignReturn() {
		return signReturn;
	}

	public void setSignReturn(Integer signReturn) {
		this.signReturn = signReturn;
	}

	public Integer getAging() {
		return aging;
	}

	public void setAging(Integer aging) {
		this.aging = aging;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getOrderSendTime() {
		return orderSendTime;
	}

	public void setOrderSendTime(String orderSendTime) {
		this.orderSendTime = orderSendTime;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public Integer getAreaProvId() {
		return areaProvId;
	}

	public void setAreaProvId(Integer areaProvId) {
		this.areaProvId = areaProvId;
	}

	public Integer getAreaCityId() {
		return areaCityId;
	}

	public void setAreaCityId(Integer areaCityId) {
		this.areaCityId = areaCityId;
	}

	public Date getShipmentStartTime() {
		return shipmentStartTime;
	}

	public void setShipmentStartTime(Date shipmentStartTime) {
		this.shipmentStartTime = shipmentStartTime;
	}

	public Date getShipmentEndTime() {
		return shipmentEndTime;
	}

	public void setShipmentEndTime(Date shipmentEndTime) {
		this.shipmentEndTime = shipmentEndTime;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddedService() {
		return addedService;
	}

	public void setAddedService(String addedService) {
		this.addedService = addedService;
	}

	public String getExtendField1() {
		return extendField1;
	}

	public void setExtendField1(String extendField1) {
		this.extendField1 = extendField1;
	}

	public String getExtendField2() {
		return extendField2;
	}

	public void setExtendField2(String extendField2) {
		this.extendField2 = extendField2;
	}

	public String getExtendField3() {
		return extendField3;
	}

	public void setExtendField3(String extendField3) {
		this.extendField3 = extendField3;
	}

	public Integer getExtendField4() {
		return extendField4;
	}

	public void setExtendField4(Integer extendField4) {
		this.extendField4 = extendField4;
	}

	public Integer getExtendField5() {
		return extendField5;
	}

	public void setExtendField5(Integer extendField5) {
		this.extendField5 = extendField5;
	}

	public String getSenderCompany() {
		return senderCompany;
	}

	public void setSenderCompany(String senderCompany) {
		this.senderCompany = senderCompany;
	}

	public String getReceiveCompany() {
		return receiveCompany;
	}

	public void setReceiveCompany(String receiveCompany) {
		this.receiveCompany = receiveCompany;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Integer getPromiseTimeType() {
		return promiseTimeType;
	}

	public void setPromiseTimeType(Integer promiseTimeType) {
		this.promiseTimeType = promiseTimeType;
	}

	public Integer getFreight() {
		return freight;
	}

	public void setFreight(Integer freight) {
		this.freight = freight;
	}

	public Date getPickUpStartTime() {
		return pickUpStartTime;
	}

	public void setPickUpStartTime(Date pickUpStartTime) {
		this.pickUpStartTime = pickUpStartTime;
	}

	public Date getPickUpEndTime() {
		return pickUpEndTime;
	}

	public void setPickUpEndTime(Date pickUpEndTime) {
		this.pickUpEndTime = pickUpEndTime;
	}

}
