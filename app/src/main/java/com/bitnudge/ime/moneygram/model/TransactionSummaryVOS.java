package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class TransactionSummaryVOS{
  @SerializedName("transactionType")
  @Expose
  private String transactionType;
  @SerializedName("sendCurrency")
  @Expose
  private String sendCurrency;
  @SerializedName("onlineTransaction")
  @Expose
  private Boolean onlineTransaction;
  @SerializedName("receiveOption")
  @Expose
  private String receiveOption;
  @SerializedName("referenceNumber")
  @Expose
  private Integer referenceNumber;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("dateInitiated")
  @Expose
  private String dateInitiated;
  @SerializedName("status")
  @Expose
  private String status;
  public void setTransactionType(String transactionType){
   this.transactionType=transactionType;
  }
  public String getTransactionType(){
   return transactionType;
  }
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
  public void setReferenceNumber(Integer referenceNumber){
   this.referenceNumber=referenceNumber;
  }
  public Integer getReferenceNumber(){
   return referenceNumber;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setDateInitiated(String dateInitiated){
   this.dateInitiated=dateInitiated;
  }
  public String getDateInitiated(){
   return dateInitiated;
  }
  public void setStatus(String status){
   this.status=status;
  }
  public String getStatus(){
   return status;
  }
}