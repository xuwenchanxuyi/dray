package com.oracle.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.entity.ConsultRecord;
import com.oracle.entity.CustomInfo;
import com.oracle.entity.Customer;
import com.oracle.entity.Users;
import com.oracle.mapper.CustomerMapper;
import com.oracle.serviceImp.AssistantSearchAllCustomer;
import com.oracle.util.ExcelUtil;
import com.oracle.util.ToolUtil;

/**
 * 完成销售助理功能的控制器
 * @author oracleOAEC
 *
 */

@Controller
public class AssistantController implements Serializable{
	@Resource(name="AssistantSearchAllCustomer")
	private AssistantSearchAllCustomer cus;
	/**
	 * 存放报表的表名，返回表名来下载
	 */
	
	/**
	 * 销售助理登录之后可以看见所有的未分配的客户
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="getAllCustomer")
	@ResponseBody
	public Map<String,Object> getAllCutomer(HttpServletRequest req,HttpServletResponse resp){
		
		ModelAndView mav=new ModelAndView();
		/**
		 * 从前台得到当前页面和页面显示的客户的数量
		 */
		System.out.println("销售助理-----");
		int currentPage =Integer.parseInt(ToolUtil.isEmpty(req.getParameter("offest"))? "0":req.getParameter("offest"));
		int pageSize=Integer.parseInt(ToolUtil.isEmpty(req.getParameter("limit"))? "10":req.getParameter("limit"));
		//将从service层得到的数据放进Map集合，
		Map<String,Object> map=(Map<String, Object>) cus.getAllCustomer(currentPage, pageSize);
		
