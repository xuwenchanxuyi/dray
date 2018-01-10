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
		String u_name=request.getParameter("u_name").trim();//得到输入的用户名
		String u_password=request.getParameter("u_password").trim();//得到用户秘买
		System.out.println("我输入的密码"+u_password);
		String pwd=Password.MD5EncodePass(u_password);//将密码进行加密
		System.out.println("加密之后的密码"+pwd);
		HttpSession session =request.getSession();//得到HttpSession对象
		Users user=userInfoServiceImp.getUserInfo(u_name,pwd);//从数据库查询用户信息是否匹配
		if(user!=null&&user.getU_wordstate().equals("在职")&&pwd.equals(user.getU_password())&&user.getU_state().equals("未登录")){
			//信息匹配，用户的状态为在职，且是不是重复登录才能进去首页
			long userid=user.getUidd();//得到用户的id
			userInfoServiceImp.updateUserState(userid);
			Users users=userRole.getUserRoleInfo(userid);
			List<JobRight> list=users.getUserRight();
			session.setAttribute("user", user);
			mav.addObject("userRight", list);
			System.out.println(user.getRole().getR_name());
			mav.setViewName("index");
			return mav;
		}else{
			req.setAttribute("message", "您已重复登录或者离职");
			mav.setViewName("login");
		}
		return mav;
		
	}
	
	@RequestMapping(value="quit")
	public void quit(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		long id=Long.parseLong(req.getParameter("id"));
		userInfoServiceImp.destoryUpdate(id);//更改用户的状态，改为未登录
		req.getSession().invalidate();//销毁session
		resp.sendRedirect("jsp/login.jsp");
	}

}
