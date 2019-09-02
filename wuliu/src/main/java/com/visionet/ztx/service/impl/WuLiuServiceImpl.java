package com.visionet.ztx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.visionet.ztx.dto.WuLiuDto;
import com.visionet.ztx.mapper.WuLiuMapper;
import com.visionet.ztx.service.IWuLiuService;
/**
 * 物流业务实现类
 * @author zhoutx
 *
 */
@Transactional
@Service(value = "wuLiuService")
public class WuLiuServiceImpl implements IWuLiuService {

	@Autowired
	private WuLiuMapper wuLiuMapper;
	
	@Override
	public WuLiuDto findByName(String wuLiuName) {
		System.out.println("进入serviceImpl");
		return wuLiuMapper.findByName(wuLiuName);
	}

}
