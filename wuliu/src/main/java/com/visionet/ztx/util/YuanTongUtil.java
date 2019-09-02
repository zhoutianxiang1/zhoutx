package com.visionet.ztx.util;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.visionet.ztx.domain.WuLiuDetail;

/**
 * 圆通快递工具类
 * 
 * @author zhoutx
 * @date 2019-07-15
 */
public class YuanTongUtil {
	private static Logger log = Logger.getLogger(YuanTongUtil.class);// 输出Log日志
	// 物流接单

	public static String placeOrder(WuLiuDetail wuLiuDetail) throws Exception {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<RequestOrder>");
		strBuilder.append("<clientID>" + wuLiuDetail.getAccount() + "</clientID>");
		strBuilder.append("<logisticProviderID>YTO</logisticProviderID>");
		strBuilder.append("<customerId>" + wuLiuDetail.getAccount() + "</customerId>");
		strBuilder.append("<txLogisticID>" + wuLiuDetail.getDid() + "</txLogisticID>");
		strBuilder.append("<tradeNo>1</tradeNo>");
		strBuilder.append("<totalServiceFee>1</totalServiceFee>");
		strBuilder.append("<codSplitFee>1</codSplitFee>");
		strBuilder.append("<orderType>1</orderType>");
		strBuilder.append("<serviceType>1</serviceType>");
		strBuilder.append("<flag>1</flag>");
		/** 发件人信息 */
		strBuilder.append("<sender>");
		strBuilder.append("<name>" + wuLiuDetail.getSourcemanger() + "</name>");
		strBuilder.append("<phone>" + wuLiuDetail.getSourcephone() + "</phone>");
		strBuilder.append("<mobile>" + wuLiuDetail.getSourcephone() + "</mobile>");
		strBuilder.append("<prov>" + wuLiuDetail.getSourcesheng() + "</prov>");
		strBuilder.append("<city>" + wuLiuDetail.getSourceshi() + "</city>");
		strBuilder.append("<address>" + wuLiuDetail.getSourceaddressDetails() + "</address>");
		strBuilder.append("</sender>");
		/** 收件人信息 */
		strBuilder.append("<receiver>");
		strBuilder.append("<name>" + wuLiuDetail.getDestmanger() + "</name>");
		strBuilder.append("<phone>" + wuLiuDetail.getDestphone() + "</phone>");
		strBuilder.append("<mobile>" + wuLiuDetail.getDestphone() + "</mobile>");
		strBuilder.append("<prov>" + wuLiuDetail.getDestsheng() + "</prov>");
		strBuilder.append("<city>" + wuLiuDetail.getDestshi() + "</city>");
		strBuilder.append("<address>" + wuLiuDetail.getDestaddress() + "</address>");
		strBuilder.append("</receiver>");
		strBuilder.append("<sendStartTime>" + wuLiuDetail.getDatetime2() + "</sendStartTime>");
		strBuilder.append("<sendEndTime>" + wuLiuDetail.getDatetime2() + "</sendEndTime>");
		strBuilder.append("<goodsValue>1</goodsValue>");
		strBuilder.append("<items>");
		strBuilder.append("<item>");
		strBuilder.append("<itemName>服装</itemName>");
		strBuilder.append("<number>1</number>");
		strBuilder.append("<itemValue>1</itemValue>");
		strBuilder.append("</item>");
		strBuilder.append("</items>");
		strBuilder.append("<insuranceValue>1</insuranceValue>");
		strBuilder.append("<special>1</special>");
		strBuilder.append("<remark>1</remark>");
		strBuilder.append("</RequestOrder>");
		log.info("圆通传入参数：" + strBuilder.toString());
		String yuanTongUrl = "http://customerewms.yto.net.cn/CommonOrderModeBPlusServlet.action";
		String data_digest = strBuilder.toString() + wuLiuDetail.getKey();
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(data_digest.getBytes("utf8"));
		data_digest = Base64.encode(messageDigest.digest());
		data_digest = URLEncoder.encode(data_digest);
		Map<String, String> yuanTongParams = new HashMap<String, String>();
		yuanTongParams.put("logistics_interface", strBuilder.toString());
		yuanTongParams.put("data_digest", data_digest);
		yuanTongParams.put("type", strBuilder.toString());
		yuanTongParams.put("clientId", wuLiuDetail.getAccount());
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (String yuanTongkey : yuanTongParams.keySet()) {
			list.add(new BasicNameValuePair(yuanTongkey, String.valueOf(yuanTongParams.get(yuanTongkey))));
		}
		HttpClient client = new SSLClient();// 创建连接and注册证书
		HttpPost post = new HttpPost(yuanTongUrl);// 设定请求方式
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");// 参数进行UTF-8编码
		post.setEntity(urlEncodedFormEntity);// 传递参数
		HttpResponse res = client.execute(post);// 发送请求
		HttpEntity entity = res.getEntity();// 接受请请求
		String respContent = EntityUtils.toString(entity, "GBK").trim();// 获取请求结果
		Document yuanTongdocument = DocumentHelper.parseText(respContent);
		Element yuanTongroot = yuanTongdocument.getRootElement();
		Map<String, String> yuanTongResult = new HashMap<String, String>();
		yuanTongResult = XmlUtil.listNodes(yuanTongroot, yuanTongResult);
		String ytResult = "";
		log.info("圆通返回参数：" + yuanTongResult.toString());
		if (yuanTongResult.get("code").equalsIgnoreCase("200")) {
			if(null!=yuanTongResult.get("shortAddress")) {
			ytResult = "true," + yuanTongResult.get("mailNo").toString().trim() + ","
					+ yuanTongResult.get("shortAddress").toString() + ", ";
			}else {
				ytResult = "true," + yuanTongResult.get("mailNo").toString().trim() + ", , ";
			}
		} else {
			ytResult = "false, , , ";
		}
		return ytResult;
	}
}
