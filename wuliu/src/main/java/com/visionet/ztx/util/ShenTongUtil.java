package com.visionet.ztx.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.visionet.ztx.domain.WuLiuDetail;

/**
 * 申通工具类
 * 
 * @author zhoutx
 * @date 2019-08-05
 */
public class ShenTongUtil {
	private static Logger log = Logger.getLogger(ShunFengUtil.class);// 输出Log日志

	// 物流接单
	public static String placeOrder(WuLiuDetail wuLiuDetail) throws Exception {
		JSONObject object = new JSONObject();
		object.put("orderType", "0");
		object.put("orderNo", wuLiuDetail.getDid());
		object.put("sender", wuLiuDetail.getSourcemanger());
		object.put("sendPhone", wuLiuDetail.getSourcephone());
		object.put("sendDate", wuLiuDetail.getDatetime2());
		object.put("sendProvince", wuLiuDetail.getSourcesheng());
		object.put("sendCity", wuLiuDetail.getSourceshi());
		object.put("sendArea", wuLiuDetail.getSourcequ());
		object.put("sendStreet", wuLiuDetail.getSourcequ());
		object.put("sendDetailAddr", wuLiuDetail.getSourceaddress());
		object.put("consignee", wuLiuDetail.getDestmanger());
		object.put("consigneePhone", wuLiuDetail.getDestphone());
		object.put("consigneeProvince", wuLiuDetail.getDestsheng());
		object.put("consigneeCity", wuLiuDetail.getDestshi());
		object.put("consigneeArea", wuLiuDetail.getDestqu());
		object.put("consigneeStreet", wuLiuDetail.getDestqu());
		object.put("consigneeDetailAddr", wuLiuDetail.getDestaddress());
		object.put("weight", wuLiuDetail.getZhongliang());
		object.put("amt", "0");
		object.put("goodsName", "服装");
		object.put("remark", wuLiuDetail.getBz());
		log.info("申通传入参数：" + object.toString());
		String postUrl = "http://waybill-get.sto-express.cn:9091/cusOrder";
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("siteName", wuLiuDetail.getHead());
		uriVariables.put("customerName", wuLiuDetail.getAccount());
		uriVariables.put("customerPwd", wuLiuDetail.getKey());
		uriVariables.put("source", "软件名称");
		uriVariables.put("orderData", object.toString());
		String md51 = Base64Md5Util.MD5(wuLiuDetail.getHead() + wuLiuDetail.getAccount() + wuLiuDetail.getKey(),
				"UTF-8");
		uriVariables.put("sign", md51);
		String res = HttpsUtil.sendPost(postUrl, uriVariables);
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(res);
		log.info("申通返回参数：" + jsonObject.toString());
		String result = null;
		if ("true".equals(jsonObject.getString("success"))) {
			String data = jsonObject.getString("data");
			com.alibaba.fastjson.JSONObject dataObject = JSON.parseObject(data);
			result = jsonObject.getString("success") + "," + dataObject.getString("wayBillNo") + ","
					+ dataObject.getString("bigWord") + " ," + dataObject.getString("packgePlace");
		} else {
			result = jsonObject.getString("success") + ", , ," + jsonObject.getString("errorMsg");
		}
		return result;
	}
}
