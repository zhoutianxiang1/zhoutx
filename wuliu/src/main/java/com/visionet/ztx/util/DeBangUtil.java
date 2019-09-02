package com.visionet.ztx.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.visionet.ztx.domain.WuLiuDetail;

/**
 * 德邦快递工具类
 * 
 * @author zhoutx
 * @date 2019-07-15
 */
public class DeBangUtil {
	private static Logger log = Logger.getLogger(DeBangUtil.class);// 输出Log日志
	// 物流接单

	public static String placeOrder(WuLiuDetail wuLiuDetail) throws Exception {
		JSONObject deBangJsonObject = new JSONObject();
		JSONObject receiverObject = new JSONObject();
		receiverObject.put("address", wuLiuDetail.getDestaddressDetails());
		receiverObject.put("city", wuLiuDetail.getDestshi());
		receiverObject.put("county", wuLiuDetail.getDestqu());
		receiverObject.put("mobile", wuLiuDetail.getDestphone());
		receiverObject.put("name", wuLiuDetail.getDestmanger());
		receiverObject.put("phone", wuLiuDetail.getDestphone());
		receiverObject.put("province", wuLiuDetail.getDestsheng());
		JSONObject senderObject = new JSONObject();
		senderObject.put("address", wuLiuDetail.getSourceaddressDetails());
		senderObject.put("city", wuLiuDetail.getSourceshi());
		senderObject.put("county", wuLiuDetail.getSourcequ());
		senderObject.put("mobile", wuLiuDetail.getSourcephone());
		senderObject.put("name", wuLiuDetail.getSourcemanger());
		senderObject.put("phone", wuLiuDetail.getSourcephone());
		senderObject.put("province", wuLiuDetail.getSourcesheng());
		deBangJsonObject.put("backSignBill", "0");
		deBangJsonObject.put("businessNetworkNo", "0");
		deBangJsonObject.put("cargoName", "服装");
		deBangJsonObject.put("codType", "1");
		deBangJsonObject.put("codValue", "0");
		deBangJsonObject.put("customerCode", wuLiuDetail.getAccount());
		deBangJsonObject.put("customerID", "TONGRUIFUSH");
		deBangJsonObject.put("deliveryType", "1");
		deBangJsonObject.put("insuranceValue", "1");
		deBangJsonObject.put("logisticCompanyID", "DEPPON");
		deBangJsonObject.put("orderSource", "TONGRUIFUSHI");
		deBangJsonObject.put("logisticID", wuLiuDetail.getDid());
		deBangJsonObject.put("serviceType", "2");
		if ("2".equals(wuLiuDetail.getPay_method())) {
			wuLiuDetail.setPay_method("1");
		} else if ("1".equals(wuLiuDetail.getPay_method())) {
			wuLiuDetail.setPay_method("2");
		}
		deBangJsonObject.put("payType", wuLiuDetail.getPay_method());
		deBangJsonObject.put("gmtCommit", wuLiuDetail.getDatetime2());
		deBangJsonObject.put("sieveOrder", "Y");
		deBangJsonObject.put("receiver", receiverObject);
		deBangJsonObject.put("sender", senderObject);
		deBangJsonObject.put("smsNotify", "N");
		deBangJsonObject.put("transportType", "PACKAGE");
		deBangJsonObject.put("vistReceive", "Y");
		deBangJsonObject.put("isOut", "Y");
		log.info("德邦传入参数：" + deBangJsonObject.toString());
		String Url = "http://dpapi.deppon.com/dop-interface-sync/dop-standard-ewborder/expressSyncNewSieveOrder.action";
		long timestamp = Calendar.getInstance().getTimeInMillis();
		String digest = deBangJsonObject.toString() + wuLiuDetail.getKey() + timestamp;
		digest = Base64Md5Util.MD5(digest, "UTF-8");
		digest = Base64Md5Util.base64(digest, "UTF-8");
		Map<String, String> deBangParams = new HashMap<String, String>();
		deBangParams.put("companyCode", "TONGRUIFUSHI");
		deBangParams.put("params", deBangJsonObject.toString());
		deBangParams.put("digest", digest);
		deBangParams.put("timestamp", String.valueOf(timestamp));
		List<NameValuePair> deBangList = new ArrayList<NameValuePair>();
		for (String deBangKey : deBangParams.keySet()) {
			deBangList.add(new BasicNameValuePair(deBangKey, String.valueOf(deBangParams.get(deBangKey))));
		}
		HttpClient deBangClient = new SSLClient();
		HttpPost deBangPost = new HttpPost(Url);
		UrlEncodedFormEntity deBangUrlEncodedFormEntity = new UrlEncodedFormEntity(deBangList, "UTF-8");
		deBangPost.setEntity(deBangUrlEncodedFormEntity);
		HttpResponse deBangRes = deBangClient.execute(deBangPost);
		HttpEntity deBangEntity = deBangRes.getEntity();
		String deBangResult = EntityUtils.toString(deBangEntity, "GBK").trim();
		com.alibaba.fastjson.JSONObject deBangResultObject = com.alibaba.fastjson.JSONObject.parseObject(deBangResult);
		log.info("德邦返回：" + deBangResultObject.toString());
		if (deBangResultObject.getString("result").equalsIgnoreCase("true")) {
			deBangResult = deBangResultObject.getString("result").trim() + ","
					+ deBangResultObject.getString("mailNo").trim() + ", ,"
					+ deBangResultObject.getString("reason").trim();
		} else if (deBangResultObject.getString("result").equalsIgnoreCase("false")) {
			deBangResult = deBangResultObject.getString("result").trim() + ", , ,"
					+ deBangResultObject.getString("reason").trim();
		}
		return deBangResult;
	}

