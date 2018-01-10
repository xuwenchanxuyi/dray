package com.oracle.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.DeptPosition;
import com.oracle.entity.Users;
import com.oracle.entity.UsersInfo;
import com.oracle.mapper.DeptPositionMapper;
import com.oracle.mapper.UsersMapper;
import com.oracle.serviceImp.UsersInfoServiceImp;
import com.oracle.util.JsonMapper;
import com.oracle.util.Password;
import com.oracle.util.ToolUtil;


@Controller
public class adminController {

	JsonMapper json=new JsonMapper();
	
	@Resource(name="UsersInfoServiceImp")
	private UsersInfoServiceImp usersInfoService;
	
	@Resource(name="deptPositionMapper")
	private DeptPositionMapper deptPositionMapper;
	
	@Resource(name="usersMapper")
	private UsersMapper usersMapper;
	@ResponseBody
	@RequestMapping(value="getUsersInfo")
	public Object getAdminManagerUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		ModelAndView mav=new ModelAndView();
		HttpServletRequest request=req;
		HttpServletResponse response = resp;
		String userName=request.getParameter("userName");//获取搜索框中的值
		int currentPage =Integer.parseInt(ToolUtil.isEmpty(request.getParameter("offest"))? "0":request.getParameter("offest"));
		int pageSize=Integer.parseInt(ToolUtil.isEmpty(request.getParameter("limit"))? "10":request.getParameter("limit"));
		Map<String, Object> map = usersInfoService.getAllUsersInfo(userName, currentPage, pageSize);
		System.out.println(map);
		String date=json.toJson(map);
		return map;
		
		/*mav.addObject("usersInfo", usersInfoService.getAllUsersInfo());
		for(UsersInfo u:usersInfoService.getAllUsersInfo()){
			System.out.println(u.getDept_name()+u.getMobile()+u.getR_name()+u.getU_wordstate());
		}
		
		System.out.println(usersInfoService.getAllUsersInfo());
		
		return usersInfoService.getAllUsersInfo();*/
		
	}
	
	@RequestMapping("getAdminPosition")
	@ResponseBody
	public List<DeptPosition> getAdminPosition(HttpServletRequest req,HttpServletResponse resp){
		String dept=req.getParameter("sel");
		List<DeptPosition> list=deptPositionMapper.searchPosition(dept);
		return list;
		
	}
	
	@RequestMapping("savaUser")
	public String savaUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String result="0";
		String name=req.getParameter("name");
		String mobile=req.getParameter("mobile");
		String sex=req.getParameter("sex");
		String dept=req.getParameter("dept");
		String position=req.getParameter("position");
		DeptPosition dp=deptPositionMapper.adminSaveUser(position);
		long rid=dp.getRole().getRid();
		long deptid=dp.getDepartment().getDid();
		String pwd=Password.MD5EncodePass("123456");
		System.out.println(name+":"+mobile+":"+sex+":"+dept+":"+position+":角色"+rid+":部门"+deptid);
		Users user=new Users();
		user.setRid(rid);
		user.setDeptId(deptid);
		user.setU_password(pwd);
		user.setU_name(name);
		user.setMobile(mobile);
		user.setSex(sex);
		user.setPositon(position);
		long num=usersMapper.savaUsers(user);
		if(num>0){
			result="1";
		}
		resp.getWriter().write(result);
		return null;
		
	}
	
}
