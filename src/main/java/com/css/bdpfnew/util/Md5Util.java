/**
 * <b>项目名：</b>wyjg<br/>  
 * <b>包名：</b>com.css.sdc.comm.utils<br/>  
 * <b>文件名：</b>Md5Util.java<br/>  
 * <b>版本信息：</b>1.0<br/>  
 * <b>日期：</b>2014年11月2日 上午10:01:13<br/>  
 * <b>COPYRIGHT 2010-2012 ALL RIGHTS RESERVED 中国软件与技术服务股份有限公司</b>-版权所有<br/>
 */
package com.css.bdpfnew.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 项目名称：bdpfnew
 * MD5加密方法
 * @createTime 2016年12月28日 下午2:14:37
 * @author huqs@css.com.cn
 */
public class Md5Util {
	/**
	 * 字符串加密
	 * @author huqs@css.com.cn
	 * @param sourceString String
	 * @return String
	 */
    public static String MD5Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byte2hexString(md.digest(resultString.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException localException) {
        } catch (UnsupportedEncodingException e){
        	
        }
        return resultString.toUpperCase();
    }

    /**
     * 二进制数组加密
     * @author huqs@css.com.cn
     * @param bytes
     * @return
     */
    public static final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xFF) < 16) {
                buf.append("0");
            }
            buf.append(Long.toString(bytes[i] & 0xFF, 16));
        }
        return buf.toString();
    }
    
    /**
     * 文件加密
     * @author huqs@css.com.cn
     * @param file File
     * @return String
     */
    public static String getMd5ByFile(File file) {
        String value = null; 
        FileInputStream in = null; 
        try { 
            MessageDigest md5 = MessageDigest.getInstance("MD5"); 
            in = new FileInputStream(file); 
            byte[] buffer = new byte[4096]; 
            int length = -1; 
            while ((length = in.read(buffer)) != -1) { 
                md5.update(buffer, 0, length);
            }
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16); 
        } catch (IOException ex) {
            ex.printStackTrace(); 
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
            try {
            	if(in != null) {
            		in.close();
            	}
            } catch (IOException ex) {
                
            } 
        }
        return value;
    }
}
