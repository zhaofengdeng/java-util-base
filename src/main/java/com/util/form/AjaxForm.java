package com.util.form;


public class AjaxForm {
	public Integer code;
	public Boolean success = false;
	public Object data;

	public AjaxForm setSuccess(Object data) {
		this.success = true;
		this.data = data;
		return this;
	}

	public AjaxForm setError(Integer errorCode, String errorMsg) {
		this.success = false;
		this.code=errorCode;
		this.data = errorMsg;
		return this;
	}
	public AjaxForm setError(String errorMsg) {
		this.success = false;
		this.data = errorMsg;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public Boolean getSuccess() {
		return success;
	}

	public Object getData() {
		return data;
	}
}
