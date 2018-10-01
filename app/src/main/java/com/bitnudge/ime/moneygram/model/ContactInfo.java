package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class ContactInfo{
  @SerializedName("promoEmail")
  @Expose
  private Boolean promoEmail;
  @SerializedName("primaryPhone")
  @Expose
  private Long primaryPhone;
  @SerializedName("communicationLanguage")
  @Expose
  private String communicationLanguage;
  @SerializedName("promoSmsOptIn")
  @Expose
  private Boolean promoSmsOptIn;
  @SerializedName("primaryPhoneType")
  @Expose
  private String primaryPhoneType;
  @SerializedName("phoneCountryCode")
  @Expose
  private String phoneCountryCode;
  @SerializedName("phoneCountryDialingCode")
  @Expose
  private Integer phoneCountryDialingCode;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("operationalSmsOptIn")
  @Expose
  private Boolean operationalSmsOptIn;
  public void setPromoEmail(Boolean promoEmail){
   this.promoEmail=promoEmail;
  }
  public Boolean getPromoEmail(){
   return promoEmail;
  }
  public void setPrimaryPhone(Long primaryPhone){
   this.primaryPhone=primaryPhone;
  }
  public Long getPrimaryPhone(){
   return primaryPhone;
  }
  public void setCommunicationLanguage(String communicationLanguage){
   this.communicationLanguage=communicationLanguage;
  }
  public String getCommunicationLanguage(){
   return communicationLanguage;
  }
  public void setPromoSmsOptIn(Boolean promoSmsOptIn){
   this.promoSmsOptIn=promoSmsOptIn;
  }
  public Boolean getPromoSmsOptIn(){
   return promoSmsOptIn;
  }
  public void setPrimaryPhoneType(String primaryPhoneType){
   this.primaryPhoneType=primaryPhoneType;
  }
  public String getPrimaryPhoneType(){
   return primaryPhoneType;
  }
  public void setPhoneCountryCode(String phoneCountryCode){
   this.phoneCountryCode=phoneCountryCode;
  }
  public String getPhoneCountryCode(){
   return phoneCountryCode;
  }
  public void setPhoneCountryDialingCode(Integer phoneCountryDialingCode){
   this.phoneCountryDialingCode=phoneCountryDialingCode;
  }
  public Integer getPhoneCountryDialingCode(){
   return phoneCountryDialingCode;
  }
  public void setEmail(String email){
   this.email=email;
  }
  public String getEmail(){
   return email;
  }
  public void setOperationalSmsOptIn(Boolean operationalSmsOptIn){
   this.operationalSmsOptIn=operationalSmsOptIn;
  }
  public Boolean getOperationalSmsOptIn(){
   return operationalSmsOptIn;
  }
}