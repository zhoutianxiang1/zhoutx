package com.visionet.ztx.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.domain.etms.TraceQueryJsf.response.get.TraceQueryResultDTO;
import com.jd.open.api.sdk.domain.etms.WaybillJosService.response.receive.PreSortResult;
import com.jd.open.api.sdk.domain.etms.WaybillJosService.response.receive.WaybillResultInfoDTO;
import com.jd.open.api.sdk.request.etms.LdopReceiveTraceGetRequest;
import com.jd.open.api.sdk.request.etms.LdopWaybillReceiveRequest;
import com.jd.open.api.sdk.response.etms.LdopReceiveTraceGetResponse;
import com.jd.open.api.sdk.response.etms.LdopWaybillReceiveResponse;
import com.visionet.ztx.domain.WuLiuDetail;
import com.visionet.ztx.dto.JingDongDto;
import com.visionet.ztx.dto.WuLiuDto;

/**
 * 京东JOS平台工具类
 * 
 * @author zhoutx
 * @date 2019-07-04
 */
public class JingDongUtil {
	private static Logger log = Logger.getLogger(ChunFengUtil.class);// 输出Log日志
	// 物流接单
	public static String placeOrder(WuLiuDetail wuLiuDetail, WuLiuDto wuLiuDto) {
		JingDongDto jingDongDto = new JingDongDto();
		jingDongDto.setOrderId(wuLiuDetail.getDid());
		jingDongDto.setSenderName(wuLiuDetail.getSourcemanger());
		jingDongDto.setSenderAddress(wuLiuDetail.getSourceaddressDetails());
		jingDongDto.setSenderTel(wuLiuDetail.getSourcephone());
		jingDongDto.setSenderPostcode(wuLiuDetail.getSourcecode());
		jingDongDto.setReceiveName(wuLiuDetail.getDestmanger());
		jingDongDto.setReceiveAddress(wuLiuDetail.getDestaddressDetails());
		jingDongDto.setProvince(wuLiuDetail.getDestsheng());
		jingDongDto.setCity(wuLiuDetail.getDestshi());
		jingDongDto.setCounty(wuLiuDetail.getDestqu());
		jingDongDto.setReceiveTel(wuLiuDetail.getDestphone());
		jingDongDto.setPostcode(wuLiuDetail.getDestcode());
		jingDongDto.setPackageCount(Integer.valueOf(wuLiuDetail.getXiangshu()));
		jingDongDto.setWeight(Double.valueOf(wuLiuDetail.getZhongliang()));
		jingDongDto.setVloumn(Double.valueOf(wuLiuDetail.getTiji()));
		jingDongDto.setSenderCompany(wuLiuDetail.getSourcedeptname());
		jingDongDto.setReceiveCompany(wuLiuDetail.getDestdeptname());
		jingDongDto.setThrOrderId(wuLiuDetail.getManual_id());
		log.info("京东传入参数：" + jingDongDto.toString());
		JdClient client = new DefaultJdClient(wuLiuDto.getServerUrl(), wuLiuDto.getAccessToken(), wuLiuDto.getAppKey(),
				wuLiuDto.getAppSecret());
		LdopWaybillReceiveRequest request = new LdopWaybillReceiveRequest();
		// 销售平台
		request.setSalePlat("0030001");
		// 商家编码
		request.setCustomerCode("021K336674");
		// 单号
		request.setOrderId(jingDongDto.getOrderId());
		// 销售平台订单号
		request.setThrOrderId(jingDongDto.getThrOrderId());
		// 寄件人姓名
		request.setSenderName(jingDongDto.getSenderName());
		// 寄件人地址
		request.setSenderAddress(jingDongDto.getSenderAddress());
		// 寄件人电话
		request.setSenderTel(jingDongDto.getSenderTel());
		// 寄件人邮编
		request.setSenderPostcode(jingDongDto.getSenderPostcode());
		// 收件人名称
		request.setReceiveName(jingDongDto.getReceiveName());
		// 收件人地址
		request.setReceiveAddress(jingDongDto.getReceiveAddress());
		// 收件人省 
		request.setProvince(jingDongDto.getProvince());
		// 收件人市 
		request.setCity(jingDongDto.getCity());
		// 收件人县
		request.setCounty(jingDongDto.getCounty());
		// 收件人电话 
		request.setReceiveTel(jingDongDto.getReceiveTel());
		// 收件人邮编
		request.setPostcode(jingDongDto.getPostcode());
		// 包裹数
		request.setPackageCount(jingDongDto.getPackageCount());
		// 重量
		request.setWeight(jingDongDto.getWeight());
		// 体积
		request.setVloumn(jingDongDto.getVloumn());
		// 寄件人公司 
		request.setSenderCompany(jingDongDto.getSenderCompany());
		// 收件人公司 
		request.setReceiveCompany(jingDongDto.getReceiveCompany());
		try {
			LdopWaybillReceiveResponse response = client.execute(request);
			WaybillResultInfoDTO result = response.getReceiveorderinfoResult();
			log.info("京东返回参数：" + result.toString());
			// 运单号
			String deliveryId = result.getDeliveryId();
			// 返回消息
			String resultMessage = result.getResultMessage();
			// 返回详细
			PreSortResult preSortResult = result.getPreSortResult();
			String sourceSortCenterName = null;
			String sourceCrossCode = null;
			String sourceTabletrolleyCode = null;
			String targetSortCenterName = null;
			String slideNo = null;
			String targetTabletrolleyCode = null;
			String siteName = null;
			String road = null;
			if (null != preSortResult) {
				// 始发分拣中心名称
				sourceSortCenterName = preSortResult.getSourceSortCenterName();
				sourceCrossCode = preSortResult.getSourceCrossCode();
				sourceTabletrolleyCode = preSortResult.getSourceTabletrolleyCode();
				// 目的分拣中心名称
				targetSortCenterName = preSortResult.getTargetSortCenterName();
				slideNo = preSortResult.getSlideNo();
				targetTabletrolleyCode = preSortResult.getTargetTabletrolleyCode();
				siteName = preSortResult.getSiteName();
				road = preSortResult.getRoad();
			}
			if (100 == result.getResultCode()) {
				return "true," + deliveryId + "," + sourceSortCenterName + "," + resultMessage + "," + sourceCrossCode
						+ "-" + sourceTabletrolleyCode + "_" + targetSortCenterName + "_" + slideNo + "-"
						+ targetTabletrolleyCode + "_" + siteName + "_" + road;
			} else {
				return "false, , ," + resultMessage;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 物流跟踪
	public static String queryLogistics(WuLiuDto wuLiuDto, String waybillCode) {
		JdClient client = new DefaultJdClient(wuLiuDto.getServerUrl(), wuLiuDto.getAccessToken(), wuLiuDto.getAppKey(),
				wuLiuDto.getAppSecret());
		LdopReceiveTraceGetRequest ldopReceiveTraceGetRequest = new LdopReceiveTraceGetRequest();
		// 商家编码
		ldopReceiveTraceGetRequest.setCustomerCode("021K336674");
		// 运单号
		ldopReceiveTraceGetRequest.setWaybillCode(waybillCode);
		try {
			LdopReceiveTraceGetResponse ldopReceiveTraceGetResponse = client.execute(ldopReceiveTraceGetRequest);
			TraceQueryResultDTO result = ldopReceiveTraceGetResponse.getQuerytraceResult();
			String jsonStr = JSONObject.toJSONString(result);
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