	// 物流接单
	public static String ldPlaceOrder(WuLiuDetail wuLiuDetail) throws Exception {
		JSONObject deBangLDJsonObject = new JSONObject();
		JSONObject receiverLDObject = new JSONObject();
		receiverLDObject.put("address", wuLiuDetail.getDestaddressDetails());
		receiverLDObject.put("city", wuLiuDetail.getDestshi());
		receiverLDObject.put("county", wuLiuDetail.getDestqu());
		receiverLDObject.put("phone", wuLiuDetail.getDestphone());
		receiverLDObject.put("name", wuLiuDetail.getDestmanger());
		receiverLDObject.put("province", wuLiuDetail.getDestsheng());
		JSONObject senderLDObject = new JSONObject();
		senderLDObject.put("address", wuLiuDetail.getSourceaddressDetails());
		senderLDObject.put("city", wuLiuDetail.getSourceshi());
		senderLDObject.put("county", wuLiuDetail.getSourcequ());
		senderLDObject.put("name", wuLiuDetail.getSourcemanger());
		senderLDObject.put("phone", wuLiuDetail.getSourcephone());
		senderLDObject.put("province", wuLiuDetail.getSourcesheng());
		deBangLDJsonObject.put("receiver", receiverLDObject);
		deBangLDJsonObject.put("sender", senderLDObject);
		deBangLDJsonObject.put("logisticID", "TEVW" + wuLiuDetail.getDid());
		deBangLDJsonObject.put("orderSource", "EWBSHRGFSYXGS");
		deBangLDJsonObject.put("cargoName", "服装");
		deBangLDJsonObject.put("totalNumber", Integer.parseInt(wuLiuDetail.getXiangshu()));
		deBangLDJsonObject.put("orderType", 1);
		deBangLDJsonObject.put("totalWeight", Integer.parseInt(wuLiuDetail.getZhongliang()));
		deBangLDJsonObject.put("totalVolume", Integer.parseInt(wuLiuDetail.getTiji()));
		if ("2".equals(wuLiuDetail.getPay_method())) {
			wuLiuDetail.setPay_method("1");
		} else if ("1".equals(wuLiuDetail.getPay_method())) {
			wuLiuDetail.setPay_method("2");
		}
		deBangLDJsonObject.put("payType", wuLiuDetail.getPay_method());
		//运输方式1.卡航,2.汽运
		deBangLDJsonObject.put("transportType", "1");
		//保价金额
		deBangLDJsonObject.put("insuranceValue", "3000");
		deBangLDJsonObject.put("codType", "1");
		deBangLDJsonObject.put("codValue", "0");
		deBangLDJsonObject.put("vistReceive", "Y");
		deBangLDJsonObject.put("serviceType", "2");
		deBangLDJsonObject.put("packageService", "纸箱");
		deBangLDJsonObject.put("customerCode", wuLiuDetail.getAccount());
		deBangLDJsonObject.put("customerID", "EWBSHRGFSYXGS");
		deBangLDJsonObject.put("gmtCommit", wuLiuDetail.getDatetime2());
		//送货方式
		deBangLDJsonObject.put("deliveryType", wuLiuDetail.getDel_method());
		deBangLDJsonObject.put("logisticCompanyID", "DEPPON");
		deBangLDJsonObject.put("is_partial_line", "Y");
		log.info("德邦零担入参：" + deBangLDJsonObject.toString());
		String Url = "http://dpapi.deppon.com/dop-interface-sync/dop-standard-ewborder/jiumuLtlNewOrder.action";
		long timestamp = Calendar.getInstance().getTimeInMillis();
		String digest = deBangLDJsonObject.toString() + wuLiuDetail.getKey() + timestamp;
		digest = Base64Md5Util.MD5(digest, "UTF-8");
		digest = Base64Md5Util.base64(digest, "UTF-8");
		Map<String, String> deBangParams = new HashMap<String, String>();
		// 月结账号
		deBangParams.put("companyCode", "EWBSHRGFSYXGS");
		deBangParams.put("params", deBangLDJsonObject.toString());
		deBangParams.put("digest", digest);
		deBangParams.put("timestamp", String.valueOf(timestamp));
		List<NameValuePair> deBangList = new ArrayList<NameValuePair>();
		for (String deBangKey : deBangParams.keySet()) {
			deBangList.add(new BasicNameValuePair(deBangKey, String.valueOf(deBangParams.get(deBangKey))));
		}
		HttpClient deBangClient = new SSLClient();
		HttpPost deBangPost = new HttpPost(Url);
		UrlEncodedFormEntity deBangUrlEncodedFormEntity = new UrlEncodedFormEntity(deBangList, "UTF-8");
		deBangPost.setEntity(deBangUrlEncodedFormEntity);
		HttpResponse deBangRes = deBangClient.execute(deBangPost);
		HttpEntity deBangEntity = deBangRes.getEntity();
		String deBangResult = EntityUtils.toString(deBangEntity, "GBK").trim();
		com.alibaba.fastjson.JSONObject deBangResultObject = com.alibaba.fastjson.JSONObject.parseObject(deBangResult);
		log.info("德邦零担返回参数：" + deBangResultObject.toString());
		if (deBangResultObject.getString("result").equalsIgnoreCase("true")) {
			deBangResult = deBangResultObject.getString("result")+ ","
					+ deBangResultObject.getString("mailNo")+ ","
					+ deBangResultObject.getString("stationNo")+ ","
					+ deBangResultObject.getString("reason")+ ","
					+ deBangResultObject.getString("arrivedOutFieldName")+ "-"
					+ deBangResultObject.getString("arrivedOrgSimpleName");
		} else if (deBangResultObject.getString("result").equalsIgnoreCase("false")) {
			deBangResult = deBangResultObject.getString("result")+ ", , ,"
					+ deBangResultObject.getString("reason");
		}
		return deBangResult;
	}
}
