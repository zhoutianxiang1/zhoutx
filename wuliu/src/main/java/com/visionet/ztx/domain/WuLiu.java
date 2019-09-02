package com.visionet.ztx.domain;

import java.io.Serializable;

/**
 * 物流表
 * 
 * @author zhoutx
 * @date: 2019-07-02
 */
public class WuLiu implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 物流编号 */
	private Integer wuLiuNo;
	/** 物流appSecret */
	private String appSecret;
	/** 物流appKey */
	private String appKey;
	/** 物流accessToken */
	private String accessToken;
	/** 物流serverUrl */
	private String serverUrl;
	/** 物流名称 */
	private String wuLiuName;

	public Integer getWuLiuNo() {
		return wuLiuNo;
	}

	public void setWuLiuNo(Integer wuLiuNo) {
		this.wuLiuNo = wuLiuNo;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getWuLiuName() {
		return wuLiuName;
	}

	public void setWuLiuName(String wuLiuName) {
		this.wuLiuName = wuLiuName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
