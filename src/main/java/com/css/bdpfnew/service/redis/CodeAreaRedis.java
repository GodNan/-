package com.css.bdpfnew.service.redis;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.css.bdpfnew.model.entity.bdpfnew.CodeAreaEntity;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

@Repository
public class CodeAreaRedis {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	public void add(String key,Long time,CodeAreaEntity codeArea){
	//	Gson gson=new Gson();
		redisTemplate.opsForValue().set(key, JSON.toJSONString(codeArea), time, TimeUnit.MINUTES);
	}
	
	public void add(String key,Long time,List<CodeAreaEntity> codeAreas){
	//	Gson gson=new Gson();
		redisTemplate.opsForValue().set(key, JSON.toJSONString(codeAreas), time, TimeUnit.MINUTES);
	}
	
	public CodeAreaEntity get(String key){
	//	Gson gson=new Gson();
		CodeAreaEntity codeArea=null;
		String json=redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(json)){
			codeArea=JSON.parseObject(json, CodeAreaEntity.class);
		}
		return codeArea;
	}
	
	public List<CodeAreaEntity> getList(String key){
		//Gson gson=new Gson();
		List<CodeAreaEntity> ts=null;
		String listJson = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(listJson)){
			//ts=gson.fromJson(listJson, new TypeToken<List<CodeAreaEntity>>(){}.getType());
		}
		return ts;
	}
	
	public void delete(String key){
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	

}
