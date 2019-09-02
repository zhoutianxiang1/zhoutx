package com.visionet.ztx.util;

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
 * 顺丰工具类
 * 
 * @author zhoutx
 * @date 2019-07-15
 */
public class ShunFengUtil {
	private static Logger log = Logger.getLogger(ShunFengUtil.class);// 输出Log日志
	// 物流接单

	public static String placeOrder(WuLiuDetail wuLiuDetail) throws Exception {
		StringBuilder shunFengBuilder = new StringBuilder();
		shunFengBuilder.append("<Request service='OrderService' lang='zh-CN'>");
		shunFengBuilder.append("<Head>" + wuLiuDetail.getHead() + "</Head>");
		shunFengBuilder.append("<Body>");
		shunFengBuilder.append("<Order").append(" ");
		shunFengBuilder.append("orderid='" + wuLiuDetail.getDid() + "" + "'").append(" ");
		// 寄件方信息
		shunFengBuilder.append("j_company='" + wuLiuDetail.getSourcemanger() + "'").append(" ");
		shunFengBuilder.append("j_contact='" + wuLiuDetail.getSourcedeptname() + "'").append(" ");
		shunFengBuilder.append("j_tel='" + wuLiuDetail.getSourcephone() + "'").append(" ");
		shunFengBuilder.append("j_mobile='" + wuLiuDetail.getSourcephone() + "'").append(" ");
		shunFengBuilder.append("j_address='" + wuLiuDetail.getSourceaddressDetails() + "'").append(" ");
		// 收件方信息
		shunFengBuilder.append("d_destmanger='" + wuLiuDetail.getDestmanger() + "'").append(" ");
		shunFengBuilder.append("d_contact='" + wuLiuDetail.getDestdeptname() + "'").append(" ");
		shunFengBuilder.append("d_tel='" + wuLiuDetail.getDestphone() + "'").append(" ");
		shunFengBuilder.append("d_mobile='" + wuLiuDetail.getDestphone() + "'").append(" ");
		shunFengBuilder.append("d_province='" + wuLiuDetail.getDestsheng() + "'").append(" ");
		shunFengBuilder.append("d_city='" + wuLiuDetail.getDestshi() + "'").append(" ");
		shunFengBuilder.append("d_county='" + wuLiuDetail.getDestqu() + "'").append(" ");
		shunFengBuilder.append("d_address='" + wuLiuDetail.getDestaddress() + "'").append(" ");
		// 备注
		shunFengBuilder.append("custid='" + wuLiuDetail.getAccount() + "'").append(" ");
		shunFengBuilder.append("pay_method='" + wuLiuDetail.getPay_method() + "'").append(" ");
		shunFengBuilder.append("parcel_quantity='1'").append(" ");
		shunFengBuilder.append("cargo_length='1'").append(" ");
		shunFengBuilder.append("cargo_width='1'").append(" ");
		shunFengBuilder.append("cargo_height='1'").append(" ");
		shunFengBuilder.append("remark=''").append(" ");
		shunFengBuilder.append(" > ");
		// 货物信息
		shunFengBuilder.append("<Cargo").append(" ");
		shunFengBuilder.append("name='服装'").append(" ");
		shunFengBuilder.append("count=''").append(" ");
		shunFengBuilder.append("unit='件'").append(">");
		shunFengBuilder.append("</Cargo>");
		shunFengBuilder.append("</Order>");
		// 备注
		shunFengBuilder.append("<Extra").append(" ");
		shunFengBuilder.append("e1='abc'").append(" ");
		shunFengBuilder.append("e2='abc'").append(" ");
		shunFengBuilder.append("</Extra>");
		shunFengBuilder.append("</Body>");
		shunFengBuilder.append("</Request>");
		log.info("顺丰传入参数：" + shunFengBuilder.toString());
		String shunFengUrl = "http://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
		String verifyCode = shunFengBuilder.toString() + wuLiuDetail.getKey();
		MessageDigest shunFengMd5 = MessageDigest.getInstance("MD5");
		shunFengMd5.update(verifyCode.getBytes("utf8"));
		verifyCode = Base64.encode(shunFengMd5.digest());
		Map<String, String> shunFengParams = new HashMap<String, String>();
		shunFengParams.put("xml", shunFengBuilder.toString());
		shunFengParams.put("verifyCode", verifyCode);
		List<NameValuePair> shunFengList = new ArrayList<NameValuePair>();
		for (String shunFengKey : shunFengParams.keySet()) {
			shunFengList.add(new BasicNameValuePair(shunFengKey, String.valueOf(shunFengParams.get(shunFengKey))));
		}
		HttpClient shunFengClient = new SSLClient();// 创建连接and注册证书
		HttpPost shunFengPost = new HttpPost(shunFengUrl);// 设定请求方式
		UrlEncodedFormEntity shunFengUrlEncodedFormEntity = new UrlEncodedFormEntity(shunFengList, "utf-8");// 参数进行UTF-8编码
		shunFengPost.setEntity(shunFengUrlEncodedFormEntity);// 传递参数
		HttpResponse shunFengRes = shunFengClient.execute(shunFengPost);// 发送请求
		HttpEntity shunFengEntity = shunFengRes.getEntity();// 接受请请求
		String shunFengrespContent = EntityUtils.toString(shunFengEntity, "GBK");// 获取请求结果
		Map<String, String> shunFengMapResult = new HashMap<String, String>();
		Document shunFengDocument = DocumentHelper.parseText(shunFengrespContent);
		Element shunFengRoot = shunFengDocument.getRootElement();
		shunFengMapResult = XmlUtil.listNodes(shunFengRoot, shunFengMapResult);
		String shunFengResult = "";
		com.alibaba.fastjson.JSONObject shunFengResultObjet = ShunFengXmlUtil.listNodes(shunFengrespContent);
		log.info("顺丰返回参数：" + shunFengResultObjet.toString());
		if (shunFengMapResult.get("Head").equalsIgnoreCase("OK")) {
			shunFengResult = "true," + shunFengResultObjet.getString("mailno") + ","
					+ shunFengResultObjet.getString("destcode") + ", ";
		} else {
			shunFengResult = "false, , ,重复下单";
		}
		return shunFengResult;
	}
}
