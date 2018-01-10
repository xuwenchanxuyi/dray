package com.oracle.util;

public class ToolUtil {

	public static boolean isEmpty(String str){
		
		boolean isEmpty=true;
		if(str!=null && str.trim().length()>0){
			isEmpty=false;
		}
		return isEmpty;
	}
}
