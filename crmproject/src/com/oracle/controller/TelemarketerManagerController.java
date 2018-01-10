package com.oracle.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.CustomInfo;
import com.oracle.entity.Users;
import com.oracle.serviceImp.TelemarketerManagerServiceImp;
import com.oracle.serviceImp.TelemarketerServiceImp;
import com.oracle.util.ToolUtil;

/**
 * 电销主管控制器，功能概述：可以看到电销员工和网咨员工，并且为销售助理提供数据
 * @author oracleOAEC
 *
 */
@Controller
public class TelemarketerManagerController {
	
	@Resource(name="TelemarketerManagerServiceImp")
	private TelemarketerManagerServiceImp telManager;
	
	@Resource(name="TelemarketerServiceImp")
	private TelemarketerServiceImp telemark;
	@RequestMapping(value="TelemarketerManager")
	@ResponseBody
	public Object TelemarketerManager(HttpServletRequest req,HttpServletResponse resp){
		
		ModelAndView mav=new ModelAndView();
		int currentPage =Integer.parseInt(ToolUtil.isEmpty(req.getParameter("offest"))? "0":req.getParameter("offest"));
		int pageSize=Integer.parseInt(ToolUtil.isEmpty(req.getParameter("limit"))? "10":req.getParameter("limit"));
		
		Map<String,Object> map=telManager.getTelemarketerManagerUsers(currentPage, pageSize);
		
		return map;
		
	}
	
	@RequestMapping("uploadFile")
	private ModelAndView uploadFile(MultipartFile myFile,HttpServletRequest req,HttpServletResponse resp) throws IOException{
		//定义一个ModelAndView对象
		ModelAndView mav=new ModelAndView();
		String result="1";
		String fileName=myFile.getOriginalFilename();//获取文件的原名
		System.out.println("上传文件"+fileName);
		//设置上传文件的路径
		String realPath=req.getServletContext().getRealPath("upload");
		System.out.println(realPath);
		//关联文件
		File file=new File(realPath,fileName);
		if(!file.getParentFile().exists()){//如果文件不存在就创建文件
			file.getParentFile().mkdirs();
		}
		try {
			myFile.transferTo(file);//将文件上传到指定位置
		} catch (IOException e) {
			result="0";
			e.printStackTrace();
		}finally{
			if(result.equals("1")){
				mav.setViewName("success");
			}else{
				mav.setViewName("fail");
			}
		}
		System.out.println("上传到tomcat的路径"+file.getPath());
		return mav;
		
	}
	
	@RequestMapping("tongji")
	@ResponseBody
	public Object MonthStatistics(HttpServletRequest req,HttpServletResponse resp){
		long id=Long.parseLong(req.getParameter("id"));
		long receive = 0,noReceive = 0,dieNumber = 0,number = 0,Record = 0,TelNumber = 0,NetNumber = 0;
		System.out.println("------------");
		Map<String,Object> returnMap=new HashMap<String,Object>();
		//legendData list集合存放本月上门 未通 死单  紧跟 总分配
		List<String> legendData=new ArrayList<String>();
		legendData.add("本月上门");
		legendData.add("未通");
		legendData.add("死单");
		legendData.add("紧跟");		
		legendData.add("记录无效");
		returnMap.put("legendData", legendData);
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> seriesData=new ArrayList<Map<String,Object>>();
		/*Users user=(Users) req.getSession().getAttribute("user");*/
		List<CustomInfo> list=telemark.getStateAllNum(id);
		for(CustomInfo cus:list){
			System.out.println(cus.getC_state()+":"+cus.getAllNumber());
			if(cus.getC_state().equals("上门")){
				receive=cus.getAllNumber();
			}else if(cus.getC_state().equals("未通")){
				noReceive=cus.getAllNumber();
			}else if(cus.getC_state().equals("死单")){
				dieNumber=cus.getAllNumber();
			}else if(cus.getC_state().equals("紧跟")){
				number=cus.getAllNumber();
			}else if(cus.getC_state().equals("记录无效")){
				Record=cus.getAllNumber();
			}
		}
		map.put("name", "本月上门");
		map.put("value", receive);
		seriesData.add(map);
		map=new HashMap<String,Object>();
		map.put("name", "未通");
		map.put("value", noReceive);
		seriesData.add(map);
		map=new HashMap<String,Object>();
		map.put("name", "死单");
		map.put("value", dieNumber);
		seriesData.add(map);
		map=new HashMap<String,Object>();
		map.put("name", "紧跟");
		map.put("value", number);
		
		map=new HashMap<String,Object>();
		map.put("name", "记录无效");
		map.put("value", Record);
		seriesData.add(map);
		returnMap.put("seriesData", seriesData);
		return returnMap;
	}

}
