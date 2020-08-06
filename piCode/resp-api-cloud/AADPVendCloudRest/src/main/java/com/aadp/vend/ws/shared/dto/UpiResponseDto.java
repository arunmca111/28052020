package com.aadp.vend.ws.shared.dto;

import java.io.Serializable;

public class UpiResponseDto implements Serializable {
	
	private static final long serialVersionUID = -3943904392369457734L;
	private String txnId;
	private String responseCode;
	private String approvalRefNo;
	private String status;
	private String txnRef;
	private String rawResponse;
	
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getApprovalRefNo() {
		return approvalRefNo;
	}
	public void setApprovalRefNo(String approvalRefNo) {
		this.approvalRefNo = approvalRefNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public String getRawResponse() {
		return rawResponse;
	}
	public void setRawResponse(String rawResponse) {
		this.rawResponse = rawResponse;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

}
