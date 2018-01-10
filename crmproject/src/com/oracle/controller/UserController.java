package com.oracle.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.JobRight;
import com.oracle.entity.Users;
import com.oracle.serviceImp.UserInfoServiceImp;
import com.oracle.serviceImp.UsersRoleInfoImp;
import com.oracle.util.Password;

@Controller
public class UserController {
	
	@Resource(name="UserInfoServiceImp")
	private UserInfoServiceImp userInfoServiceImp;
	
	@Resource(name="UsersRoleInfoImp")
	private UsersRoleInfoImp userRole;
	
	
	@RequestMapping(value="/userInfo")
	public ModelAndView getUserInfo(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		
		ModelAndView mav=new ModelAndView();
		HttpServletRequest request=req;
		String u_name=request.getParameter("u_name").trim();//�õ�������û���
		String u_password=request.getParameter("u_password").trim();//�õ��û�����
		System.out.println("�����������"+u_password);
		String pwd=Password.MD5EncodePass(u_password);//��������м���
		System.out.println("����֮�������"+pwd);
		HttpSession session =request.getSession();//�õ�HttpSession����
		Users user=userInfoServiceImp.getUserInfo(u_name,pwd);//�����ݿ��ѯ�û���Ϣ�Ƿ�ƥ��
		if(user!=null&&user.getU_wordstate().equals("��ְ")&&pwd.equals(user.getU_password())&&user.getU_state().equals("δ��¼")){
			//��Ϣƥ�䣬�û���״̬Ϊ��ְ�����ǲ����ظ���¼���ܽ�ȥ��ҳ
			long userid=user.getUidd();//�õ��û���id
			userInfoServiceImp.updateUserState(userid);
			Users users=userRole.getUserRoleInfo(userid);
			List<JobRight> list=users.getUserRight();
			session.setAttribute("user", user);
			mav.addObject("userRight", list);
			System.out.println(user.getRole().getR_name());
			mav.setViewName("index");
			return mav;
		}else{
			req.setAttribute("message", "�����ظ���¼������ְ");
			mav.setViewName("login");
		}
		return mav;
		
	}
	
	@RequestMapping(value="quit")
	public void quit(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		long id=Long.parseLong(req.getParameter("id"));
		userInfoServiceImp.destoryUpdate(id);//�����û���״̬����Ϊδ��¼
		req.getSession().invalidate();//����session
		resp.sendRedirect("jsp/login.jsp");
	}

}
