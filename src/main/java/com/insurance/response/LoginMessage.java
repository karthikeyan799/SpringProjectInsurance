package com.insurance.response;

public class LoginMessage {
	 String message;
	    Boolean status;
	    public String getMessage() {
	        return message;
	    }
	    public void setMessage(String message) {
	        this.message = message;
	    }
	    public Boolean getStatus() {
	        return status;
	    }
	    public void setStatus(Boolean status) {
	        this.status = status;
	    }
	    public void LoginMesage(String message, Boolean status) {
	        this.message = message;
	        this.status = status;
	    }
}
