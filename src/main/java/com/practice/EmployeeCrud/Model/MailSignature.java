package com.practice.EmployeeCrud.Model;


public class MailSignature {
	
	private String fromMail;
	
	private String toMail;
	
	private String message;
	
	private String senderpass;

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSenderpass() {
		return senderpass;
	}

	public void setSenderpass(String senderpass) {
		this.senderpass = senderpass;
	}

	@Override
	public String toString() {
		return "MailSignature [fromMail=" + fromMail + ", toMail=" + toMail + ", message=" + message + ", senderpass="
				+ senderpass + "]";
	}
	
	

}
