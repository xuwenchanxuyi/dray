package com.oracle.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.CustomInfo;
import com.oracle.entity.Customer;
import com.oracle.entity.Users;
import com.oracle.serviceImp.TelemarketerServiceImp;
import com.oracle.util.ToolUtil;

/**
 * 电销员工的控制器
 * @author oracleOAEC
 *
 */

@Controller
public class TelemarketerController {
	
	@Resource(name="TelemarketerServiceImp")
	private TelemarketerServiceImp telemark;
	
	
	@RequestMapping(value="getTelemarketerCustomer")
	@ResponseBody
	public Object getTelemarketerCustomer(HttpServletRequest req,HttpServletResponse resp){
		ModelAndView mav=new ModelAndView();
		Map<String,Object> map;
		HttpSession session=req.getSession();//得到session
		Users user=(Users) session.getAttribute("user");//得到session存储的登录对象信息
		if(user==null){
			mav.setViewName("login.jsp");
			return mav;
		}else{
			long id=user.getUidd();//得到电销员工的id
			System.out.println("id:"+id);
			int currentPage =Integer.parseInt(ToolUtil.isEmpty(req.getParameter("offest"))? "0":req.getParameter("offest"));
			int pageSize=Integer.parseInt(ToolUtil.isEmpty(req.getParameter("limit"))? "10":req.getParameter("limit"));
			map=telemark.getTelemarketerCustomer(currentPage, pageSize, id);
		}
		return map;
	} 
	
	@RequestMapping(value="TelemarketerGetCustomerInfoById")
	@ResponseBody
	public Customer TelemarketerGetCustomerInfoById(HttpServletRequest req,HttpServletResponse resp){
		
		String ccid=req.getParameter("ccid");
		long id=Long.parseLong(ccid);
		Customer cus=telemark.TelGetCustomerInfoById(id);
		
		return cus;
		
	}
	
	@RequestMapping(value="TelemarketerUpdateInfo")
	public String TelemarketerUpdateInfo(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		
		String result="";
		String ccid=req.getParameter("ccid");
		long id=Long.parseLong(ccid);
		String state=req.getParameter("state");
		System.out.println(id+"----------"+state);
		long num=telemark.TelemarketerUpdateCustomInfo(id,state);
		if(num>0){
			result="1";
		}else{
			result="0";
		}
		resp.getWriter().write(result);
		return null;
		
	}
	
	@RequestMapping("MonthStatistics")
	@ResponseBody
	public Object MonthStatistics(HttpServletRequest req,HttpServletResponse resp){
		
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
		Users user=(Users) req.getSession().getAttribute("user");
		List<CustomInfo> list=telemark.getStateAllNum(user.getUidd());
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
