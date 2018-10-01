package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Transaction{
  @SerializedName("sendCurrency")
  @Expose
  private String sendCurrency;
  @SerializedName("onlineTransaction")
  @Expose
  private Boolean onlineTransaction;
  @SerializedName("receiveOption")
  @Expose
  private String receiveOption;
  @SerializedName("receiver")
  @Expose
  private Receiver receiver;
  @SerializedName("authorizationId")
  @Expose
  private Integer authorizationId;
  @SerializedName("senderFirstName")
  @Expose
  private String senderFirstName;
  @SerializedName("sendAmount")
  @Expose
  private Integer sendAmount;
  @SerializedName("dateInitiated")
  @Expose
  private String dateInitiated;
  @SerializedName("transactionType")
  @Expose
  private String transactionType;
  @SerializedName("referenceNumber")
  @Expose
  private Integer referenceNumber;
  @SerializedName("senderEmail")
  @Expose
  private String senderEmail;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("senderLastName")
  @Expose
  private String senderLastName;
  @SerializedName("status")
  @Expose
  private String status;
  public void setSendCurrency(String sendCurrency){
   this.sendCurrency=sendCurrency;
  }
  public String getSendCurrency(){
   return sendCurrency;
  }
  public void setOnlineTransaction(Boolean onlineTransaction){
   this.onlineTransaction=onlineTransaction;
  }
  public Boolean getOnlineTransaction(){
   return onlineTransaction;
  }
  public void setReceiveOption(String receiveOption){
   this.receiveOption=receiveOption;
  }
  public String getReceiveOption(){
   return receiveOption;
  }
  public void setReceiver(Receiver receiver){
   this.receiver=receiver;
  }
  public Receiver getReceiver(){
   return receiver;
  }
  public void setAuthorizationId(Integer authorizationId){
   this.authorizationId=authorizationId;
  }
  public Integer getAuthorizationId(){
   return authorizationId;
  }
  public void setSenderFirstName(String senderFirstName){
   this.senderFirstName=senderFirstName;
  }
  public String getSenderFirstName(){
   return senderFirstName;
  }
  public void setSendAmount(Integer sendAmount){
   this.sendAmount=sendAmount;
  }
  public Integer getSendAmount(){
   return sendAmount;
  }
  public void setDateInitiated(String dateInitiated){
   this.dateInitiated=dateInitiated;
  }
  public String getDateInitiated(){
   return dateInitiated;
  }
  public void setTransactionType(String transactionType){
   this.transactionType=transactionType;
  }
  public String getTransactionType(){
   return transactionType;
  }
  public void setReferenceNumber(Integer referenceNumber){
   this.referenceNumber=referenceNumber;
  }
  public Integer getReferenceNumber(){
   return referenceNumber;
  }
  public void setSenderEmail(String senderEmail){
   this.senderEmail=senderEmail;
  }
  public String getSenderEmail(){
   return senderEmail;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setSenderLastName(String senderLastName){
   this.senderLastName=senderLastName;
  }
  public String getSenderLastName(){
   return senderLastName;
  }
  public void setStatus(String status){
   this.status=status;
  }
  public String getStatus(){
   return status;
  }
}