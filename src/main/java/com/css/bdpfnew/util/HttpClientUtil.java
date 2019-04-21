package com.css.bdpfnew.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpClientUtil {
	
	/*
	 * 通过URL调取REST接口数据
	 */
	public static String sendPostMessage(String urlStr, Map<String, String> params, String encode) {

		StringBuffer stringBuffer = new StringBuffer();

		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				try {
					stringBuffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), encode)).append("&");

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			// 删掉最后一个 & 字符
			stringBuffer.deleteCharAt(stringBuffer.length() - 1);
			System.out.println("-->>" + stringBuffer.toString());

			try {
				URL url = new URL(urlStr);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setConnectTimeout(3000);
				httpURLConnection.setDoInput(true);// 从服务器获取数据
				httpURLConnection.setDoOutput(true);// 向服务器写入数据

				// 获得上传信息的字节大小及长度
				byte[] mydata = stringBuffer.toString().getBytes();
				// 设置请求体的类型
				httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				httpURLConnection.setRequestProperty("Content-Lenth", String.valueOf(mydata.length));

				// 获得输出流，向服务器输出数据
				OutputStream outputStream = (OutputStream) httpURLConnection.getOutputStream();
				outputStream.write(mydata);

				// 获得服务器响应的结果和状态码
				int responseCode = httpURLConnection.getResponseCode();
				if (responseCode == 200) {

					// 获得输入流，从服务器端获得数据
					InputStream inputStream = (InputStream) httpURLConnection.getInputStream();
					return (changeInputStream(inputStream, encode));

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "";
	}
	
	/*
	 * 把从输入流InputStream按指定编码格式encode变成字符串String
	 */
	public static String changeInputStream(InputStream inputStream, String encode) {

		// ByteArrayOutputStream 一般叫做内存流
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {

			try {
				while ((len = inputStream.read(data)) != -1) {
					byteArrayOutputStream.write(data, 0, len);

				}
				result = new String(byteArrayOutputStream.toByteArray(), encode);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return result;
	}
	
//	public static void main(String[] args) throws Exception {
//			String urlStr = "http://59.255.6.134:8001/tj/api";
//		 	Map<String, String> params = new HashMap<String, String>();
//		 	params.put("method","IZcl.cjr.getQCCjrInfo");
//		 	params.put("idCard","110101198608263516");
//		 	params.put("name","刘某");
//		 	params.put("syscode","gajk_bj"); //请求接口系统代码(测试)
//		 	params.put("sessionId","DCC2C9B40DA6FE06CD9D13B7F11F78B4");//系统授权码
//		 	params.put("timestamp","20181122111154");//时间戳
//		 	String jsonStr = HttpClientUtil.sendPostMessage(urlStr,params, "utf-8");
//		 	JSONObject oo = new JSONObject(jsonStr);
//		 	String res_state = oo.getString("res_state");
//		 	if (!res_state.equals("1")) {
//			}
//		 	//String res_data = oo.getJSONObject("res_data").toString();
//		    String cjr = oo.getJSONObject("res_data").getJSONObject("cjr").toString();
//		    CdpfCitizenKsqr info = (CdpfCitizenKsqr) JsonUtil.deserializeToBean(cjr, CdpfCitizenKsqr.class);
//		    String area_code = info.getArea_code();
//		    String id = info.getId();
//		    String name = info.getName();
//		    String kindstr = info.getKindstr();
//		    System.out.println("=======>>>" + "area_code:" + area_code + ";id:" 
//		    + id + ";name:" + name + ";kindstr:" + kindstr);
//	}
		
}