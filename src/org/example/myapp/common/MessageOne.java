package org.example.myapp.common;

public class MessageOne {
	
	private Long sender;
	private Long receiver;
	
	private int sender_role;
	private int receiver_role;
	
	private String content;
	
	private String sender_name;
	private String receiver_name;
	
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	private String time;
	private boolean isLeft;//�Ƿ�Ϊ�յ�����Ϣ�������
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}
	public Long getReceiver() {
		return receiver;
	}
	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}
	public int getSender_role() {
		return sender_role;
	}
	public void setSender_role(int sender_role) {
		this.sender_role = sender_role;
	}
	public int getReceiver_role() {
		return receiver_role;
	}
	public void setReceiver_role(int receiver_role) {
		this.receiver_role = receiver_role;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public boolean isLeft() {
		return isLeft;
	}
	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}
	

	
	public void paser_str() {
		
		
	}
	
}
