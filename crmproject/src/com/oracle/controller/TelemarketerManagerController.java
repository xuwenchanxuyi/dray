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
 * �������ܿ����������ܸ��������Կ�������Ա��������Ա��������Ϊ���������ṩ����
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
		//����һ��ModelAndView����
		ModelAndView mav=new ModelAndView();
		String result="1";
		String fileName=myFile.getOriginalFilename();//��ȡ�ļ���ԭ��
		System.out.println("�ϴ��ļ�"+fileName);
		//�����ϴ��ļ���·��
		String realPath=req.getServletContext().getRealPath("upload");
		System.out.println(realPath);
		//�����ļ�
		File file=new File(realPath,fileName);
		if(!file.getParentFile().exists()){//����ļ������ھʹ����ļ�
			file.getParentFile().mkdirs();
		}
		try {
			myFile.transferTo(file);//���ļ��ϴ���ָ��λ��
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
		System.out.println("�ϴ���tomcat��·��"+file.getPath());
		return mav;
		
	}
	
	@RequestMapping("tongji")
	@ResponseBody
	public Object MonthStatistics(HttpServletRequest req,HttpServletResponse resp){
		long id=Long.parseLong(req.getParameter("id"));
		long receive = 0,noReceive = 0,dieNumber = 0,number = 0,Record = 0,TelNumber = 0,NetNumber = 0;
		System.out.println("------------");
		Map<String,Object> returnMap=new HashMap<String,Object>();
		//legendData list���ϴ�ű������� δͨ ����  ���� �ܷ���
		List<String> legendData=new ArrayList<String>();
		legendData.add("��������");
		legendData.add("δͨ");
		legendData.add("����");
		legendData.add("����");		
		legendData.add("��¼��Ч");
		returnMap.put("legendData", legendData);
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> seriesData=new ArrayList<Map<String,Object>>();
		/*Users user=(Users) req.getSession().getAttribute("user");*/
		List<CustomInfo> list=telemark.getStateAllNum(id);
		for(CustomInfo cus:list){
			System.out.println(cus.getC_state()+":"+cus.getAllNumber());
			if(cus.getC_state().equals("����")){
				receive=cus.getAllNumber();
			}else if(cus.getC_state().equals("δͨ")){
				noReceive=cus.getAllNumber();
			}else if(cus.getC_state().equals("����")){
				dieNumber=cus.getAllNumber();
			}else if(cus.getC_state().equals("����")){
				number=cus.getAllNumber();
			}else if(cus.getC_state().equals("��¼��Ч")){
				Record=cus.getAllNumber();
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
		
		map=new HashMap<String,Object>();
		map.put("name", "��¼��Ч");
		map.put("value", Record);
		seriesData.add(map);
		returnMap.put("seriesData", seriesData);
		return returnMap;
	}

}
