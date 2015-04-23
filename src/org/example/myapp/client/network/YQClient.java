package org.example.myapp.client.network;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.example.myapp.client.view.LoginActivity;
import org.example.myapp.client.view.MainActivity;
import org.example.myapp.common.MessageOne;
import org.example.myapp.common.MyAppConfig;
import org.example.myapp.common.ReturnObj;
import org.example.myapp.client.model.User;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;



public class YQClient {
	
	private Context context;
	private static HttpParams params = null;
	private static SchemeRegistry schemeRegistry = null;

	private HttpClient httpClient = null; 

	public static DefaultHttpClient getThreadSafeClient() {
		if (YQClient.params == null || schemeRegistry == null) {
			DefaultHttpClient client = new DefaultHttpClient();
			params = client.getParams();
			ClientConnectionManager mgr = client.getConnectionManager(); 
			YQClient.schemeRegistry = mgr.getSchemeRegistry();
		}
		
		DefaultHttpClient client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
		return client;
	}
	
	public YQClient() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().penaltyDeath().build());
		httpClient = getThreadSafeClient();		
	}
	
	public YQClient(boolean is_not_null){
		
	}
	

	/**
	 * @brief： 登陆
	 * 
	 */
	public ReturnObj sendLoginInfo(User user) {
		ReturnObj obj = new ReturnObj();
		try {
			NameValuePair pair_1 = new BasicNameValuePair("tel",
					Long.toString(user.getId()));
			NameValuePair pair_2 = new BasicNameValuePair("password",
					user.getPassword());

			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(pair_1);
			pairList.add(pair_2);
			
			Log.i("debug wangbo", "begin login");

			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList,
					HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(MyAppConfig.USER_LOGIN_URL);
			httpPost.setEntity(requestHttpEntity);

			
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			// 鍙戦�佽姹�
			
			HttpResponse response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;

			}
			// 鑾峰彇涓汉淇℃伅
			// MainActivity.myInfo=ms.getContent();
			String str_user_info = getUserInfo(user.getId());
			MainActivity.myInfo = str_user_info;
			Log.i("wangbo debug", str_user_info);
			if (0 != user.paser_str(str_user_info)) {
				obj.setMsg("鑾峰彇鐢ㄦ埛淇℃伅鍑洪敊");
				obj.setRet_code(-1);
				return obj;
			}
			
			MainActivity.myUser = user;
			
			
			obj.paser_return_code(result);
		} catch (Exception e) {
			e.printStackTrace();
			obj.setMsg(e.getMessage());
			obj.setRet_code(1);
		}
		return obj;
	}
	/**
	 * @brief: 注册用户
	 */
	public ReturnObj sendRegisterInfo(User user){

		ReturnObj obj = new ReturnObj();
		try {
			NameValuePair pair_1 = new BasicNameValuePair("name", user.getName());
			NameValuePair pair_2 = new BasicNameValuePair("sex", user.getSex());
			NameValuePair pair_3 = new BasicNameValuePair("age", Integer.toString(user.getAge()));
			NameValuePair pair_4 = new BasicNameValuePair("tel", Long.toString(user.getId()));
			NameValuePair pair_5 = new BasicNameValuePair("address", user.getAddress());
			NameValuePair pair_6 = new BasicNameValuePair("password", user.getPassword());
			NameValuePair pair_7 = new BasicNameValuePair("email", user.getMail());
			
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(pair_1);
			pairList.add(pair_2);
			pairList.add(pair_3);
			pairList.add(pair_4);
			pairList.add(pair_5);
			pairList.add(pair_6);
			pairList.add(pair_7);
			
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(MyAppConfig.USER_REGISTER_URL);
			httpPost.setEntity(requestHttpEntity);
		
			
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			// 鍙戦�佽姹�

			HttpResponse response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;
			}

			obj.paser_return_code(result);
		} catch (Exception e) {
			 e.printStackTrace();
             obj.setMsg(e.getMessage());
             obj.setRet_code(1);
          
		}
		return obj;
	}
	
	/**
	 * @brief 更新用户信息
	 */
	public ReturnObj sendUpdateInfo(User user){

		ReturnObj obj = new ReturnObj();
		try {
		
			
			NameValuePair pair_1 = new BasicNameValuePair("name", user.getName());
			NameValuePair pair_2 = new BasicNameValuePair("sex", user.getSex());
			NameValuePair pair_3 = new BasicNameValuePair("age", Integer.toString(user.getAge()));
			NameValuePair pair_4 = new BasicNameValuePair("tel", Long.toString(user.getId()));
			NameValuePair pair_5 = new BasicNameValuePair("address", user.getAddress());
			NameValuePair pair_6 = new BasicNameValuePair("password", user.getPassword());
			NameValuePair pair_7 = new BasicNameValuePair("email", user.getMail());
			
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(pair_1);
			pairList.add(pair_2);
			pairList.add(pair_3);
			pairList.add(pair_4);
			pairList.add(pair_5);
			pairList.add(pair_6);
			pairList.add(pair_7);
			
			pairList.add(new BasicNameValuePair("id", Integer.toString(user.getId_real())));
			
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(MyAppConfig.USER_UPDATE_INFO_URL);
			httpPost.setEntity(requestHttpEntity);
		
			
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			
			HttpResponse response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;
			}

			String place = "[" + Thread.currentThread().getStackTrace()[2].getFileName() + " " + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber()+"] ";
			Log.i("debug update user info  debug here", place + result);
			obj.paser_return_code(result);
			
		} catch (Exception e) {
			 e.printStackTrace();
             obj.setMsg(e.getMessage());
             Log.i("debug update user info  debug", e.getMessage());
             Log.i("debug update user info  debug", e.getStackTrace().toString());
             obj.setRet_code(1);
          
		}
		
		String place = "[" + Thread.currentThread().getStackTrace()[2].getFileName() + " " + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + Thread.currentThread().getStackTrace()[2].getLineNumber()+"] ";
		Log.i("debug update user info  debug here", place + obj.getOrg_str());
		
		return obj;
	}
	
	/**
	 * @brief: 获取用户信息
	 */
	public String getUserInfo(Long id) {
		String str = "";
		
		
		ReturnObj obj = new ReturnObj();
		try {
		
		
	
			HttpGet httpGet = new HttpGet(MyAppConfig.USER_INFO_URL + Long.toString(id));  
			
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String line = "";
			while (null != (line = reader.readLine())) {
				str += line;
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return  str;
	}
	
	/**
	 * @brief: 病人添加好友
	 */
	public ReturnObj add_user_doc(Long id, Long doc_id) {
	
		ReturnObj obj = new ReturnObj();
		try {
		
		
	
			HttpGet httpGet = new HttpGet(MyAppConfig.USER_ADD_DOC_URL + Long.toString(id) + "/" + Long.toString(doc_id));  
			
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		
		return  obj;
	}
	
	
	/**
	 * @brief: 删除好友
	 */
	public ReturnObj del_user_doc(Long id, Long doc_id) {
		
		ReturnObj obj = new ReturnObj();
		try {
		
		
			//todo 闇�瑕佷慨鏀硅繖涓猧d
			HttpGet httpGet = new HttpGet(MyAppConfig.USER_DEL_DOC_URL + Long.toString(id) + "/" + Long.toString(doc_id));  
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		
		return  obj;
	}
	
	/**
	 * @brief 获取医生信息
	 */
	public String get_doc_info(Long id) {
		
		String str_all = "";
		try {
			
			//todo 闇�瑕佷慨鏀硅繖涓猧d
			HttpGet httpGet = new HttpGet(MyAppConfig.DOC_INFO_URL + Long.toString(id));  
			
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			
		} catch (Exception e) {
			 e.printStackTrace();
			 str_all = "";
		}
		
		return  str_all;
	}
	
	
	/**
	 * @brief 获取医生列表
	 */
	public ReturnObj get_user_doc_list(Long id) {
		
		ReturnObj obj = new ReturnObj();
		try {
			HttpGet httpGet = new HttpGet(MyAppConfig.USER_DOC_LIST_URL + Long.toString(id));  
			
			HttpResponse httpResponse = getThreadSafeClient().execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
		
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		
		return  obj;
	}
	
	/**
	 * @brief 获取医生列表
	 */
	public ReturnObj get_all_doc_list(int pageindex) {
		
		ReturnObj obj = new ReturnObj();
		try {
			HttpGet httpGet = new HttpGet(MyAppConfig.DOC_ALL_URL + Integer.toString(pageindex));  
			
			HttpResponse httpResponse = getThreadSafeClient().execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
		
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		
		return  obj;
	}
	
	/**
	 * @brief 获取未读信息
	 */
	public ReturnObj get_user_doc_msg_toread_list(Long id) {
		
		ReturnObj obj = new ReturnObj();
		try {
		
			
			HttpGet httpGet = new HttpGet(MyAppConfig.USER_MSG_TO_READ_URL + Long.toString(id));  
			
			
			HttpResponse httpResponse = getThreadSafeClient().execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
			
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		
		return  obj;
	}
	
	/**
	 * @brief 发送信息
	 */
	public ReturnObj sendOneMessageToDoc(MessageOne one_mes) {
		
		ReturnObj obj = new ReturnObj();
		try {
			
			NameValuePair pair_1 = new BasicNameValuePair("sendertel", Long.toString(one_mes.getSender()));
			NameValuePair pair_2 = new BasicNameValuePair("senderrole", Integer.toString(one_mes.getSender_role()));
			NameValuePair pair_3 = new BasicNameValuePair("receivertel", Long.toString(one_mes.getReceiver()));
			NameValuePair pair_4 = new BasicNameValuePair("receiverrole", Integer.toString(one_mes.getReceiver_role()));
			NameValuePair pair_5 = new BasicNameValuePair("content", one_mes.getContent());
	
			
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(pair_1);
			pairList.add(pair_2);
			pairList.add(pair_3);
			pairList.add(pair_4);
			pairList.add(pair_5);
			
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(MyAppConfig.USER_SEND_MSG_TO_DOC_URL);
			httpPost.setEntity(requestHttpEntity);
		
			
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			// 鍙戦�佽姹�
			HttpResponse response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;
			}

			obj.paser_return_code(result);
		} catch (Exception e) {
			 e.printStackTrace();
             obj.setMsg(e.getMessage());
             obj.setRet_code(1);
          
		}
		return obj;
	}
	
	
	/**
	 * @brief 获取未读信息
	 */
	public ReturnObj get_user_doc_msg_toread_detail_list(Long id,  Long doc_id) {
		
		ReturnObj obj = new ReturnObj();
		try {
			
			
			HttpGet httpGet = new HttpGet(MyAppConfig.USER_DOC_CHAT_MSG_URL + Long.toString(doc_id) + "/" + Long.toString(id));  
	
			HttpResponse httpResponse = YQClient.getThreadSafeClient().execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
			
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		
		return  obj;
	}
	
	/**
	 * @brief 新增预约
	 */
	public ReturnObj add_user_oder(Long patient_tel,  Long 	doctor_tel, String appdatetime) {
		
		ReturnObj obj = new ReturnObj();
		try {
			
			NameValuePair pair_1 = new BasicNameValuePair("patient_tel", Long.toString(patient_tel));
			NameValuePair pair_2 = new BasicNameValuePair("doctor_tel", Long.toString(doctor_tel));
			NameValuePair pair_3 = new BasicNameValuePair("appdatetime", appdatetime);
	
			
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(pair_1);
			pairList.add(pair_2);
			pairList.add(pair_3);
			
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(MyAppConfig.PATIENT_ADD_ORDER);
			httpPost.setEntity(requestHttpEntity);
		
			
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			// 鍙戦�佽姹�
			HttpResponse response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;
			}

			obj.paser_return_code(result);
		} catch (Exception e) {
			 e.printStackTrace();
             obj.setMsg(e.getMessage());
             obj.setRet_code(1);
		}
		return  obj;
	}

	/**
	 * @brief: 查询预约
	 * @param id
	 * @return
	 */
	public ReturnObj get_user_order_list(long id) {
		ReturnObj obj = new ReturnObj();
		try {
			//todo 闇�瑕佷慨鏀硅繖涓猧d
			HttpGet httpGet = new HttpGet(MyAppConfig.PATIENT_GET_ORDER_LIST + Long.toString(id));  
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		return obj;
	}
	
	/**
	 * @brief: 删除预约
	 * 
	 */
	public ReturnObj del_user_order(int id) {
		ReturnObj obj = new ReturnObj();
		try {
			//todo 闇�瑕佷慨鏀硅繖涓猧d
			HttpGet httpGet = new HttpGet(MyAppConfig.PATIENT_DEL_ORDER + Integer.toString(id));  
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		return obj;
	}
	
	
	public ReturnObj get_post_list(long id) {
		ReturnObj obj = new ReturnObj();
		try {
			HttpGet httpGet = new HttpGet(MyAppConfig.PATIENT_GET_POST_LIST + Long.toString(id));  
			HttpResponse httpResponse = httpClient.execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}

			obj.paser_return_code(str_all);
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		return obj;
	}
	
	
	/**
	 * @brief 发帖
	 */
	public ReturnObj add_user_post(Long patient_tel,  String title, String content, int node_id) {
		
		ReturnObj obj = new ReturnObj();
		try {
			
			NameValuePair pair_1 = new BasicNameValuePair("patient_tel", Long.toString(patient_tel));
			NameValuePair pair_2 = new BasicNameValuePair("title", title);
			NameValuePair pair_3 = new BasicNameValuePair("content", content);
			NameValuePair pair_4 = new BasicNameValuePair("node_id", Integer.toString(node_id));
			
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(pair_1);
			pairList.add(pair_2);
			pairList.add(pair_3);
			pairList.add(pair_4);
			
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(MyAppConfig.PATIENT_ADD_POST);
			httpPost.setEntity(requestHttpEntity);
		
			
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			// 鍙戦�佽姹�
			HttpResponse response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;
			}

			obj.paser_return_code(result);
		} catch (Exception e) {
			 e.printStackTrace();
             obj.setMsg(e.getMessage());
             obj.setRet_code(1);
		}
		return  obj;
	}
	/** 
	 * 获取帖子内容
	 * @param id
	 * @return
	 */
	public ReturnObj get_post_detail_by_id(int id) {
		ReturnObj obj = new ReturnObj();
		try {
			
			HttpGet httpGet = new HttpGet(MyAppConfig.PATIENT_GET_POST_DETAIL + Integer.toString(id));  
			
			HttpResponse httpResponse = getThreadSafeClient().execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}
			obj.paser_return_code(str_all);
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		return obj;
	}


	/** 
	 * 获取帖子内容
	 * @param id 帖子ID
	 * @return
	 */
	public ReturnObj get_post_comment_list(int id) {
		ReturnObj obj = new ReturnObj();
		try {
			
			HttpGet httpGet = new HttpGet(MyAppConfig.PATIENT_GET_POST_COMMENT_LIST + Integer.toString(id));  
			
			HttpResponse httpResponse = getThreadSafeClient().execute(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String str_all = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				str_all += line;
			}
			obj.paser_return_code(str_all);
		} catch (Exception e) {
			 e.printStackTrace();
			 obj.setMsg(e.getMessage());
			 obj.setRet_code(-1);
		}
		return obj;
	}
	
	/**
	 * @brief 发帖
	 * 
	 */
	public ReturnObj add_post_comment(Long patient_tel,  String comment, int topic_id) {
		
		ReturnObj obj = new ReturnObj();
		try {
			
			NameValuePair pair_1 = new BasicNameValuePair("patient_tel", Long.toString(patient_tel));
			NameValuePair pair_2 = new BasicNameValuePair("comment", comment);
			NameValuePair pair_3 = new BasicNameValuePair("topic_id", Integer.toString(topic_id));
			
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			pairList.add(pair_1);
			pairList.add(pair_2);
			pairList.add(pair_3);
			
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(MyAppConfig.PATIENT_ADD_POST_COMMENT);
			httpPost.setEntity(requestHttpEntity);
		
			
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			
			HttpResponse response = httpClient.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;
			}

			obj.paser_return_code(result);
		} catch (Exception e) {
			 e.printStackTrace();
             obj.setMsg(e.getMessage());
             obj.setRet_code(1);
		}
		return  obj;
	}
}
