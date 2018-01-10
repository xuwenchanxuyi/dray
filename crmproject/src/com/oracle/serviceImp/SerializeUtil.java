/*package com.oracle.serviceImp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component("SerializeUtil")
public class SerializeUtil {
	
	*//** 
     * 序列化 Object
     * @param object 
     * @return 
	 * @throws IOException 
     *//*  
	@Resource(name="redisTemplate")
	private RedisTemplate redisTemplate;
	
	
    public  byte[] serialize(Object object) throws IOException {  
        if (object == null) {  
            return null;  
        }  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        byte[] bytes = null;  
        try {  
            // 序列化  
            baos = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(baos);  
            oos.writeObject(object);  
            bytes = baos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            oos.close();
            baos.close();
             
        }  
        return bytes;  
    }  
    
    *//** 
     * 反序列化  Object
     *  
     * @param bytes 
     * @return 
     * @throws IOException 
     *//*  
    public  Object unserialize(byte[] bytes) throws IOException {  
        if (bytes == null) {  
            return null;  
        }  
        ByteArrayInputStream bais = null;  
        ObjectInputStream ois = null;  
        try {  
            // 反序列化  
            bais = new ByteArrayInputStream(bytes);  
            ois = new ObjectInputStream(bais);  
            return ois.readObject();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
           bais.close();
           ois.close();
        }  
        return null;  
    }  
    
    
    *//** 
     * 序列化 list 集合 
     *  
     * @param list 
     * @return 
     * @throws IOException 
     *//*  
    public  byte[] serializeList(List<?> list) throws IOException {  
  
        if (list==null) {  
            return null;  
        }  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        byte[] bytes = null;  
        try {  
            baos = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(baos);  
            for (Object obj : list) {  
                oos.writeObject(obj);  
            }  
            bytes = baos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
          oos.close();
          baos.close();
        }  
        return bytes;  
    }  
    
    *//** 
     * 反序列化 list 集合 
     *  
     * @param lb 
     * @return 
     * @throws IOException 
     *//*  
    public  List<?> unserializeList(byte[] bytes) throws IOException {  
        if (bytes == null) {  
            return null;  
        }  
  
        List<Object> list = new ArrayList<Object>();  
        ByteArrayInputStream bais = null;  
        ObjectInputStream ois = null;  
        try {  
            // 反序列化  
            bais = new ByteArrayInputStream(bytes);  
            ois = new ObjectInputStream(bais);  
            while (bais.available() > 0) {  
                Object obj = (Object) ois.readObject();  
                if (obj == null) {  
                    break;  
                }  
                list.add(obj);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            bais.close();
            ois.close();
        }  
        return list;  
    } 
   
    public  void setObject(String key ,Object obj){  
        try {  
            obj = obj == null ? new Object():obj;  
            redisTemplate.opsForValue().set(key.getBytes(),serialize(obj));
            //getJedis().set(key.getBytes(), SerializeUtil.serialize(obj));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
    public  Object getObject(String key) throws IOException{  
        if(!exists(key)){  
            return null;  
        }  
        byte[] data = (byte[]) redisTemplate.opsForValue().get(key.getBytes());  
        return (Object)unserialize(data);  
    }  
    
    public  boolean exists(String key){
    //	boolean flag=true;
    	//Object obj=redisTemplate.opsForValue().get(key);
    	return redisTemplate.hasKey(key);
    	if(obj!=null){
    		flag=false; 
    	}else{
    		flag=true;
    	}
    	return flag;
    }
    
    
    *//** 
     * 设置List集合 
     * @param key 
     * @param list 
     *//*  
    public  void setList(String key ,List<?> list){  
     try {  
           
         if(list!=null){  
        	 
        	 redisTemplate.opsForList().rightPush(key,serializeList(list));  
         }else{//如果list为空,则设置一个空  
        	 redisTemplate.opsForList().rightPush(key, "".getBytes());  
         }  
     } catch (Exception e) {  
         e.printStackTrace();  
     }  
    }  
    
    public  List<?> getList(String key) throws IOException{  
        if(!exists(key)){  
            return null;  
        }  
        byte[] data = (byte[]) redisTemplate.opsForList().rightPop(key.getBytes());  
        return unserializeList(data);  
    }  
    
}
*/