package com.visionet.ztx.service;

import com.visionet.ztx.dto.WuLiuDto;

/**
 * 物流业务接口
 * 
 * @author zhoutx
 *
 */
public interface IWuLiuService {
	WuLiuDto findByName(String wuLiuName);
}
