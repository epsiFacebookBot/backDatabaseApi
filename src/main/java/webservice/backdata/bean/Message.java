package webservice.backdata.bean;

public class Message {
	String mid;
	String text;
	String idFrom;
	String idTo;
	String attachmentType;
	String payload;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIdFrom() {
		return idFrom;
	}
	public void setIdFrom(String idFrom) {
		this.idFrom = idFrom;
	}
	public String getIdTo() {
		return idTo;
	}
	public void setIdTo(String idTo) {
		this.idTo = idTo;
	}
	
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	

}
