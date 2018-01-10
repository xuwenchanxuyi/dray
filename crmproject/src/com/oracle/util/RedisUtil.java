package com.oracle.util;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
	
	@Resource(name="redisTemplate")
	private RedisTemplate redisTemplate;
	
	JsonMapper json=new JsonMapper();
	/*public void setObject(String key,Object obj){
		redisTemplate.opsForValue().set(key, json.toJson(obj));
	}
	
	public Object getObject(String key){
		return json.fromJson(redisTemplate.opsForValue().get(key),Object.class);
	}*/

}
