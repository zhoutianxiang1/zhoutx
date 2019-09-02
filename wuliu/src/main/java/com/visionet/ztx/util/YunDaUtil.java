package com.visionet.ztx.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.visionet.ztx.domain.WuLiuDetail;

/**
 * 韵达快递工具类
 * 
 * @author zhoutx
 * @date 2019-07-15
 */
public class YunDaUtil {
	private static Logger log = Logger.getLogger(YunDaUtil.class);// 输出Log日志
	// 物流接单
	public static String placeOrder(WuLiuDetail wuLiuDetail) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("<orders>");
		builder.append("<order>");
		builder.append("<order_serial_no>").append(wuLiuDetail.getDid()).append("</order_serial_no>");// 订单号
		builder.append("<khddh>").append(wuLiuDetail.getDid()).append("</khddh>");// 系统订单号
		builder.append("<nbckh></nbckh>");// 系统订单号
		builder.append("<order_type>o2o</order_type>");// 订单类型oto
		builder.append("<sender>");
		builder.append("<name>").append(wuLiuDetail.getSourcemanger()).append("</name>");// 发货人姓名
		builder.append("<company>").append(wuLiuDetail.getSourcedeptname())
				.append("</company>");// 发货人公司
		builder.append("<city>").append(wuLiuDetail.getFaddress()).append("</city>");// 发货人地址
		builder.append("<address>").append(wuLiuDetail.getSourceaddressDetails()).append("</address>");// 发货人详细地址
		builder.append("<postcode>").append(wuLiuDetail.getSourcecode()).append("</postcode>");// 发货人邮编
		builder.append("<phone>").append(wuLiuDetail.getSourcephone()).append("</phone>");// 发货人固话
		builder.append("<mobile>").append(wuLiuDetail.getSourcephone()).append("</mobile>");// 发货人手机
		builder.append("<branch></branch>");// 发货人网点编码
		builder.append("</sender>");
		builder.append("<receiver>");
		builder.append("<name>").append(wuLiuDetail.getDestmanger()).append("</name>");// 收货人姓名
		builder.append("<company>").append(wuLiuDetail.getDestdeptname())
				.append("</company>");// 收货人公司
		builder.append("<city>").append(wuLiuDetail.getSaddress()).append("</city>");// 收货人地址
		builder.append("<address>").append(wuLiuDetail.getDestaddressDetails()).append("</address>");// 收货人详细地址
		builder.append("<postcode>").append(wuLiuDetail.getDestcode()).append("</postcode>");// 收货人邮编
		builder.append("<phone>").append(wuLiuDetail.getDestphone()).append("</phone>");// 收货人固话
		builder.append("<mobile>").append(wuLiuDetail.getDestphone()).append("</mobile>");// 收货人手机
		builder.append("<branch></branch>");// 收货人网点编码
		builder.append("</receiver>");
		builder.append("<weight>").append(wuLiuDetail.getZhongliang()).append("</weight>");// 货品重量
		builder.append("<size></size>");// 货品尺寸
		builder.append("<value></value>");// 货物金额
		builder.append("<freight></freight>");// 运费
		builder.append("<premium></premium>");// 保险费
		builder.append("<other_charges></other_charges>");// 其它运费
		builder.append("<collection_currency></collection_currency>");// 代收货款币种
		builder.append("<collection_value></collection_value>");// 代收货款金额
		builder.append("<special></special>");// 商品性质
		builder.append("<items>");
		builder.append("<item>");
		builder.append("<name></name>");// 商品名称
		builder.append("<number></number>");// 商品数量
		builder.append("<remark></remark>");// 商品备注
		builder.append("</item>");
		builder.append("</items>");
		builder.append("<remark>").append(wuLiuDetail.getBz()).append("</remark>");// 手工单号,备注
		builder.append("<cus_area1>").append(wuLiuDetail.getDid()).append("</cus_area1>");// 手工单号
		builder.append("<cus_area2>").append(wuLiuDetail.getDid()).append("</cus_area2>");// 自定义显示信息
		builder.append("</order>");
		builder.append("</orders>");
		String yunDaUrl = "http://join.yundasys.com/interface_oto.php";
		// 加密数据
		String partnerid = "201199900018";// 合作商ID
		String password = "a123";// 密码
		String version = "1.0";// 版本号
		String ydrequest = "otodata";// 数据请求格式
		String xmldata = builder.toString();
		log.info("韵达传入参数：" + xmldata);
		String base64XmlData = Base64Md5Util.base64(xmldata, "UTF-8");
		String md5Xml = base64XmlData + partnerid + password;
		String validation = Base64Md5Util.MD5(md5Xml, "UTF-8");
		Map<String, String> yunDaResult = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("partnerid", partnerid);
		params.put("version", version);
		params.put("request", ydrequest);
		params.put("xmldata", Base64Md5Util.urlEncoder(base64XmlData, "UTF-8"));
		params.put("validation", Base64Md5Util.urlEncoder(validation, "UTF-8"));
		String yunDa = HttpsUtil.sendPost(yunDaUrl, params);
		Document document = DocumentHelper.parseText(yunDa);
		Element root = document.getRootElement();
		yunDaResult = XmlUtil.listNodes(root, yunDaResult);
		log.info("韵达返回参数：" + yunDaResult.toString());
		boolean contains = yunDaResult.containsKey("status");
		if (contains) {
			if (yunDaResult.get("mail_no") != null) {
				return "true," + yunDaResult.get("mail_no") + ", ," + yunDaResult.get("msg");
			} else {
				return "false, , ," + yunDaResult.get("msg");
			}
		} else {
			return "false, , ," + yunDaResult.get("remark");
		}
	}

	// 仓库接单
	public static String canKuPlaceOrder(WuLiuDetail wuLiuDetail) throws Exception {
		StringBuilder canKuBuilder = new StringBuilder();
		canKuBuilder.append("<orders>");
		canKuBuilder.append("<order>");
		canKuBuilder.append("<order_serial_no>").append(wuLiuDetail.getDid()).append("</order_serial_no>");// 订单号
		canKuBuilder.append("<khddh>").append(wuLiuDetail.getDid()).append("</khddh>");// 系统订单号
		canKuBuilder.append("<nbckh></nbckh>");// 系统订单号
		canKuBuilder.append("<order_type>common</order_type>");// 订单类型
		canKuBuilder.append("<sender>");
		canKuBuilder.append("<name>").append(wuLiuDetail.getSourcemanger()).append("</name>");// 发货人姓名
		canKuBuilder.append("<company>").append(wuLiuDetail.getSourcedeptname())
				.append("</company>");// 发货人公司
		canKuBuilder.append("<city>").append(wuLiuDetail.getFaddress()).append("</city>");// 发货人地址
		canKuBuilder.append("<address>").append(wuLiuDetail.getSourceaddressDetails()).append("</address>");// 发货人详细地址
		canKuBuilder.append("<postcode>").append(wuLiuDetail.getSourcecode()).append("</postcode>");// 发货人邮编
		canKuBuilder.append("<phone>").append(wuLiuDetail.getSourcephone()).append("</phone>");// 发货人固话
		canKuBuilder.append("<mobile>").append(wuLiuDetail.getSourcephone()).append("</mobile>");// 发货人手机
		canKuBuilder.append("<branch></branch>");// 发货人网点编码
		canKuBuilder.append("</sender>");
		canKuBuilder.append("<receiver>");
		canKuBuilder.append("<name>").append(wuLiuDetail.getDestmanger()).append("</name>");// 收货人姓名
		canKuBuilder.append("<company>").append(wuLiuDetail.getDestdeptname())
				.append("</company>");// 收货人公司
		canKuBuilder.append("<city>").append(wuLiuDetail.getSaddress()).append("</city>");// 收货人地址
		canKuBuilder.append("<address>").append(wuLiuDetail.getDestaddressDetails()).append("</address>");// 收货人详细地址
		canKuBuilder.append("<postcode>").append(wuLiuDetail.getDestcode()).append("</postcode>");// 收货人邮编
		canKuBuilder.append("<phone>").append(wuLiuDetail.getDestphone()).append("</phone>");// 收货人固话
		canKuBuilder.append("<mobile>").append(wuLiuDetail.getDestphone()).append("</mobile>");// 收货人手机
		canKuBuilder.append("<branch></branch>");// 收货人网点编码
		canKuBuilder.append("</receiver>");
		canKuBuilder.append("<weight>").append(wuLiuDetail.getZhongliang()).append("</weight>");// 货品重量
		canKuBuilder.append("<size></size>");// 货品尺寸
		canKuBuilder.append("<value></value>");// 货物金额
		canKuBuilder.append("<freight></freight>");// 运费
		canKuBuilder.append("<premium></premium>");// 保险费
		canKuBuilder.append("<other_charges></other_charges>");// 其它运费
		canKuBuilder.append("<collection_currency></collection_currency>");// 代收货款币种
		canKuBuilder.append("<collection_value></collection_value>");// 代收货款金额
		canKuBuilder.append("<special></special>");// 商品性质
		canKuBuilder.append("<items>");
		canKuBuilder.append("<item>");
		canKuBuilder.append("<name></name>");// 商品名称
		canKuBuilder.append("<number></number>");// 商品数量
		canKuBuilder.append("<remark></remark>");// 商品备注
		canKuBuilder.append("</item>");
		canKuBuilder.append("</items>");
		canKuBuilder.append("<remark>").append(wuLiuDetail.getBz()).append("</remark>");// 手工单号,备注
		canKuBuilder.append("<cus_area1>").append(wuLiuDetail.getDid()).append("</cus_area1>");// 手工单号
		canKuBuilder.append("<cus_area2>").append(wuLiuDetail.getDid()).append("</cus_area2>");// 自定义显示信息
		canKuBuilder.append("</order>");
		canKuBuilder.append("</orders>");
		String yunDaUrl = "http://order.yundasys.com:10235/cus_order/order_interface/interface_receive_order__mailno.php";
		// 加密数据
		String partnerid = wuLiuDetail.getAccount();// 合作商ID
		String password = wuLiuDetail.getKey();// 密码
		String version = "1.4";// 版本号
		String ydrequest = "data";// 数据请求格式
		String xmldata = canKuBuilder.toString();
		log.info("韵达仓库传入参数：" + xmldata);
		String base64XmlData = Base64Md5Util.base64(xmldata, "UTF-8");
		String md5Xml = base64XmlData + partnerid + password;
		String validation = Base64Md5Util.MD5(md5Xml, "UTF-8");
		Map<String, String> yunDaResult = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("partnerid", partnerid);
		params.put("version", version);
		params.put("request", ydrequest);
		params.put("xmldata", Base64Md5Util.urlEncoder(base64XmlData, "UTF-8"));
		params.put("validation", Base64Md5Util.urlEncoder(validation, "UTF-8"));
		String yunDa = HttpsUtil.sendPost(yunDaUrl, params);
		Document document = DocumentHelper.parseText(yunDa);
		Element root = document.getRootElement();
		yunDaResult = XmlUtil.listNodes(root, yunDaResult);
		log.info("韵达仓库返回参数：" + yunDaResult.toString());
		boolean contains = yunDaResult.containsKey("status");
		if (contains) {
			if (yunDaResult.get("mail_no") != null) {
				return "true," + yunDaResult.get("mail_no") + ", ," + yunDaResult.get("msg");
			} else {
				return "false, , ," + yunDaResult.get("msg");
			}
		} else {
			return "false, , ," + yunDaResult.get("remark");
		}
	}
	
	// 物流取消订单
	public static String cancelOrder(WuLiuDetail wuLiuDetail) throws Exception {
		StringBuilder builder = new StringBuilder();
//		builder.append("<orders>");
//		builder.append("<order>");
//		builder.append("<order_serial_no>").append(wuLiuDetail.getDid()).append("</order_serial_no> ");
//		builder.append("<mailno>").append(wuLiuDetail.getMailno()).append("</mailno> ");
//		builder.append("</order>");
//		builder.append("</orders>");
//		String yunDaUrl = "http://join.yundasys.com/interface_cancel_order.php";
		builder.append("<orders>");
			builder.append("<order>");
				builder.append("<orderid>").append(wuLiuDetail.getDid()).append("</orderid> ");
				builder.append("<callback>").append(wuLiuDetail.getMailno()).append("</callback>");
				builder.append("<command>status</command>");
				builder.append("<parameter>withdraw</parameter>");
				builder.append("<remark></remark>");
			builder.append("</order>");
		builder.append("</orders>");
		String yunDaUrl = "http://uat.meta.yundasys.com:8080/store_order/public/interface.php/api/order/index";
		// 加密数据
		String partnerid = "2017009876";// 合作商ID
		String password = "123456";// 密码
		String version = "1.0";// 版本号
//		String ydrequest = "otodata";// 数据请求格式
		String ydrequest = "info";// 数据请求格式
		String xmldata = builder.toString();
		log.info("韵达取消订单传入参数：" + xmldata);
		String base64XmlData = Base64Md5Util.base64(xmldata, "UTF-8");
		String md5Xml = base64XmlData+partnerid+password;
		String validation = Base64Md5Util.MD5(md5Xml, "UTF-8");
		Map<String, String> params = new HashMap<String, String>();
	    params.put("partnerid", partnerid);
	    params.put("version", version);
	    params.put("request", ydrequest);
	    params.put("xmldata", Base64Md5Util.urlEncoder(base64XmlData,"UTF-8"));
	    params.put("validation", Base64Md5Util.urlEncoder(validation,"UTF-8"));
		String yunDa = HttpsUtil.sendPost(yunDaUrl, params);
		Document document = DocumentHelper.parseText(yunDa);
		Element root = document.getRootElement();
		Map<String, String> yunDaResult = new HashMap<String, String>();
		yunDaResult = XmlUtil.listNodes(root, yunDaResult);
		log.info("韵达返回参数：" + yunDaResult.toString());
		return yunDaResult.toString();
	}
	
	
		// 物流下订单
		public static String test(WuLiuDetail wuLiuDetail) throws Exception {
			StringBuilder builder = new StringBuilder();
			builder.append("<orders>");
			builder.append("<order>");
			builder.append("<orderid>E00000000001</orderid>");
			builder.append("<mailno>1200000000001</mailno>");
			builder.append("<callback>D_00001</callback>");
			builder.append("<sender>");
			builder.append("<storeid>20119990001811</storeid>");
			builder.append("<name>刘 XX</name>");
			builder.append("<city>上海市,青浦区</city>");
			builder.append("<address>盈港东路 6679 号</address>");
			builder.append("<mobile>13812345678,13587654321</mobile>");
			builder.append("</sender>");
			builder.append("<receiver>");
			builder.append("<storeid>20119990001812</storeid>");
			builder.append("<name>张 YY</name>");
			builder.append("<city>广东省,广州市,天河区</city>");
			builder.append("<address>帝王大厦 5-12 号</address>");
			builder.append("<mobile>13587654321</mobile>");
			builder.append("</receiver>");
			builder.append("<items>");
			builder.append("<item>"); 
			builder.append("<name>芭比闹钟</name>");
			builder.append("<number>1</number>");
			builder.append("<remark></remark>");
			builder.append("<</item>");
			builder.append("</items>");
			builder.append("<remark>包已打好</remark>");
			builder.append("</order>");
			builder.append("</orders>");
			String yunDaUrl = "http://uat.meta.yundasys.com:8080/store_order/public/interface.php/api/order/index";
			// 加密数据
			String partnerid = "2017009876";// 合作商ID
			String password = "123456";// 密码
			String version = "1.0";// 版本号
			String ydrequest = "data";// 数据请求格式
			String xmldata = builder.toString();
			log.info("韵达传入参数：" + xmldata);
			String base64XmlData = Base64Md5Util.base64(xmldata, "UTF-8");
			String md5Xml = base64XmlData + partnerid + password;
			String validation = Base64Md5Util.MD5(md5Xml, "UTF-8");
			Map<String, String> yunDaResult = new HashMap<String, String>();
			Map<String, String> params = new HashMap<String, String>();
			params.put("partnerid", partnerid);
			params.put("version", version);
			params.put("request", ydrequest);
			params.put("xmldata", Base64Md5Util.urlEncoder(base64XmlData, "UTF-8"));
			params.put("validation", Base64Md5Util.urlEncoder(validation, "UTF-8"));
			String yunDa = HttpsUtil.sendPost(yunDaUrl, params);
			Document document = DocumentHelper.parseText(yunDa);
			Element root = document.getRootElement();
			yunDaResult = XmlUtil.listNodes(root, yunDaResult);
			log.info("韵达返回参数：" + yunDaResult.toString());
			return yunDaResult.toString();
		}
}
