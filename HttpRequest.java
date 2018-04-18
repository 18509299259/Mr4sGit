package com.gm.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequest {
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 * @throws IOException
	 */
	public static String sendGet(String url, String param) throws IOException {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = param!=null?url + "?" + param:url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} // 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送post请求
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String sendPost(String url, String param) throws IOException {
		StringBuffer data = new StringBuffer();
		URL connect = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
//		connection.setRequestProperty("Content-Type", "application/json");

		OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		paramout.write(param);
		paramout.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		paramout.close();
		reader.close();
		return data.toString();
	}
	
	public static String sendPut(String url, String param) throws IOException {
		StringBuffer data = new StringBuffer();
		URL connect = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");

		OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		paramout.write(param);
		paramout.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		paramout.close();
		reader.close();
		return data.toString();
	}
	
	public static String sendDelete(String url, String param) throws IOException {
		StringBuffer data = new StringBuffer();
		URL connect = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
		connection.setRequestMethod("DELETE");
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		reader.close();
		return data.toString();
	}
}