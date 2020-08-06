class UpiTransactionResponse {
  String txnId;
  String responseCode;
  String approvalRefNo;
  String status;
  String txnRef;
  String rawResponse;

  UpiTransactionResponse(this.txnId, this.responseCode, this.approvalRefNo,
      this.status, this.txnRef, this.rawResponse);

  factory UpiTransactionResponse.fromJson(dynamic json) {
    return UpiTransactionResponse(
        json['txnId'] as String,
        json['responseCode'] as String,
        json['approvalRefNo'] as String,
        json['status'] as String,
        json['txnRef'] as String,
        json['rawResponse'] as String);
  }

  @override
  String toString() {
    return '{ ${this.txnId}, ${this.responseCode} }';
  }
}
