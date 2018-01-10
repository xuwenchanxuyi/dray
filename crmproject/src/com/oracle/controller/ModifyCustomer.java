package com.oracle.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.DeptPosition;
import com.oracle.entity.Users;
import com.oracle.serviceImp.ModifyUserImp;

@Controller
public class ModifyCustomer {
	
	@Resource(name="ModifyUserImp")
	private ModifyUserImp modify;
	
	
	@ResponseBody
	@RequestMapping(value="modifyCustomer")
	public Users ModifyCustomerController(HttpServletRequest req,HttpServletResponse resp){
		
		Users user = null;
		String uid=req.getParameter("uidd");
		long id;
		if(uid!=null){
			id=Long.parseLong(uid);
			user=modify.getUsersInfo(id);
		}else{
			System.out.println("�õ���IDΪ��");
		}
		return user;
	}
	
	@RequestMapping(value="resetPassword")
	public String resetPassword(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		
		System.out.println("resetPassword");
		
		
		
		String date="";
		
		String uid=req.getParameter("uid");
		if(uid!=null){
			long id=Long.parseLong(uid);
			
			long num=modify.resetPassword(id);
			
			if(num>0){
				date="1";
			}else{
				date="0";
			}
		}
		
		resp.getWriter().write(date);
		
		return null;
		
	}
	
	@ResponseBody
	@RequestMapping(value="delUser")
	public String delUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String result="";
		String id=req.getParameter("uid");
		long uid=Long.parseLong(id);
		Long num=modify.delUsers(uid);
		System.out.println("num:"+num);
		if( num!=null && num>0){
			result="1";
		}else{
			result="0";
		}
		resp.getWriter().write(result);
		System.out.println("result:"+result);
		return null;
	}
	
	
	@RequestMapping(value="savaModify")
	
	public String saveModify(HttpServletRequest req,HttpServletResponse resp) throws IOException{

		long roleId;
		long deptId = 0;
		System.out.println("�����޸���Ϣ");
		String result="1";//1���³ɹ�  2 ����ʧ��   3 ������Ϣ����
		List<Users> pos=null;
		Set<String> set=new HashSet<String>();
		List<DeptPosition> dpList=null;
		String uidd=req.getParameter("uidd").trim();//��ȡ�û�id
		String u_name=req.getParameter("u_name").trim();//��ȡ����
		String mobile=req.getParameter("mobile").trim();//��ȡ�ֻ���
		String u_wordstate=req.getParameter("u_wordstate").trim();//��ȡ����״̬
		String positon=req.getParameter("positon").trim(); //��ȡְλ
		String department=req.getParameter("department").trim();
		
		System.out.println("uidd:"+uidd+",u_name:"+u_name+",mobile:"+mobile+",u_wordstate:"+u_wordstate+",positon:"+positon+",department:"+department);
		
		
		if(uidd!=null && u_name!=null && mobile!=null && u_wordstate!=null && positon!=null && department!=null){
			if(department.length()>0){
				pos=modify.getPositionByDept(department);
				for(Users u:pos){
					deptId=Long.parseLong(u.getU_deptid());
					dpList=u.getDepartment().getDpList();
					for(DeptPosition dp:dpList){
						set.add(dp.getPosition());
					}
				}
			}
		}else{
			result="2";
		}
		
		if(set.contains(positon)){
			roleId=modify.getRoleByPosition(positon);
			long id=Long.parseLong(uidd);
			modify.updateUserInfo(id,u_name,mobile,u_wordstate,positon,roleId,deptId);
		}else{
			System.out.println("ѡ���"+department+"���Ų�����"+positon);
			result="3";
		}
		
		resp.getWriter().write(result);
		System.out.println("��־λ"+result);
		
		return null;
		
	}
	
	
}
