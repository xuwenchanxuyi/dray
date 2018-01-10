package com.oracle.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.ConsultRecord;
import com.oracle.entity.Users;
import com.oracle.mapper.CustomerMapper;
import com.oracle.entity.Customer;
import com.oracle.serviceImp.CounselorServiceImp;
import com.oracle.util.ToolUtil;

/**
 * ×ÉÑ¯Ê¦¿ØÖÆÆ÷
 * @author oracleOAEC
 *
 */
@Controller
public class CounselorController {
	
	@Resource(name="CounselorServiceImp")
	private CounselorServiceImp coun;
	
	@Resource(name="customerMapper")
	private CustomerMapper customerMapper;
	
	@RequestMapping(value="getCounselorById")
	@ResponseBody
	public Object getCounselorById(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		ModelAndView mav=new ModelAndView();
		Map<String,Object> map;
		Users user=(Users) req.getSession().getAttribute("user");
		if(user==null){
			mav.setViewName("login.jsp");
			resp.sendRedirect("login.jsp");
			return mav;
		}else{
			//long userid=user.getUidd();
			int currentPage =Integer.parseInt(ToolUtil.isEmpty(req.getParameter("offest"))? "0":req.getParameter("offest"));
			int pageSize=Integer.parseInt(ToolUtil.isEmpty(req.getParameter("limit"))? "10":req.getParameter("limit"));
			map=coun.getConselorById(currentPage, pageSize,user.getUidd());
		}
		return map;
		
	}
	
	@RequestMapping("CounselorModifyCustomer")
	public Object modifyCustomer(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String result="";
		Users user=(Users) req.getSession().getAttribute("user");
		ModelAndView mav=new ModelAndView();
		long num,userid;
		if(user==null){
			mav.setViewName("login.jsp");
			return mav;
		}else{
			String ccid=req.getParameter("ccid");
			userid=Long.parseLong(ccid);
			String c_state=req.getParameter("c_state");
			String reason=req.getParameter("reason").trim();
			String linktime=req.getParameter("linkTime").trim();
			System.out.println(ccid+":"+c_state+":"+reason+":"+linktime);
			num=coun.updateConsultRecord(userid,c_state,reason,linktime,user.getUidd());
		}
		
		
		if(num>0){
			result="1";
		}else{
			result="0";
		}
		resp.getWriter().write(result);
		return null;
		
	}
	
	@RequestMapping("timeSearch")
	public String timeSearch(HttpServletRequest req,HttpServletResponse resp) throws IOException, ParseException{
	
		System.out.println("timeSearch-------------");
		String result="1";
		String time=req.getParameter("time");
		SimpleDateFormat sdf=new SimpleDateFormat ("yyyy-MM-dd");
		Date date=sdf.parse(time);
		
		Customer cus=customerMapper.timeSearch(time);
		long num=cus.getNums();
		System.out.println("num:"+num);
		if(num>0){
			result="0";
		}
		resp.getWriter().write(result);
		
		return null;
		
	}
	

}
