package com.aadp.vend.ws.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PaymentInfo  implements Serializable {

	private static final long serialVersionUID = -1707741609522546161L;

	@Id
	@Column(nullable = false, length = 120)
	private String userId;
	
	@Column(length=45, nullable=false)
	private String receiverUpiAddress;
	
	@Column(length=45, nullable=false)
	private String receiverName;
	
	@Column(length=11, nullable=false)
	private String merchantCode;
	
	@Column(length=45, nullable=false)
	private String transactionNote;
	
	@Column(length = 120, nullable = false )
	private String resultCallBackurl;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
	
}
