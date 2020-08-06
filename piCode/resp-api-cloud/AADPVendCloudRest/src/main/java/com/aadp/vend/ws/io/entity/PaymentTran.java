package com.aadp.vend.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PaymentTran implements Serializable {

	private static final long serialVersionUID = -542011128162206084L;

	@Id
	@Column(nullable = false, length = 30)
	private String txnId;
	
	@Column(nullable = false, length = 30)
	private String responseCode;
	
	@Column(nullable = false, length = 60)
	private String approvalRefNo;
	
	@Column(length=11, nullable=false)
	private String status;
	
	@Column(length=30, nullable=false)
	private int txnRef;
	
	@Column(nullable=false)
	private double rawResponse;
	
	@OneToOne(mappedBy = "payment")
	private Orders order;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
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

	public int getTxnRef() {
		return txnRef;
	}

	public void setTxnRef(int txnRef) {
		this.txnRef = txnRef;
	}

	public double getRawResponse() {
		return rawResponse;
	}

	public void setRawResponse(double rawResponse) {
		this.rawResponse = rawResponse;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}
	

}
