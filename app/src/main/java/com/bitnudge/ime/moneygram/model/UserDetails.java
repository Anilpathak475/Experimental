package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class UserDetails{
  @SerializedName("accesTokenInfo")
  @Expose
  private AccesTokenInfo accesTokenInfo;
  @SerializedName("consumerProfile")
  @Expose
  private ConsumerProfile consumerProfile;
  public void setAccesTokenInfo(AccesTokenInfo accesTokenInfo){
   this.accesTokenInfo=accesTokenInfo;
  }
  public AccesTokenInfo getAccesTokenInfo(){
   return accesTokenInfo;
  }
  public void setConsumerProfile(ConsumerProfile consumerProfile){
   this.consumerProfile=consumerProfile;
  }
  public ConsumerProfile getConsumerProfile(){
   return consumerProfile;
  }
}