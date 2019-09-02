package com.visionet.ztx.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.visionet.ztx.domain.WuLiuDetail;
import com.visionet.ztx.dto.WuLiuDto;
import com.visionet.ztx.service.IWuLiuService;
import com.visionet.ztx.service.constants.WuLiuType;
import com.visionet.ztx.util.Base64Md5Util;
import com.visionet.ztx.util.ChunFengUtil;
import com.visionet.ztx.util.DeBangUtil;
import com.visionet.ztx.util.IpUtil;
import com.visionet.ztx.util.JingDongUtil;
import com.visionet.ztx.util.SSLClient;
import com.visionet.ztx.util.ShenTongUtil;
import com.visionet.ztx.util.ShunFengUtil;
import com.visionet.ztx.util.YuanTongUtil;
import com.visionet.ztx.util.YunDaUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物流快递controller
 * 
 * @author 周天祥
 * @date: 2019-07-04
 */
@Api(tags = { "物流快递接口" })
@RestController
@RequestMapping(value = "/wuLiu")
public class WuLiuController {
	private Logger log = Logger.getLogger(WuLiuController.class);// 输出Log日志
	@Autowired
	private IWuLiuService wuLiuService;

	@ApiOperation(value = "物流接单接口", notes = "物流接单接口")
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public void wuliuPlaceOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		WuLiuDetail wuLiuDetail = new WuLiuDetail();
		// 物流公司
		wuLiuDetail.setCompany(request.getParameter("company"));
		log.info("快递公司名称：" + wuLiuDetail.getCompany());
		// 账号
		wuLiuDetail.setAccount(request.getParameter("account"));
		// 密匙
		wuLiuDetail.setKey(request.getParameter("key"));
		// 单号
		wuLiuDetail.setDid(request.getParameter("did"));
		// 发货人
		wuLiuDetail.setSourcemanger(request.getParameter("sourcemanger"));
		// 发货单位
		wuLiuDetail.setSourcedeptname(request.getParameter("sourcedeptname"));
		// 发货省
		wuLiuDetail.setSourcesheng(request.getParameter("sourcesheng"));
		// 发货市
		wuLiuDetail.setSourceshi(request.getParameter("sourceshi"));
		// 发货区
		wuLiuDetail.setSourcequ(request.getParameter("sourcequ"));
		// 发货地址
		wuLiuDetail.setSourceaddress(request.getParameter("sourceaddress"));
		// 发货方邮编
		wuLiuDetail.setSourcecode(request.getParameter("sourcecode"));
		// 发货人电话
		wuLiuDetail.setSourcephone(request.getParameter("sourcephone"));
		// 收货人
		wuLiuDetail.setDestmanger(request.getParameter("destmanger"));
		// 收货单位
		wuLiuDetail.setDestdeptname(request.getParameter("destdeptname"));
		// 收货省
		wuLiuDetail.setDestsheng(request.getParameter("destsheng"));
		// 收货市
		wuLiuDetail.setDestshi(request.getParameter("destshi"));
		// 收货区
		wuLiuDetail.setDestqu(request.getParameter("destqu"));
		// 收货地址
		wuLiuDetail.setDestaddress(request.getParameter("destaddress"));
		// 收货方邮编
		wuLiuDetail.setDestcode(request.getParameter("destcode"));
		// 收货人电话
		wuLiuDetail.setDestphone(request.getParameter("destphone"));
		// 重量
		wuLiuDetail.setZhongliang(request.getParameter("zhongliang"));
		// 包裹数
		wuLiuDetail.setXiangshu(request.getParameter("xiangshu"));
		// 体积
		wuLiuDetail.setTiji(request.getParameter("tiji"));
		// 备注
		wuLiuDetail.setBz(request.getParameter("bz"));
		// 手工单号
		wuLiuDetail.setManual_id(request.getParameter("manual_id"));
		// Head
		wuLiuDetail.setHead(request.getParameter("Head"));
		// 支付方式
		wuLiuDetail.setPay_method(request.getParameter("pay_method"));
		// 支付方式
		wuLiuDetail.setDel_method(request.getParameter("del_method"));
		log.info("0：现付  2：到付 1：月结支付方式：" + wuLiuDetail.getPay_method());
		// 发货地址明细
		wuLiuDetail.setSourceaddressDetails(wuLiuDetail.getSourcesheng() + wuLiuDetail.getSourceshi()
				+ wuLiuDetail.getSourcequ() + wuLiuDetail.getSourceaddress());
		// 收货地址明细
		wuLiuDetail.setDestaddressDetails(wuLiuDetail.getDestsheng() + wuLiuDetail.getDestshi()
				+ wuLiuDetail.getDestqu() + wuLiuDetail.getDestaddress());
		// 发货地址省，市
		wuLiuDetail.setFaddress(wuLiuDetail.getSourcesheng() + "," + wuLiuDetail.getSourceshi());
		if (null != wuLiuDetail.getSourcequ() && 0 < wuLiuDetail.getSourcequ().length()) {
			wuLiuDetail.setFaddress(wuLiuDetail.getFaddress() + "," + wuLiuDetail.getSourcequ());// 发货地址省，市，区
		}
		wuLiuDetail.setSaddress(wuLiuDetail.getDestsheng() + "," + wuLiuDetail.getDestshi());// 收货地址省,市
		if (null != wuLiuDetail.getDestqu() && 0 < wuLiuDetail.getDestqu().length()) {
			wuLiuDetail.setSaddress(wuLiuDetail.getSaddress() + "," + wuLiuDetail.getDestqu());
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(date);// 当前时间(不带符号)
		wuLiuDetail.setDatetime(datetime);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime2 = sdf2.format(date);
		wuLiuDetail.setDatetime2(datetime2);
		switch (wuLiuDetail.getCompany()) {
		case WuLiuType.YUNDA:
			String yunDaResult = YunDaUtil.placeOrder(wuLiuDetail);
			response.getWriter().println(yunDaResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.YUNDA_CANKU:
			String yunDaCanKuResult = YunDaUtil.canKuPlaceOrder(wuLiuDetail);
			response.getWriter().println(yunDaCanKuResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.CHUNFENG_QIYUN:
			String chunFenResult = ChunFengUtil.placeOrder(wuLiuDetail);
			response.getWriter().println(chunFenResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.CHUNFENG_QIYUN_CANKU:
			// 春风仓库json
			wuLiuDetail.setCfjson(request.getParameter("cfjson"));
			String chunFenCKResult = ChunFengUtil.canKuPlaceOrder(wuLiuDetail);
			response.getWriter().println(chunFenCKResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.DEBANG:
			String deBangResult = DeBangUtil.placeOrder(wuLiuDetail);
			response.getWriter().print(deBangResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.DEBANG_QIYUN:
			String deBangLDResult = DeBangUtil.ldPlaceOrder(wuLiuDetail);
			response.getWriter().print(deBangLDResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.JINGDONG:
			WuLiuDto wuLiuDto = wuLiuService.findByName(WuLiuType.JINGDONG);
			String jinDongResult = JingDongUtil.placeOrder(wuLiuDetail, wuLiuDto);
			response.getWriter().print(jinDongResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.YUANTONG:
			String yuanTongResult = YuanTongUtil.placeOrder(wuLiuDetail);
			response.getWriter().print(yuanTongResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.SHUNFENG:
			String shunFengResult = ShunFengUtil.placeOrder(wuLiuDetail);
			response.getWriter().print(shunFengResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.SHENTONG:
			String shenTongResult = ShenTongUtil.placeOrder(wuLiuDetail);
			response.getWriter().print(shenTongResult);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		case WuLiuType.KUAYUE:
			String URL = "https://openapi.ky-express.com/kyeopenapi/CustomerWaybillPrint";
			String vipshopCode = "";
			if (wuLiuDetail.getDestdeptname().indexOf("唯品会") > 0) {
				vipshopCode = "唯品会";
			}
			com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
			jsonObject.put("uuid", "02118925565");
			jsonObject.put("key", "20FC8C6AE52C335B9FB86690CCBEFDAA");
			jsonObject.put("col_018", "当天达");
			jsonObject.put("col_001", wuLiuDetail.getSourcedeptname());
			jsonObject.put("col_004", wuLiuDetail.getSourcemanger());
			jsonObject.put("col_002", wuLiuDetail.getSourceaddressDetails());
			jsonObject.put("col_005", wuLiuDetail.getSourcephone());
			jsonObject.put("col_006", wuLiuDetail.getDestdeptname());
			jsonObject.put("col_010", wuLiuDetail.getDestmanger());
			jsonObject.put("col_007", wuLiuDetail.getDestaddressDetails());
			jsonObject.put("col_009", wuLiuDetail.getDestphone());
			jsonObject.put("col_037", "02118925565");
			jsonObject.put("col_013", wuLiuDetail.getPay_method());
			jsonObject.put("col_019", "服装");
			jsonObject.put("col_031", "0");
			jsonObject.put("col_028", "0");
			jsonObject.put("vipshopCode", vipshopCode);
			String SendObject = jsonObject.toString();
			Iterator<Entry<String, Object>> it = jsonObject.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> temp = it.next();
				if (temp.getValue() == null || temp.getValue().toString().length() < 1) {
					it.remove();
				}
			}
			String token = com.alibaba.fastjson.JSONObject.toJSONString(jsonObject.toString(),
					SerializerFeature.SortField);
			token = com.alibaba.fastjson.JSONObject.parse(token).toString();
			token = token.replace("{", "").replace("\"", "").replace(":", "").replace(",", "").replace("}", "");
			token = "4B383A74A3FE6286E0262F1104220E14" + token;
			token = Base64Md5Util.MD5(token, "UTF-8").toUpperCase();
			HttpClient client = new SSLClient();
			HttpPost post = new HttpPost(URL);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("kye", "10269");
			post.setHeader("access-token", token);
			StringEntity SendEntity = new StringEntity(SendObject.toString(), "UTF-8");
			post.setEntity(SendEntity);
			HttpResponse res = client.execute(post);
			HttpEntity entity = res.getEntity();
			String Result = EntityUtils.toString(entity, "GBK").trim();
			response.getWriter().print(Result);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		default:
			String defaultResult = "false, , ,物流公司名称不存在！";
			response.getWriter().print(defaultResult);
			response.getWriter().flush();
			response.getWriter().close();
		}
		String ip = IpUtil.getIpAddr(request);
		log.info("ip地址：" + ip);
	}

	@ApiOperation(value = "物流跟踪接口", notes = "物流跟踪接口")
	@RequestMapping(value = "/queryLogistics", method = RequestMethod.POST)
	public void wuliuQueryLogistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		String company = request.getParameter("company");// 物流公司
		String waybillCode = request.getParameter("did");// 运单号
		switch (company) {
		case WuLiuType.JINGDONG:
			WuLiuDto wuLiuDto = wuLiuService.findByName(WuLiuType.JINGDONG);
			String result = JingDongUtil.queryLogistics(wuLiuDto, waybillCode);
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		default:
			String defaultResult = "false, , ,物流公司名称不存在！";
			response.getWriter().print(defaultResult);
			response.getWriter().flush();
			response.getWriter().close();
		}

	}

	@ApiOperation(value = "物流取消订单接口", notes = "物流取消订单接口")
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public void wuliuCancelOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		WuLiuDetail wuLiuDetail = new WuLiuDetail();
		wuLiuDetail.setCompany(request.getParameter("company"));// 物流公司
		wuLiuDetail.setDid(request.getParameter("did"));// 运单号
		wuLiuDetail.setMailno(request.getParameter("mailno"));// 运单号
		switch (wuLiuDetail.getCompany()) {
		case WuLiuType.YUNDA:
			String result = YunDaUtil.cancelOrder(wuLiuDetail);
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		default:
			String defaultResult = "false, , ,物流公司名称不存在！";
			response.getWriter().print(defaultResult);
			response.getWriter().flush();
			response.getWriter().close();
		}

	}
	
	
	@ApiOperation(value = "物流下订单接口", notes = "物流下订单接口")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		WuLiuDetail wuLiuDetail = new WuLiuDetail();
		wuLiuDetail.setCompany(request.getParameter("company"));// 物流公司
		wuLiuDetail.setDid(request.getParameter("did"));// 运单号
		wuLiuDetail.setMailno(request.getParameter("mailno"));// 运单号
		switch (wuLiuDetail.getCompany()) {
		case WuLiuType.YUNDA:
			String result = YunDaUtil.test(wuLiuDetail);
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
			break;
		default:
			String defaultResult = "false, , ,物流公司名称不存在！";
			response.getWriter().print(defaultResult);
			response.getWriter().flush();
			response.getWriter().close();
		}

	}

	
	
}
