package com.visionet.ztx.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.visionet.ztx.dto.WuLiuDto;

/**
 * 物流mapper
 * 
 * @author zhoutx
 *
 */
@Mapper
public interface WuLiuMapper {

	WuLiuDto findByName(String wuLiuName);
}
