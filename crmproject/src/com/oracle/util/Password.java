package com.oracle.util;

import java.security.MessageDigest;

public class Password {

	public static String MD5EncodePass(String sourceString)
    {
        String resultString = null;
        try
        {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex)
        {
        }
        return resultString;
    }

	/*
     * 
     * 去掉原MD5加密验证后增加的逗号
     * 原句:buf.append(Long.toHexString((int) bytes[i] & 0xff)+",");
     */
    public static String byte2hexString(byte[] bytes)
    {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++)
        {
            if (((int) bytes[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toHexString((int) bytes[i] & 0xff));
        }
        return buf.toString();
    }
    public static void main(String[] asgs){
    	Password pa=new Password();
    	System.out.println(pa.MD5EncodePass("123456"));
    }
}
    