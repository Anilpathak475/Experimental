package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class ConsumerProfile{
  @SerializedName("profileId")
  @Expose
  private Integer profileId;
  @SerializedName("profile")
  @Expose
  private Profile profile;
  @SerializedName("amountSent30Days")
  @Expose
  private Integer amountSent30Days;
  @SerializedName("premier")
  @Expose
  private Boolean premier;
  @SerializedName("existingDriversLicense")
  @Expose
  private Boolean existingDriversLicense;
  @SerializedName("username")
  @Expose
  private String username;
  @SerializedName("status")
  @Expose
  private String status;
  public void setProfileId(Integer profileId){
   this.profileId=profileId;
  }
  public Integer getProfileId(){
   return profileId;
  }
  public void setProfile(Profile profile){
   this.profile=profile;
  }
  public Profile getProfile(){
   return profile;
  }
  public void setAmountSent30Days(Integer amountSent30Days){
   this.amountSent30Days=amountSent30Days;
  }
  public Integer getAmountSent30Days(){
   return amountSent30Days;
  }
  public void setPremier(Boolean premier){
   this.premier=premier;
  }
  public Boolean getPremier(){
   return premier;
  }
  public void setExistingDriversLicense(Boolean existingDriversLicense){
   this.existingDriversLicense=existingDriversLicense;
  }
  public Boolean getExistingDriversLicense(){
   return existingDriversLicense;
  }
  public void setUsername(String username){
   this.username=username;
  }
  public String getUsername(){
   return username;
  }
  public void setStatus(String status){
   this.status=status;
  }
  public String getStatus(){
   return status;
  }
}