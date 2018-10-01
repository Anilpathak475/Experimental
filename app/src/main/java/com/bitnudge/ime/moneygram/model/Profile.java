package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Profile{
  @SerializedName("personalInfo")
  @Expose
  private PersonalInfo personalInfo;
  @SerializedName("sourceCountry")
  @Expose
  private String sourceCountry;
  @SerializedName("contactInfo")
  @Expose
  private ContactInfo contactInfo;
  @SerializedName("occupationCode")
  @Expose
  private Integer occupationCode;
  @SerializedName("addressInfos")
  @Expose
  private List<AddressInfos> addressInfos;
  @SerializedName("accountInfos")
  @Expose
  private List<AccountInfos> accountInfos;
  public void setPersonalInfo(PersonalInfo personalInfo){
   this.personalInfo=personalInfo;
  }
  public PersonalInfo getPersonalInfo(){
   return personalInfo;
  }
  public void setSourceCountry(String sourceCountry){
   this.sourceCountry=sourceCountry;
  }
  public String getSourceCountry(){
   return sourceCountry;
  }
  public void setContactInfo(ContactInfo contactInfo){
   this.contactInfo=contactInfo;
  }
  public ContactInfo getContactInfo(){
   return contactInfo;
  }
  public void setOccupationCode(Integer occupationCode){
   this.occupationCode=occupationCode;
  }
  public Integer getOccupationCode(){
   return occupationCode;
  }
  public void setAddressInfos(List<AddressInfos> addressInfos){
   this.addressInfos=addressInfos;
  }
  public List<AddressInfos> getAddressInfos(){
   return addressInfos;
  }
  public void setAccountInfos(List<AccountInfos> accountInfos){
   this.accountInfos=accountInfos;
  }
  public List<AccountInfos> getAccountInfos(){
   return accountInfos;
  }
}