package com.oracle.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.oracle.entity.Customer;

/**
 * Excel�ļ��ĵ���͵���
 * @author oracleOAEC
 *
 */
public class ExcelUtil {
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
//	public static String NO_DEFINE = "no_define";// δ������ֶ�
//	public static String DEFAULT_DATE_PATTERN = "yyyy��MM��dd��";// Ĭ�����ڸ�ʽ
//	public static int DEFAULT_COLOUMN_WIDTH = 17;
	public static Logger log = Logger.getLogger(ExcelUtil.class);
	
	public static Workbook getWorkbook(String fileName) throws IOException{
		Workbook wb=null;
		InputStream input=new FileInputStream(fileName);
		//��ȡ�ļ��ĺ�׺��
		String filePostFixName=getPostfix(fileName);
		if (filePostFixName.equals(OFFICE_EXCEL_2010_POSTFIX))
		{
			wb = new XSSFWorkbook(input);
		}
		else if (filePostFixName.equals(OFFICE_EXCEL_2003_POSTFIX))
		{
			wb = new HSSFWorkbook(input);
		}
		return wb;
	}
	/**
	 * ��ȡ�ļ���׺��
	 * @param path �ļ�·��
	 * @return �ļ���׺��
	 */
	public static String getPostfix(String path)
	{
		if (path == null)
		{
			return "";
		}
		if (path.contains("."))
		{
			return path.substring(path.lastIndexOf(".") + 1, path.length());
		}
		return "";
	}
	/**
	 * ��ȡ��Ԫ�����������
	 * @param cell
	 * @return
	 */
	private static String getCellValue(Cell cell)
	{
		String cellValue = "";
		// ���ݸ�ʽ
		DataFormatter formatter = new DataFormatter();
		if (cell != null)
		{
			// ��Ԫ������
			switch (cell.getCellType())
			{
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell))
				{
					// ȡֵ
					cellValue = formatter.formatCellValue(cell);
				}
				else
				{
					// ����
					double value = cell.getNumericCellValue();
					int intValue = (int) value;
					cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
				}
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = cell.toString().trim();
				break;
			}
		}
		return cellValue.trim();
	}
	/**
	 * ����Excel 2007 OOXML (.xlsx)��ʽ
	 * @param title ������
	 * @param headMap ����-��ͷ
	 * @param jsonArray ���ݼ�
	 * @param datePattern ���ڸ�ʽ����nullֵ��Ĭ�� ������
	 * @param colWidth �п� Ĭ�� ����17���ֽ�
	 * @param out �����
	 */
	
	public static void exportExcelX(OutputStream out, List<Customer> list)
	{
	
		// ����һ��������
		XSSFWorkbook workbook = new XSSFWorkbook();
		// ��ͷ��ʽ
		CellStyle titleStyle = workbook.createCellStyle();//���ñ�ͷ��ʽ
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//����������ж���
		Font titleFont = workbook.createFont();//����һ������Ķ���
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBoldweight((short) 700);
		titleStyle.setFont(titleFont);//��������Excel������
		// ��ͷ��ʽ
		CellStyle headerStyle = workbook.createCellStyle();
		// headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setFont(headerFont);
		// ��Ԫ����ʽ
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font cellFont = workbook.createFont();
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellStyle.setFont(cellFont);
		// ����һ��(������)���
		XSSFSheet sheet = workbook.createSheet("�ͻ�����");
		// ��
		XSSFRow titleRow = sheet.createRow(0);
		String[] str={"CCID","C_NAME","C_EMAIL","C_MOBILE","C_DATE","C_STATE","C_USERNAME","C_SEX","C_AGE","C_BIRTHDAY","ISALLOT"};
		int i=str.length;
		for(int j=0;j<i;j++){//����ͷ����
			XSSFCell titleCell = titleRow.createCell(j);
			titleCell.setCellValue(str[j]);
			titleCell.setCellStyle(headerStyle);
			System.out.println(str[j]);
		}
		// �����������ݣ�����������
		int k=list.size();//k�������д�Ŷ��������
		System.out.println("k:"+k);
		System.out.println("i:"+i);
		for(int n=1;n<k+1;n++){
			Customer cus=list.get(n-1);
			XSSFRow dataRow = sheet.createRow(n);
			for(int m=0;m<i;m++){
				XSSFCell newCell = dataRow.createCell(m);
				switch(m){
					case 0:
						newCell.setCellValue(cus.getCcid());
						newCell.setCellStyle(cellStyle);break;
					case 1:
						newCell.setCellValue(cus.getC_name());
						newCell.setCellStyle(cellStyle);break;
					case 2:
						newCell.setCellValue(cus.getC_email());
						newCell.setCellStyle(cellStyle);break;
					case 3:
						newCell.setCellValue(cus.getC_mobile());
						newCell.setCellStyle(cellStyle);break;
					case 4:
						newCell.setCellValue(cus.getC_date());
						newCell.setCellStyle(cellStyle);break;
					case 5:
						newCell.setCellValue(cus.getC_state());
						newCell.setCellStyle(cellStyle);break;
					case 6:
						newCell.setCellValue(cus.getC_username());
						newCell.setCellStyle(cellStyle);break;
					case 7:
						newCell.setCellValue(cus.getC_sex());
						newCell.setCellStyle(cellStyle);break;
					case 8:
						newCell.setCellValue(cus.getC_age());
						newCell.setCellStyle(cellStyle);break;
					case 9:
						newCell.setCellValue(cus.getC_birthday());
						newCell.setCellStyle(cellStyle);break;
					case 10:
						newCell.setCellValue(cus.getIsAllot());
						newCell.setCellStyle(cellStyle);break;
				}
			}
		}
		try
		{
			workbook.write(out);
			workbook.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * �ļ�����
	 * @param list
	 * @param response
	 */
	
	/*public static void downloadExcelFile(List<Customer> list, HttpServletResponse response)
	{
		try
		{
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			exportExcelX(os, list);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// ����response���������Դ�����ҳ��
			response.reset();

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((new Date().getTime() + ".xlsx").getBytes(), "iso-8859-1"));
			response.setContentLength(content.length);
			ServletOutputStream outputStream = response.getOutputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			BufferedOutputStream bos = new BufferedOutputStream(outputStream);
			byte[] buff = new byte[8192];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
			{
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();
			outputStream.flush();
			outputStream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	*/
	public static List<Customer> getExcelObject(String fileName) throws IOException{
		Workbook book=getWorkbook(fileName);//ͨ���ļ�������һ������������
		Sheet sheet = book.getSheetAt(0);//�õ�Excel�� ��һ��sheet�ļ�
		List<Customer> list=new ArrayList<Customer>();
		int num=sheet.getLastRowNum();
		for(int i=3;i<=num-3;i++){
			Customer cus=new Customer();
			Row row=sheet.getRow(i);
			for(int j=1;j<=10;j++){
				if(row!=null){
					switch(j){
						case 1:
							cus.setC_name(getCellValue(row.getCell(j)));break;
						case 2:
							cus.setC_email(getCellValue(row.getCell(j)));break;
						case 3:
							cus.setC_mobile(getCellValue(row.getCell(j)));break;
						case 5:
							cus.setC_state(getCellValue(row.getCell(j)));break;
						case 6:
							cus.setC_username(getCellValue(row.getCell(j)));break;
						case 7:
							cus.setC_sex(getCellValue(row.getCell(j)));break;
						case 8:
							cus.setC_age(Long.parseLong(getCellValue(row.getCell(j))));break;
						case 9:
							cus.setC_birthday(getCellValue(row.getCell(j)));break;
						case 10:
							cus.setIsAllot(getCellValue(row.getCell(j)));break;
						default:break;
					}
					
					
				}
				
			}
			list.add(cus);
		}
		return list;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file=new File("d://report","report.xlsx");
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		OutputStream out=new FileOutputStream(file);
		List<Customer> list=new ArrayList<Customer>();
		Customer cus=null;
		cus=new Customer();
		cus.setCcid((long)1);
		cus.setC_name("���Ĳ�");
		cus.setC_email("2696578102@qq.com");
		cus.setC_mobile("15797655641");
		cus.setC_state("����");
		cus.setC_username("xxx");
		cus.setC_sex("��");
		cus.setC_age(18);
		cus.setC_birthday("1995-10-19");
		cus.setIsAllot("1");
		list.add(cus);
		cus=new Customer();
		cus.setCcid((long)2);
		cus.setC_name("���Ĳ�2");
		cus.setC_email("26965782102@qq.com");
		cus.setC_mobile("157976255641");
		cus.setC_state("����2");
		cus.setC_username("x2xx");
		cus.setC_sex("��2");
		cus.setC_age(18);
		cus.setC_birthday("19925-10-19");
		cus.setIsAllot("12");
		list.add(cus);
		ExcelUtil.exportExcelX(out, list);
	
	
	}
	



}
