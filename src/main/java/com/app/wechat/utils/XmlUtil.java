package com.app.wechat.utils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.List;


@Slf4j
public class XmlUtil {
	private SAXReader reader;
	private Document document;

	/**
	 *  sample : /result/@info <===> <result info="">
	 */
	@SuppressWarnings("unchecked")
	public List<Node> getAttribute(String xpath) {
		if (reader == null) {
			throw new NullPointerException("SAXReader");
		}
		return document.selectNodes(xpath);
	}

	/**
	 * sample : /response/errcode <===> <response><errcode>12321</errcode></response> 
	 */
	@SuppressWarnings("unchecked")
	public List<Node> getSelectNodes(String xpath) {
		if (reader == null) {
			throw new NullPointerException("SAXReader");
		}
		return document.selectNodes(xpath);
	}

	public XmlUtil(String xml) {
		reader = new SAXReader();
		try {
			document = reader.read(new BufferedInputStream(new ByteArrayInputStream(xml.trim().getBytes())));
		} catch (DocumentException e) {
			log.info("解析xml异常 : ", e);
		}
	}

}
