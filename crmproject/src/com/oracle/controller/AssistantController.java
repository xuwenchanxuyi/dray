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
 * ������������ܵĿ�����
 * @author oracleOAEC
 *
 */

@Controller
public class AssistantController implements Serializable{
	@Resource(name="AssistantSearchAllCustomer")
	private AssistantSearchAllCustomer cus;
	/**
	 * ��ű���ı��������ر���������
	 */
	
	/**
	 * ���������¼֮����Կ������е�δ����Ŀͻ�
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="getAllCustomer")
	@ResponseBody
	public Map<String,Object> getAllCutomer(HttpServletRequest req,HttpServletResponse resp){
		
		ModelAndView mav=new ModelAndView();
		/**
		 * ��ǰ̨�õ���ǰҳ���ҳ����ʾ�Ŀͻ�������
		 */
		System.out.println("��������-----");
		int currentPage =Integer.parseInt(ToolUtil.isEmpty(req.getParameter("offest"))? "0":req.getParameter("offest"));
		int pageSize=Integer.parseInt(ToolUtil.isEmpty(req.getParameter("limit"))? "10":req.getParameter("limit"));
		//����service��õ������ݷŽ�Map���ϣ�
		Map<String,Object> map=(Map<String, Object>) cus.getAllCustomer(currentPage, pageSize);
		
		return map;
		
	}
	/**
	 * ��������Ϊ����Ա���͵���Ա������ͻ������ȴӺ�̨��ѯ������Ա��������Ա��
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
		String sel=req.getParameter("user").trim();//�õ�ָ�������Ա��
		long userId = 0;//��ʼ��Ա����id
		if(sel!=null&&sel.length()>0){
			userId=cus.getUsersIdByName(sel);//����Ա����������ѯԱ����id
		}
		String result="";//���ؽ��
		String[] str=req.getParameterValues("selCustomer");//�õ�ǰ��ҳ�洫�����Ŀͻ���id
		//List<Long> idlist=new ArrayList<Long>();
		long [] arr=new long[str.length];//����һ������,��ſͻ���id
		//List<CustomInfo> list=new ArrayList<CustomInfo>();//����һ�����ϴ��CustomInfo����
		long number=0,number1=0;												//�Ա��CustomInfo�������������
		int num=str.length;//�õ��������Ŀͻ�id������
		for(int i=0;i<num;i++){//ʹ��ѭ������CustomInfo���󲢷Ž�������
			arr[i]=Long.parseLong(str[i]);
			CustomInfo c=new CustomInfo();
			c.setCustomerId(arr[i]);
			c.setUserId(userId);
			number=cus.insertCustomerInfo(c);//����������ݼ��뵽CustomInfo����
			number1=cus.updateCustomerAllotById(arr[i]);
			
		}
		//���÷���������������
		if(number>0){//�ж��Ƿ����ɹ�
			result="1";//ǰ̨ͨ��ajax�ж�1��0��ʾ�Ƿ����ɹ�
		}else{
			result="0";
		}
		resp.getWriter().write(result);//�����д��ǰ̨
		return null;
	}
	
	/**
	 * �������������༭��ʱ��,�����޸ĵ�ģ̬����ʾ�û��Ļ��� ��Ϣ���˷��������޸�ǰ��һ����ѯ
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
	 * �������������޸Ŀͻ��ķ���
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
	 * ��������ĵ������ݷ���
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="intoData",method=RequestMethod.POST)
	public ModelAndView intoData(HttpServletRequest req,HttpServletResponse resp){
		ModelAndView mav=new ModelAndView();
		/**
		 * �õ��ļ���������ļ�������������ϼ��Ѿ��ϴ�֮����ļ���Ҫ������ǰ�ϴ���Tomcat���������棬�����ǲ���ֱ��
		 * �ӱ��صõ��ģ����ϴ��ļ���ʱ��ͨ��form����enctype="multipart/form-data",����������ļ��Ļ�����
		 * �͹����ļ���һ���ļ���������תΪ�������ļ��ϴ���������
		 */
		String fileName=req.getParameter("myFile");//�õ��ϴ����ļ���
		System.out.println(fileName);
		String path=req.getServletContext().getRealPath("upload");//�õ�Ҫ����ļ���·��
		File file=new File(path,fileName);//����һ��File�������ļ����й���
		String filePath=file.getPath();//�õ��ļ��Ĵ洢·��
		System.out.println(filePath);
		//String path=req.getParameter("path");
		List<Customer> list;
		long num;
		try {
			list=ExcelUtil.getExcelObject(filePath);//��Excel�ļ����н��������������Ķ����ŵ�List�����
			for(Customer cu:list){//����List���ϣ�ѭ�����뵽�����
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
			long nun=cus.insertConsultRecord(cr);//�����ݲ��뵽ConsultRecord�����
			long num2=cus.updateCustomerAllotById(cusid);
		}
		
		return null;
		
	}
	/**
	 * ���������������
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
			System.out.println("�ļ���"+file.getName());
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
	 * ���ر���
	 */
	@RequestMapping("download")
	public ResponseEntity  download(HttpServletRequest req,HttpServletResponse resp){
		String report=req.getParameter("report");
		System.out.println(report);
		HttpHeaders responseHeaders=new HttpHeaders();
		//���صĸ�������
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
			System.out.println("�ļ�������");
		}
		
		
		return null;
		
	}
}
