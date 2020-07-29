package com.aadp.vend.ws.ui.model.response;

public class PaymentsInfoResponse {

	private String receiverUpiAddress;
	private String receiverName;
	private String merchantCode;
	private String transactionNote;
	private String resultCallBackurl;
	private String transactionRef;
	
	public String getReceiverUpiAddress() {
		return receiverUpiAddress;
	}
	public void setReceiverUpiAddress(String receiverUpiAddress) {
		this.receiverUpiAddress = receiverUpiAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getTransactionNote() {
		return transactionNote;
	}
	public void setTransactionNote(String transactionNote) {
		this.transactionNote = transactionNote;
	}
	public String getResultCallBackurl() {
		return resultCallBackurl;
	}
	public void setResultCallBackurl(String resultCallBackurl) {
		this.resultCallBackurl = resultCallBackurl;
	}
	public String getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}

}
