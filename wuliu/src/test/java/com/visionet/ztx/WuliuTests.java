package com.visionet.ztx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.visionet.ztx.util.Base64Md5Util;
import com.visionet.ztx.util.HttpsUtil;
import com.visionet.ztx.util.SSLClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WuliuTests {

	@Test
	public void DeBangTest() throws Exception {
		Date date = new Date();
		SimpleDateFormat deBangLDformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject deBangLDJsonObject = new JSONObject();
		JSONObject receiverLDObject = new JSONObject();
		receiverLDObject.put("address", "上海市浦东新区414路58号102");
		receiverLDObject.put("city", "上海市");
		receiverLDObject.put("county", "浦东新区");
		receiverLDObject.put("phone", "40023232");
		receiverLDObject.put("name", "彭博");
		receiverLDObject.put("province", "上海");
		JSONObject senderLDObject = new JSONObject();
		senderLDObject.put("address", "上海市普陀区22路258号102");
		senderLDObject.put("city", "上海市");
		senderLDObject.put("county", "普陀区");
		senderLDObject.put("name", "是商");
		senderLDObject.put("phone", "15832323232");
		senderLDObject.put("province", "上海");
		deBangLDJsonObject.put("receiver", receiverLDObject);
		deBangLDJsonObject.put("sender", senderLDObject);
		deBangLDJsonObject.put("logisticID", "TEVW111111");
		deBangLDJsonObject.put("orderSource", "EWBSHRGFSYXGS");
		deBangLDJsonObject.put("cargoName", "商品名称测试");
		deBangLDJsonObject.put("totalNumber", 1);
		deBangLDJsonObject.put("orderType", 1);
		deBangLDJsonObject.put("totalWeight", 1);
		deBangLDJsonObject.put("totalVolume", 15);
		deBangLDJsonObject.put("payType", "2");
		deBangLDJsonObject.put("transportType", "20");
		deBangLDJsonObject.put("insuranceValue", "2000");
		deBangLDJsonObject.put("codType", "1");
		deBangLDJsonObject.put("codValue", "0");
		deBangLDJsonObject.put("vistReceive", "N");
		deBangLDJsonObject.put("serviceType", "2");
		deBangLDJsonObject.put("packageService", "纸箱");
		deBangLDJsonObject.put("customerCode", "EWBSHRGFSYXGS");
		deBangLDJsonObject.put("customerID", "EWBSHRGFSYXGS");
		deBangLDJsonObject.put("gmtCommit", deBangLDformat.format(date));
		deBangLDJsonObject.put("deliveryType", "4");
		deBangLDJsonObject.put("logisticCompanyID", "DEPPON");
		deBangLDJsonObject.put("is_partial_line", "N");
		String Url = "http://dpsanbox.deppon.com/sandbox-web/dop-standard-ewborder/jiumuLtl.action";
		long timestamp = Calendar.getInstance().getTimeInMillis();
		String digest = deBangLDJsonObject.toString() + "ceca05fd318238fea8e73d9d5dc0769f" + timestamp;
		digest = Base64Md5Util.MD5(digest, "UTF-8");
		digest = Base64Md5Util.base64(digest, "UTF-8");
		Map<String, String> deBangParams = new HashMap<String, String>();
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
		System.out.println(deBangResultObject.toString());
		if (deBangResultObject.getString("result").equalsIgnoreCase("true")) {
			deBangResult = deBangResultObject.getString("result").trim() + ","
					+ deBangResultObject.getString("mailNo").trim() + ", ,"
					+ deBangResultObject.getString("reason").trim();
		} else if (deBangResultObject.getString("result").equalsIgnoreCase("false")) {
			deBangResult = deBangResultObject.getString("result").trim() + ", , ,"
					+ deBangResultObject.getString("reason").trim();
		}
	}

	@Test
	public void KuaYueTest() throws Exception {
		String Url = "https://openapi.ky-express.com/kyeopenapi/CustomerWaybillPrint";
		String uuid = "02118925565";
		String key = "20FC8C6AE52C335B9FB86690CCBEFDAA";
		String accesskey = "4B383A74A3FE6286E0262F1104220E14";
		String kye = "10269";
		try {
			com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
			jsonObject.put("uuid", uuid);
			jsonObject.put("key", key);
			jsonObject.put("col_018", "当天达");
			jsonObject.put("col_001", "寄件公司");
			jsonObject.put("col_004", "寄件联系人");
			jsonObject.put("col_005", "18379055021");
			jsonObject.put("col_002", "寄件地址");
			jsonObject.put("col_006", "收件公司");
			jsonObject.put("col_010", "收件联系人");
			jsonObject.put("col_007", "收件地址");
			jsonObject.put("col_009", "17196697199");
			jsonObject.put("col_037", "02118925565");
			jsonObject.put("col_013", "到付");
			jsonObject.put("col_019", "托寄物");
			jsonObject.put("col_031", "0");
			jsonObject.put("col_028", "0");
			jsonObject.put("vipshopCode", "唯品会");
			String SendObject = jsonObject.toString();
			Iterator<Entry<String, Object>> it = jsonObject.entrySet().iterator();
			while (it.hasNext()){
			    Map.Entry<String, Object> temp = it.next();
			    if (temp.getValue()==null||temp.getValue().toString().length()<1){
			        it.remove();
			    }
			}
			String token = com.alibaba.fastjson.JSONObject.toJSONString(jsonObject.toString(), SerializerFeature.SortField);
			token = com.alibaba.fastjson.JSONObject.parse(token).toString();
			token = token.replace("{", "").replace("\"", "").replace(":", "").
					replace(",", "").replace("}", "");
			token = accesskey+token;
			token = Base64Md5Util.MD5(token, "UTF-8").toUpperCase();
			HttpClient client = new SSLClient();
			HttpPost post = new HttpPost(Url);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("kye", kye);
			post.setHeader("access-token", token);
			StringEntity SendEntity = new StringEntity(SendObject.toString(), "UTF-8");
			post.setEntity(SendEntity);
			HttpResponse res = client.execute(post);
			HttpEntity entity = res.getEntity();
			String Result = EntityUtils.toString(entity,"GBK").trim();
			System.out.println(Result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 客户余额查询接口
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCusBalanceTest() throws Exception {
		String siteName = "山东德州公司";
		String customerName = "中合服饰";
		String customerPwd = "123456";
		String postUrl = "http://waybill-apply.sto-express.cn:9091/getCusBalance";
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("siteName", siteName);
		uriVariables.put("customerName", customerName);
		uriVariables.put("customerPwd", customerPwd);
		String md5 = Base64Md5Util.MD5(siteName + customerName + customerPwd, "UTF-8");
		uriVariables.put("sign", md5);
		String res = HttpsUtil.sendPost(postUrl, uriVariables);
		System.out.println(res);
	}

	/**
	 * 客户账号密码校验接口
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkCusPwdTest() throws Exception {
		String siteName = "山东德州公司";
		String customerName = "中合服饰";
		String customerPwd = "123456";
		String postUrl = "http://waybill-apply.sto-express.cn:9091/checkCusPwd";
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("siteName", siteName);
		uriVariables.put("customerName", customerName);
		uriVariables.put("customerPwd", customerPwd);
		String md5 = Base64Md5Util.MD5(siteName + customerName + customerPwd, "UTF-8");
		uriVariables.put("sign", md5);
		String res = HttpsUtil.sendPost(postUrl, uriVariables);
		System.out.println(res);
	}

	/**
	 * 订单上传接口
	 * 
	 * @throws Exception
	 */
	@Test
	public void cusOrderTest() throws Exception {
		String siteName = "山东德州公司";
		String customerName = "中合服饰";
		String customerPwd = "123456";
		JSONObject object = new JSONObject();
		object.put("orderType", "0");
		object.put("orderNo", "123132");
		object.put("sender", "中合服饰");
		object.put("sendPhone", "13662625320");
		object.put("sendDate", "2018-02-23 09:43:12");
		object.put("sendProvince", "湖南省");
		object.put("sendCity", "郴州市");
		object.put("sendArea", "永兴县");
		object.put("sendStreet", "八甲村");
		object.put("sendDetailAddr", "上湾组");
		object.put("consignee", "中国移动");
		object.put("consigneePhone", "10086");
		object.put("consigneeProvince", "广东省");
		object.put("consigneeCity", "深圳市");
		object.put("consigneeArea", "南山区");
		object.put("consigneeStreet", "大冲存");
		object.put("consigneeDetailAddr", "中科大厦19楼B1");
		object.put("weight", "20");
		object.put("amt", "0");
		object.put("goodsName", "内件品名");
		object.put("remark", "备注");
		String postUrl = "http://waybill-get.sto-express.cn:9091/cusOrder";
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("siteName", siteName);
		uriVariables.put("customerName", customerName);
		uriVariables.put("customerPwd", customerPwd);
		uriVariables.put("source", "软件名称");
		uriVariables.put("orderData", object.toString());
		String md5 = Base64Md5Util.MD5(siteName + customerName + customerPwd, "UTF-8");
		uriVariables.put("sign", md5);
		String res = HttpsUtil.sendPost(postUrl, uriVariables);
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(res);
		System.out.println(jsonObject.toString());
		String data = jsonObject.getString("data");
		com.alibaba.fastjson.JSONObject dataObject = JSON.parseObject(data);
		System.out.println(data);
		String result = null;
		if ("true".equals(jsonObject.getString("success"))) {
			result = jsonObject.getString("success") + "," + dataObject.getString("wayBillNo") + ","
					+ dataObject.getString("bigWord") + " ," + dataObject.getString("packgePlace");
		} else {
			result = jsonObject.getString("success") + ", , ," + jsonObject.getString("errorMsg");
		}
		System.out.println(result);
	}

	@Test
	public void strTest() {
		String str="`~!@#$%^&*()_-+=|{}':;\",[].<>/?";
		 str=str.replaceAll("[`~!@#$%^&*()_+=|{}':;\",\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
		System.out.println(str);
	}
}
