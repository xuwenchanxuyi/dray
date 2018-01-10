/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.oracle.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * 绠�鍗曞皝瑁匤ackson锛屽疄鐜癑SON String<->Java Object鐨凪apper.
 * 
 * 灏佽涓嶅悓鐨勮緭鍑洪鏍�, 浣跨敤涓嶅悓鐨刡uilder鍑芥暟鍒涘缓瀹炰緥.
 * 
 * @author mary
 */
public class JsonMapper {

//	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private ObjectMapper mapper;

	public JsonMapper() {
		this(null);
	}

	public JsonMapper(Include include) {
		mapper = new ObjectMapper();

		// 璁剧疆杈撳嚭鏃跺寘鍚睘鎬х殑椋庢牸
		if (include != null) {
			mapper.setSerializationInclusion(include);
		}
		// 璁剧疆杈撳叆鏃跺拷鐣ュ湪JSON瀛楃涓蹭腑瀛樺湪浣咼ava瀵硅薄瀹為檯娌℃湁鐨勫睘鎬�
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 璁剧疆杈撳嚭鏃剁殑鏃堕棿鏍煎紡
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 鍒涘缓鍙緭鍑洪潪Null涓旈潪Empty(濡侺ist.isEmpty)鐨勫睘鎬у埌Json瀛楃涓茬殑Mapper,寤鸿鍦ㄥ閮ㄦ帴鍙ｄ腑浣跨敤.
	 */
	public static JsonMapper nonEmptyMapper() {
		return new JsonMapper(Include.NON_EMPTY);
	}

	/**
	 * 鍒涘缓鍙緭鍑哄垵濮嬪�艰鏀瑰彉鐨勫睘鎬у埌Json瀛楃涓茬殑Mapper, 鏈�鑺傜害鐨勫瓨鍌ㄦ柟寮忥紝寤鸿鍦ㄥ唴閮ㄦ帴鍙ｄ腑浣跨敤銆�
	 */
	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(Include.NON_DEFAULT);
	}

	/**
	 * Object鍙互鏄疨OJO锛屼篃鍙互鏄疌ollection鎴栨暟缁勩��
	 * 濡傛灉瀵硅薄涓篘ull, 杩斿洖"null".
	 * 濡傛灉闆嗗悎涓虹┖闆嗗悎, 杩斿洖"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
//			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 鍙嶅簭鍒楀寲POJO鎴栫畝鍗旵ollection濡侺ist<String>.
	 * 
	 * 濡傛灉JSON瀛楃涓蹭负Null鎴�"null"瀛楃涓�, 杩斿洖Null.
	 * 濡傛灉JSON瀛楃涓蹭负"[]", 杩斿洖绌洪泦鍚�.
	 * 
	 * 濡傞渶鍙嶅簭鍒楀寲澶嶆潅Collection濡侺ist<MyBean>, 璇蜂娇鐢╢romJson(String, JavaType)
	 * 
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
//			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 鍙嶅簭鍒楀寲澶嶆潅Collection濡侺ist<Bean>, 鍏堜娇鐢╟reateCollectionType()鎴朿ontructMapType()鏋勯�犵被鍨�, 鐒跺悗璋冪敤鏈嚱鏁�.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
//			logger.warn("parse json string error:" + jsonString, e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Description: 鏍规嵁娉涘瀷绫诲瀷
	 * @param json
	 * @param type
	 * @return
	 * @throws
	 */
	public <T> T fromJson(String jsonString, TypeReference<T> type) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		}
		try {
			return mapper.readValue(jsonString, type);
		} catch (Exception e) {
			e.printStackTrace();
//			logger.warn("parse json string error:" + jsonString, e);
			throw new RuntimeException("瑙ｆ瀽json閿欒");
		}
	}

	/**
	 * 鏋勯�燙ollection绫诲瀷.
	 */
	public JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
		return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

	/**
	 * 鏋勯�燤ap绫诲瀷.
	 */
	public JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
		return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}

	/**
	 * 鐣禞SON瑁″彧鍚湁Bean鐨勯儴鍒嗗爆鎬ф檪锛屾洿鏂颁竴鍊嬪凡瀛樺湪Bean锛屽彧瑕嗚搵瑭查儴鍒嗙殑灞��.
	 */
	public <T> T update(String jsonString, T object) {
		try {
			return (T) mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
//			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
//			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}

	/**
	 * 杓稿嚭JSONP鏍煎紡鏁告摎.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 瑷畾鏄惁浣跨敤Enum鐨則oString鍑芥暩渚嗚畝瀵獷num,
	 * 鐐篎alse鏅傛檪浣跨敤Enum鐨刵ame()鍑芥暩渚嗚畝瀵獷num, 榛樿獚鐐篎alse.
	 * 娉ㄦ剰鏈嚱鏁镐竴瀹氳鍦∕apper鍓靛缓寰�, 鎵�鏈夌殑璁�瀵嫊浣滀箣鍓嶈鐢�.
	 */
	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * 鏀寔浣跨敤Jaxb鐨凙nnotation锛屼娇寰桺OJO涓婄殑annotation涓嶇敤涓嶫ackson鑰﹀悎銆�
	 * 榛樿浼氬厛鏌ユ壘jaxb鐨刟nnotation锛屽鏋滄壘涓嶅埌鍐嶆壘jackson鐨勩��
	 */
	public void enableJaxbAnnotation() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		mapper.registerModule(module);
	}

	/**
	 * 鍙栧嚭Mapper鍋氳繘涓�姝ョ殑璁剧疆鎴栦娇鐢ㄥ叾浠栧簭鍒楀寲API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
