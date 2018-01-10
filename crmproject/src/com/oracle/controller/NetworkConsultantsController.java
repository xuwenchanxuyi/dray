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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.CustomInfo;
import com.oracle.entity.Customer;
import com.oracle.entity.Users;
import com.oracle.serviceImp.NetworkConsultantsServiceImp;
import com.oracle.util.ToolUtil;

/**
 * ����Ա����Controller
 * @author oracleOAEC
 *
 */
@Controller
public class NetworkConsultantsController {
	@Resource(name="NetworkConsultantsServiceImp")
	private NetworkConsultantsServiceImp netImp;
	
	@RequestMapping(value="getNetwordCustomer")
	@ResponseBody
	public Object getNetwordCustomer(HttpServletRequest req,HttpServletResponse resp){
		ModelAndView mav=new ModelAndView();
		int currentPage =Integer.parseInt(ToolUtil.isEmpty(req.getParameter("offest"))? "0":req.getParameter("offest"));
		int pageSize=Integer.parseInt(ToolUtil.isEmpty(req.getParameter("limit"))? "10":req.getParameter("limit"));
		Map<String,Object> map=new HashMap<String,Object>();
		Users user=(Users) req.getSession().getAttribute("user");
		if(user!=null){
			map=(Map<String, Object>) netImp.getNetwordConsultCustomer(currentPage, pageSize, user.getU_name());
			return map;
		}else{
			mav.setViewName("jsp/login.jsp");
			return mav;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="NetworkConsultantsGetCustomer",method=RequestMethod.GET)
	public Customer getCustomer(HttpServletRequest req,HttpServletResponse resp){
		
		long id=Long.parseLong(req.getParameter("ccid"));
		Customer customer=netImp.NetworkConsultantsCustomer(id);
		return customer;
		
	}
	
	@RequestMapping(value="NetworkConsultantsModify")
	public String networkConsultantsModify(HttpServletRequest req,HttpServletResponse rsep) throws IOException{
		long id=Long.parseLong(req.getParameter("ccid"));
		String result="0";
		String state=req.getParameter("c_state");
		long number=netImp.NetworkConsultantsUpdate(id,state);
		if(number>0){
			result="1";
		}
		rsep.getWriter().write(result);
		return null;
		
	}
	/**
	 * ����Ա���ı���ͳ��
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="networdConsultantsStatistics",method=RequestMethod.GET)
	@ResponseBody
	public Object networdConsultantsStatistics(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		Users user=(Users) req.getSession().getAttribute("user");
		if(user!=null){
			long receive = 0,newCustomer=0,noReceive = 0,dieNumber = 0,number = 0,Record = 0,TelNumber = 0,NetNumber = 0;
			System.out.println("------------");
			Map<String,Object> returnMap=new HashMap<String,Object>();
			//legendData list���ϴ�ű������� δͨ ����  ���� �ܷ���
			List<String> legendData=new ArrayList<String>();
			legendData.add("��������");
			legendData.add("δͨ");
			legendData.add("����");
			legendData.add("����");		
			legendData.add("��¼��Ч");
			legendData.add("����δ����");
			returnMap.put("legendData", legendData);
			Map<String,Object> map=new HashMap<String,Object>();
			List<Map<String,Object>> seriesData=new ArrayList<Map<String,Object>>();
			/*Users user=(Users) req.getSession().getAttribute("user");*/
			List<Customer> list=netImp.networdConsultantsStatistics(user.getU_name());
			for(Customer cus:list){
				System.out.println(cus.getC_state()+":"+cus.getNums());
				if(cus.getC_state().equals("����")){
					receive=cus.getNums();
				}else if(cus.getC_state().equals("δͨ")){
					noReceive=cus.getNums();
				}else if(cus.getC_state().equals("����")){
					dieNumber=cus.getNums();
				}else if(cus.getC_state().equals("����")){
					number=cus.getNums();
				}else if(cus.getC_state().equals("��¼��Ч")){
					Record=cus.getNums();
				}else if(cus.getC_state().equals("����δ����")){
					newCustomer=cus.getNums();
				}
			}
			map.put("name", "��������");
			map.put("value", receive);
			seriesData.add(map);
			map=new HashMap<String,Object>();
			map.put("name", "δͨ");
			map.put("value", noReceive);
			seriesData.add(map);
			map=new HashMap<String,Object>();
			map.put("name", "����");
			map.put("value", dieNumber);
			seriesData.add(map);
			map=new HashMap<String,Object>();
			map.put("name", "����");
			map.put("value", number);
			seriesData.add(map);
			map=new HashMap<String,Object>();
			map.put("name", "��¼��Ч");
			map.put("value", Record);
			map=new HashMap<String,Object>();
			map.put("name", "����δ����");
			map.put("value", newCustomer);
			seriesData.add(map);
			returnMap.put("seriesData", seriesData);
			return returnMap;
			
		}else{
			resp.sendRedirect("jsp/login.jsp");
		}
		return "";

		
		
		
		
		
	}

}
