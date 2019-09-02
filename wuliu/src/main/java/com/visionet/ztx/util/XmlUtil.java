package com.visionet.ztx.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class XmlUtil {
	public static Map<String, String> listNodes(Element node, Map<String, String> map) {
		// 首先获取当前节点的所有属性节点
		List<Attribute> list = node.attributes();
		// 如果当前节点内容不为空，则输出
		if (!(node.getTextTrim().equals(""))) {
			map.put(node.getName(), node.getText());
		}
		Iterator<Element> iterator = node.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			listNodes(e, map);
		}
		return map;
	}
}