		return map;
		
	}
	/**
	 * 销售助理为网咨员工和电销员工分配客户，首先从后台查询到电销员工和网咨员工
	 * @return
	 */

	@RequestMapping(value="getUsers")
	@ResponseBody
	public List<Users> getUsers(){
		List<Users> list=cus.getUsers();
		for(Users u:list){
			System.out.println(u.getU_name());
		}
		return list;
	}
	
	@RequestMapping(value="allotUsers")
	@Transactional
	public String allotUsers(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String sel=req.getParameter("user").trim();//得到指定分配的员工
		long userId = 0;//初始化员工的id
		if(sel!=null&&sel.length()>0){
			userId=cus.getUsersIdByName(sel);//根据员工的姓名查询员工的id
		}
		String result="";//返回结果
		String[] str=req.getParameterValues("selCustomer");//得到前端页面传回来的客户的id
		//List<Long> idlist=new ArrayList<Long>();
		long [] arr=new long[str.length];//定义一个数组,存放客户的id
		//List<CustomInfo> list=new ArrayList<CustomInfo>();//定义一个集合存放CustomInfo对象，
		long number=0,number1=0;												//以便对CustomInfo表进行批量插入
		int num=str.length;//得到传过来的客户id的数量
		for(int i=0;i<num;i++){//使用循环创建CustomInfo对象并放进集合中
			arr[i]=Long.parseLong(str[i]);
			CustomInfo c=new CustomInfo();
			c.setCustomerId(arr[i]);
			c.setUserId(userId);
			number=cus.insertCustomerInfo(c);//将分配的数据加入到CustomInfo表中
			number1=cus.updateCustomerAllotById(arr[i]);
			
		}
		//调用方法进行批量插入
		if(number>0){//判断是否插入成功
			result="1";//前台通过ajax判断1或0提示是否插入成功
		}else{
			result="0";
		}
		resp.getWriter().write(result);//将结果写回前台
		return null;
	}
	
	/**
	 * 当销售助理点击编辑的时候,弹出修改的模态框显示用户的基本 信息，此方法就是修改前的一个查询
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="searchCustomer")
	@ResponseBody
	public Customer searchCustomer(HttpServletRequest req,HttpServletResponse resp){
		
		String ccid=req.getParameter("ccid");
		Customer customer=null;
		long id=0;
		if(ccid!=null){
			id=Long.parseLong(ccid);
			customer=cus.searchCustomerById(id);
			
		}
		return customer;
		
	}
	/**
	 * 保存销售助理修改客户的方法
	 * @param customer
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="assistantSavaModify")
	public String savaModify(Customer customer,HttpServletResponse resp) throws IOException{
		ModelAndView mav=new ModelAndView();
		
		String result="";
		long num=cus.assistantUpdateCustomer(customer);
		if(num>0){
			result="1";
		}else{
			result="0";
		}
		resp.getWriter().write(result);
		
		return null;
		
	}
	/**
	 * 销售助理的导入数据方法
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="intoData",method=RequestMethod.POST)
	public ModelAndView intoData(HttpServletRequest req,HttpServletResponse resp){
		ModelAndView mav=new ModelAndView();
		/**
		 * 得到文件名，这个文件是销售助理的上级已经上传之后的文件，要将他提前上传到Tomcat服务器里面，否则是不能直接
		 * 从本地得到的，在上传文件的时候通过form表单的enctype="multipart/form-data",这个里面有文件的缓冲区
		 * 和关联文件的一个文件流并将其转为二进制文件上传到服务器
		 */
		String fileName=req.getParameter("myFile");//得到上传的文件名
		System.out.println(fileName);
		String path=req.getServletContext().getRealPath("upload");//得到要存放文件的路径
		File file=new File(path,fileName);//创建一个File对象与文件进行关联
		String filePath=file.getPath();//得到文件的存储路径
		System.out.println(filePath);
		//String path=req.getParameter("path");
		List<Customer> list;
		long num;
		try {
			list=ExcelUtil.getExcelObject(filePath);//对Excel文件进行解析，并将解析的对象存放到List结合中
			for(Customer cu:list){//遍历List集合，循环插入到表格中
				num=cus.assistantAddCustomerByFile(cu);//
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(path);
		return null;
		
	}
	
	@RequestMapping("getCounselor")
	@ResponseBody
	public List<Users> getCounselor(){
		List<Users> list=cus.getCounselor();
		return list;
	}
	
	@RequestMapping("allotCounselor")
	public String allotCounselor(HttpServletRequest req,HttpServletResponse resp){
		String Counselor=req.getParameter("Counselor").trim();
		long id=0;
		if(Counselor!=null&&Counselor.length()>0){
			id=cus.getUsersIdByName(Counselor);
		}
		String[] cusArr=req.getParameterValues("counselorarr");
		for(String str:cusArr){
			long cusid=Long.parseLong(str);
			ConsultRecord cr=new ConsultRecord();
			cr.setCr_userid(id);
			cr.setCr_customerid(cusid);
			long nun=cus.insertConsultRecord(cr);//将数据插入到ConsultRecord表格中
			long num2=cus.updateCustomerAllotById(cusid);
		}
		
		return null;
		
	}
	/**
	 * 销售助理产生报表
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("reportFile")
	public String reportFile(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		System.out.println("------------------");
		
		String result="1";
		List<Customer> list=cus.assistantReportFile();
		if(list.size()>0){
			for(Customer cus:list){
				System.out.println(cus.getC_name());
			}
			String path=req.getServletContext().getRealPath("upload");
			File file=new File(path,"report.xlsx");
			System.out.println("文件名"+file.getName());
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			OutputStream out=new FileOutputStream(file);
			ExcelUtil.exportExcelX(out, list);
		}else{
			result="0";
		}
		resp.getWriter().write(result);
		return null;
		
	}
	
	/**
	 * 下载报表
	 */
	@RequestMapping("download")
	public ResponseEntity  download(HttpServletRequest req,HttpServletResponse resp){
		String report=req.getParameter("report");
		System.out.println(report);
		HttpHeaders responseHeaders=new HttpHeaders();
		//下载的附件类型
		responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		responseHeaders.setContentDispositionFormData("attachment", report);
		String path=req.getServletContext().getRealPath("upload");
		File file=new File(path,report);
		if(file.exists()){
			try{
				return new ResponseEntity(FileUtils.readFileToByteArray(file),responseHeaders,HttpStatus.CREATED);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("文件不存在");
		}
		
		
		return null;
		
	}
}
