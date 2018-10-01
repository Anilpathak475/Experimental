package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class CreditCard{
  @SerializedName("creditCardProvider")
  @Expose
  private String creditCardProvider;
  @SerializedName("cardType")
  @Expose
  private String cardType;
  @SerializedName("cardNumber")
  @Expose
  private Integer cardNumber;
  @SerializedName("expirationDate")
  @Expose
  private String expirationDate;
  public void setCreditCardProvider(String creditCardProvider){
   this.creditCardProvider=creditCardProvider;
  }
  public String getCreditCardProvider(){
   return creditCardProvider;
  }
  public void setCardType(String cardType){
   this.cardType=cardType;
  }
  public String getCardType(){
   return cardType;
  }
  public void setCardNumber(Integer cardNumber){
   this.cardNumber=cardNumber;
  }
  public Integer getCardNumber(){
   return cardNumber;
  }
  public void setExpirationDate(String expirationDate){
   this.expirationDate=expirationDate;
  }
  public String getExpirationDate(){
   return expirationDate;
  }
}