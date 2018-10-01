package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class AccountInfos{
  @SerializedName("accountId")
  @Expose
  private Integer accountId;
  @SerializedName("creditCard")
  @Expose
  private CreditCard creditCard;
  public void setAccountId(Integer accountId){
   this.accountId=accountId;
  }
  public Integer getAccountId(){
   return accountId;
  }
  public void setCreditCard(CreditCard creditCard){
   this.creditCard=creditCard;
  }
  public CreditCard getCreditCard(){
   return creditCard;
  }
}