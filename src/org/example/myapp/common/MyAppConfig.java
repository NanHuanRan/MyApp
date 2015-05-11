package org.example.myapp.common;

public class MyAppConfig {
	public static final int MOBILE_LEN = 11; //手机号长度，也就是ID的长度
	
	public static final long SUPER_ZIXUN_ACCOUNT = 11111111111L; //super 医生账户

	public static final String SUPER_ZIXUN_NICK = "我要咨询";
	
	public static final String APP_VERSION = "1.0.0";
	
	
	//注册用户
	public static final String USER_REGISTER_URL = "http://rj17701.sinaapp.com/index.php/patient/create";
	
	//用户登录
	public static final String USER_LOGIN_URL = "http://rj17701.sinaapp.com/index.php/patient/login"; 
	                                            
	//获取病人信息信息
	public static final String USER_INFO_URL = "http://rj17701.sinaapp.com/index.php/patient/one/"; 
	
	//用户添加医生的关系
	public static final String USER_ADD_DOC_URL = "http://rj17701.sinaapp.com/index.php/doctorpatientrelationship/add/";
	
	
	//获取医生的信息
	public static final String DOC_INFO_URL = "http://rj17701.sinaapp.com/index.php/doctor/one/";
	
	//删除医生的关联
	public static final String USER_DEL_DOC_URL = "http://rj17701.sinaapp.com/index.php/doctorpatientrelationship/delete/";
	
	
	//获取当前用户医生的列表
	public static final String USER_DOC_LIST_URL = "http://rj17701.sinaapp.com/index.php/doctorpatientrelationship/listing/";
		
	//获取所有医生的列表
	public static final String DOC_ALL_URL = "http://rj17701.sinaapp.com/index.php/doctor/all/";
	
	//更新用户的信息
	public static final String USER_UPDATE_INFO_URL = "http://rj17701.sinaapp.com/index.php/patient/update";
			
	
	//给某一个医生发送信息，先部分在线还是离线
	public static final String USER_SEND_MSG_TO_DOC_URL = "http://rj17701.sinaapp.com/index.php/chatmessage/send";
	
	//获取当前用户的所有信息，主要是未读的；已经获取过一次的所有消息都标记为已读，新存入数据库还未获取的消息为未读
	public static final String USER_MSG_TO_READ_URL = "http://rj17701.sinaapp.com/index.php/chatmessage/getunreadsummary/";
	
	
	//
	public static final String USER_DOC_CHAT_MSG_URL = "http://rj17701.sinaapp.com/index.php/chatmessage/getunreadsdetail/";

	public static final int UPDATE_MESSAGE_COUNT = 120000;
	
	
	public static final int GET_MESSAGE_COUNT = 15000;
	
	//持续存储
	public static final String SHARE_PREFERENCE_FILE = "three_stone_cradle";
	
	
	//预约管理 post
	public static final String PATIENT_ADD_ORDER = "http://rj17701.sinaapp.com/index.php/appointmentrecord/addAppointment";
	
	//get
	public static final String PATIENT_DEL_ORDER = "http://rj17701.sinaapp.com/index.php/appointmentrecord/deleteAppointment/";
	
	//获取订单列表
	public static final String PATIENT_GET_ORDER_LIST = "http://rj17701.sinaapp.com/index.php/appointmentrecord/GetAppointmentsByPat/";

	
	//获取帖子列表
	public static final String PATIENT_GET_POST_LIST = "http://rj17701.sinaapp.com/index.php/node/showInMobile/";

	//发表帖子
	public static final String PATIENT_ADD_POST = "http://rj17701.sinaapp.com/index.php/topic/addFromMobile";

	//获取帖子详细内容
	public static final String PATIENT_GET_POST_DETAIL = "http://rj17701.sinaapp.com/index.php/topic/showTopicInMobile/";

	
	//获取帖子评论
	public static final String PATIENT_GET_POST_COMMENT_LIST = "http://rj17701.sinaapp.com/index.php/topic/showCommentsInMobile/";
	
	//发表帖子评论
	public static final String PATIENT_ADD_POST_COMMENT = "http://rj17701.sinaapp.com/index.php/comment/addFromMobile";

}
