package com.visionet.ztx.util;

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

import com.alibaba.fastjson.JSON;
import com.visionet.ztx.domain.WuLiuDetail;

/**
 * 春风快递工具类
 * 
 * @author zhoutx
 * @date 2019-07-15
 */
public class ChunFengUtil {
	private static Logger log = Logger.getLogger(ChunFengUtil.class);// 输出Log日志
	// 物流接单

	public static String placeOrder(WuLiuDetail wuLiuDetail) throws Exception {
		String code = "200041";// 账号
		String pass = "STsuoDndZNrFntnBNRs0sZyyyPp0pliB";// 密码
		String datetime = wuLiuDetail.getDatetime();// 当前时间(不带符号)
		String md5 = Base64Md5Util.MD5(code + pass + datetime, "UTF-8");
		String func = "order.submit";
		String JsonXml = "{\"code\":\"" + code + "\",\"password\":\"" + pass + "\",\"datetime\":\"" + datetime
				+ "\",\"verify_code\":\"" + md5 + "\"" + ",\"func\":\"" + func + "\",\"content\":{\"quantity\":\""
				+ wuLiuDetail.getXiangshu() + "\",\"billno\":\"" + wuLiuDetail.getDid()
				+ "\",\"package\":\"\",\"volume\":\"" + wuLiuDetail.getTiji() + "\"" + ",\"sender\":{\"code\":\""
				+ wuLiuDetail.getSourcedeptname() + "\",\"name\":\"" + wuLiuDetail.getSourcemanger()
				+ "\",\"company\":\"" + wuLiuDetail.getSourcedeptname() + "\"" + ",\"city\":\""
				+ wuLiuDetail.getFaddress() + "\",\"address\":\"" + wuLiuDetail.getSourceaddress() + "\",\"phone\":\""
				+ wuLiuDetail.getSourcephone() + "\",\"mobile\":\"" + wuLiuDetail.getSourcephone() + "\"}"
				+ ",\"receiver\":{\"code\":\"" + wuLiuDetail.getDestdeptname() + "\",\"name\":\""
				+ wuLiuDetail.getDestmanger() + "\",\"company\":\"" + wuLiuDetail.getDestdeptname()
				+ "\"" + ",\"city\":\"" + wuLiuDetail.getSaddress() + "\",\"address\":\"" + wuLiuDetail.getDestaddress()
				+ "\",\"phone\":\"" + wuLiuDetail.getSourcephone() + "\",\"mobile\":\"" + wuLiuDetail.getDestphone()
				+ "\"}}}";
		log.info("春风汽运传入参数：" + JsonXml);
		String base64Json = Base64Md5Util.base64(Base64Md5Util.urlEncoder(JsonXml, "UTF-8"), "UTF-8");
		Map<String, String> chunFenParams = new HashMap<String, String>();
		chunFenParams.put("func", func);
		chunFenParams.put("datetime", datetime);
		chunFenParams.put("code", code);
		chunFenParams.put("password", pass);
		chunFenParams.put("verify_code", md5);
		chunFenParams.put("data", base64Json);
		String chunFenUrl = "http://api.spring56.com/api/interface.php";
		String Post = HttpsUtil.sendPost(chunFenUrl, chunFenParams);
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(Post);
		log.info("春风汽运返回参数：" + jsonObject.toString());
		if (jsonObject.getString("result").equals("true")) {
			com.alibaba.fastjson.JSONObject orderJson = JSON.parseObject(jsonObject.getString("info").toString());
			return "true," + orderJson.getString("orderno") + ", ," + jsonObject.getString("remark");
		} else if (jsonObject.getString("result").equals("false")) {
			return "false, , ," + jsonObject.getString("remark");
		}
		return null;

	}

	// 仓库接单
	public static String canKuPlaceOrder(WuLiuDetail wuLiuDetail) throws Exception {
		String datetimeCK = wuLiuDetail.getDatetime();// 当前时间(不带符号)
		String funcCK = "order.submit";
		log.info("春风仓库传入参数" + wuLiuDetail.getCfjson());
		String base64JsonCK = Base64Md5Util.base64(wuLiuDetail.getCfjson(), "UTF-8");
		String md5CK = Base64Md5Util
				.MD5(wuLiuDetail.getAccount() + datetimeCK + funcCK + base64JsonCK + wuLiuDetail.getKey(), "UTF-8");
		Map<String, String> chunFenCKParams = new HashMap<String, String>();
		chunFenCKParams.put("style", "json");
		chunFenCKParams.put("func", "order.submit");
		chunFenCKParams.put("partner", "doright");
		chunFenCKParams.put("datetime", datetimeCK);
		chunFenCKParams.put("content", base64JsonCK);
		chunFenCKParams.put("verify", md5CK);
		chunFenCKParams.put("echo_mailno", "true");
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (String chunFenCKkey : chunFenCKParams.keySet()) {
			list.add(new BasicNameValuePair(chunFenCKkey, String.valueOf(chunFenCKParams.get(chunFenCKkey))));
		}
		// 获取默认的请求客户端
		HttpClient client = new SSLClient();
		// 通过HttpPost来发送post请求
		HttpPost httpPost = new HttpPost("http://partner.spring56.com/partner/interface.php");
		// 第二步：我们发现Entity是一个接口，所以只能找实现类，发现实现类又需要一个集合，集合的泛型是NameValuePair类型
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list);
		// 第一步：通过setEntity 将我们的entity对象传递过去
		httpPost.setEntity(formEntity);
		/*
		 * post带参数结束
		 */
		// HttpEntity
		// 是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
		// 通过client来执行请求，获取一个响应结果
		HttpResponse response = client.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity, "UTF-8");
		com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
		log.info("春风仓库返回参数：" + jsonObject.toString());
		if (jsonObject.getString("result").equals("true")) {
			return jsonObject.getString("result") + "," + jsonObject.getString("mailno") + ", ,"
					+ jsonObject.getString("remark");
		} else if (jsonObject.getString("result").equals("false")) {
			return "false, , ," + jsonObject.getString("remark");
		}
		return null;
	}
}
