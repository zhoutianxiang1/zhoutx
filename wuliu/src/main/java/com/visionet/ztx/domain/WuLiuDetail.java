package com.visionet.ztx.domain;

/**
 * 物流详细
 * 
 * @author zhoutx
 * @date: 2019-07-29
 */
public class WuLiuDetail {
	private String company; // 物流公司 
	private String account; // 账号
	private String key; // 密匙
	private String did; // 单号
	private String mailno; // 运单号
	private String sourcemanger; // 发货人
	private String sourcedeptname; // 发货单位
	private String sourcesheng; // 发货省
	private String sourceshi; // 发货市
	private String sourcequ; // 发货区
	private String sourceaddress; // 发货地址
	private String sourcecode; // 发货方邮编
	private String sourcephone; // 发货人电话
	private String destmanger; // 收货人 
	private String destdeptname; // 收货单位
	private String destsheng; // 收货省
	private String destshi; // 收货市
	private String destqu; // 收货区
	private String destaddress; // 收货地址
	private String destcode; // 收货方邮编
	private String destphone; // 收货人电话
	private String zhongliang; // 重量
	private String xiangshu; // 包裹数
	private String tiji; // 体积 
	private String bz; // 备注
	private String manual_id; // 手工单号
	private String Head; // Head
	private String pay_method; // 支付方式 
	private String del_method; // 送货方式 
	private String sourceaddressDetails;// 发货地址明细
	private String destaddressDetails;// 收货地址明细
	private String faddress;// 发货地址省，市，区
	private String saddress;// 收货地址省，市，区
	private String datetime;// yyyyMMddHHmmss
	private String cfjson;// 春风汽运仓库参数
	private String datetime2;// yyyy-MM-dd HH:mm:ss

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getSourcemanger() {
		return sourcemanger;
	}

	public void setSourcemanger(String sourcemanger) {
		this.sourcemanger = sourcemanger;
	}

	public String getSourcedeptname() {
		return sourcedeptname;
	}

	public void setSourcedeptname(String sourcedeptname) {
		this.sourcedeptname = sourcedeptname;
	}

	public String getSourcesheng() {
		return sourcesheng;
	}

	public void setSourcesheng(String sourcesheng) {
		this.sourcesheng = sourcesheng;
	}

	public String getSourceshi() {
		return sourceshi;
	}

	public void setSourceshi(String sourceshi) {
		this.sourceshi = sourceshi;
	}

	public String getSourcequ() {
		return sourcequ;
	}

	public void setSourcequ(String sourcequ) {
		this.sourcequ = sourcequ;
	}

	public String getSourceaddress() {
		return sourceaddress;
	}

	public void setSourceaddress(String sourceaddress) {
		this.sourceaddress = sourceaddress;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public String getSourcephone() {
		return sourcephone;
	}

	public void setSourcephone(String sourcephone) {
		this.sourcephone = sourcephone;
	}

	public String getDestmanger() {
		return destmanger;
	}

	public void setDestmanger(String destmanger) {
		this.destmanger = destmanger;
	}

	public String getDestdeptname() {
		return destdeptname;
	}

	public void setDestdeptname(String destdeptname) {
		this.destdeptname = destdeptname;
	}

	public String getDestsheng() {
		return destsheng;
	}

	public void setDestsheng(String destsheng) {
		this.destsheng = destsheng;
	}

	public String getDestshi() {
		return destshi;
	}

	public void setDestshi(String destshi) {
		this.destshi = destshi;
	}

	public String getDestqu() {
		return destqu;
	}

	public void setDestqu(String destqu) {
		this.destqu = destqu;
	}

	public String getDestaddress() {
		return destaddress;
	}

	public void setDestaddress(String destaddress) {
		this.destaddress = destaddress;
	}

	public String getDestcode() {
		return destcode;
	}

	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}

	public String getDestphone() {
		return destphone;
	}

	public void setDestphone(String destphone) {
		this.destphone = destphone;
	}

	public String getZhongliang() {
		return zhongliang;
	}

	public void setZhongliang(String zhongliang) {
		this.zhongliang = zhongliang;
	}

	public String getXiangshu() {
		return xiangshu;
	}

	public void setXiangshu(String xiangshu) {
		this.xiangshu = xiangshu;
	}

	public String getTiji() {
		return tiji;
	}

	public void setTiji(String tiji) {
		this.tiji = tiji;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getManual_id() {
		return manual_id;
	}

	public void setManual_id(String manual_id) {
		this.manual_id = manual_id;
	}

	public String getHead() {
		return Head;
	}

	public void setHead(String head) {
		Head = head;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getSourceaddressDetails() {
		return sourceaddressDetails;
	}

	public void setSourceaddressDetails(String sourceaddressDetails) {
		this.sourceaddressDetails = sourceaddressDetails;
	}

	public String getDestaddressDetails() {
		return destaddressDetails;
	}

	public void setDestaddressDetails(String destaddressDetails) {
		this.destaddressDetails = destaddressDetails;
	}

	public String getFaddress() {
		return faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getCfjson() {
		return cfjson;
	}

	public void setCfjson(String cfjson) {
		this.cfjson = cfjson;
	}

	public String getDatetime2() {
		return datetime2;
	}

	public void setDatetime2(String datetime2) {
		this.datetime2 = datetime2;
	}

	public String getDel_method() {
		return del_method;
	}

	public void setDel_method(String del_method) {
		this.del_method = del_method;
	}

	public String getMailno() {
		return mailno;
	}

	public void setMailno(String mailno) {
		this.mailno = mailno;
	}

	@Override
	public String toString() {
		return "WuLiuDetail [company=" + company + ", account=" + account + ", key=" + key + ", did=" + did
				+ ", mailno=" + mailno + ", sourcemanger=" + sourcemanger + ", sourcedeptname=" + sourcedeptname
				+ ", sourcesheng=" + sourcesheng + ", sourceshi=" + sourceshi + ", sourcequ=" + sourcequ
				+ ", sourceaddress=" + sourceaddress + ", sourcecode=" + sourcecode + ", sourcephone=" + sourcephone
				+ ", destmanger=" + destmanger + ", destdeptname=" + destdeptname + ", destsheng=" + destsheng
				+ ", destshi=" + destshi + ", destqu=" + destqu + ", destaddress=" + destaddress + ", destcode="
				+ destcode + ", destphone=" + destphone + ", zhongliang=" + zhongliang + ", xiangshu=" + xiangshu
				+ ", tiji=" + tiji + ", bz=" + bz + ", manual_id=" + manual_id + ", Head=" + Head + ", pay_method="
				+ pay_method + ", del_method=" + del_method + ", sourceaddressDetails=" + sourceaddressDetails
				+ ", destaddressDetails=" + destaddressDetails + ", faddress=" + faddress + ", saddress=" + saddress
				+ ", datetime=" + datetime + ", cfjson=" + cfjson + ", datetime2=" + datetime2 + "]";
	}

}
