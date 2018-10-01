package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class AddressInfos{
  @SerializedName("country")
  @Expose
  private String country;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("postalCode")
  @Expose
  private Integer postalCode;
  @SerializedName("addressLine1")
  @Expose
  private String addressLine1;
  @SerializedName("state")
  @Expose
  private String state;
  @SerializedName("addressId")
  @Expose
  private Integer addressId;
  @SerializedName("homeAddress")
  @Expose
  private Boolean homeAddress;
  public void setCountry(String country){
   this.country=country;
  }
  public String getCountry(){
   return country;
  }
  public void setCity(String city){
   this.city=city;
  }
  public String getCity(){
   return city;
  }
  public void setPostalCode(Integer postalCode){
   this.postalCode=postalCode;
  }
  public Integer getPostalCode(){
   return postalCode;
  }
  public void setAddressLine1(String addressLine1){
   this.addressLine1=addressLine1;
  }
  public String getAddressLine1(){
   return addressLine1;
  }
  public void setState(String state){
   this.state=state;
  }
  public String getState(){
   return state;
  }
  public void setAddressId(Integer addressId){
   this.addressId=addressId;
  }
  public Integer getAddressId(){
   return addressId;
  }
  public void setHomeAddress(Boolean homeAddress){
   this.homeAddress=homeAddress;
  }
  public Boolean getHomeAddress(){
   return homeAddress;
  }
}