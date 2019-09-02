package com.visionet.ztx.util;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;

public class ShunFengXmlUtil {
	public static JSONObject listNodes(String Xml) {
		JSONObject jsonObject = new JSONObject();
		try {
			Document document = DocumentHelper.parseText(Xml);
			Element root = document.getRootElement();
			for (Iterator i_pe = root.elementIterator(); i_pe.hasNext();) {
				Element e_pe = (Element) i_pe.next();
				String person = e_pe.getName();
				if (person.equalsIgnoreCase("Body")) {
					Element e_adds = e_pe.element("OrderResponse");
					String mailno = e_adds.attributeValue("mailno");
					String destcode = e_adds.attributeValue("destcode");
					jsonObject.put("mailno", mailno);
					jsonObject.put("destcode", destcode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
