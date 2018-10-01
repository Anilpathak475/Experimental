package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class PersonalInfo{
  @SerializedName("firstName")
  @Expose
  private String firstName;
  @SerializedName("lastName")
  @Expose
  private String lastName;
  @SerializedName("dateOfBirth")
  @Expose
  private String dateOfBirth;
  public void setFirstName(String firstName){
   this.firstName=firstName;
  }
  public String getFirstName(){
   return firstName;
  }
  public void setLastName(String lastName){
   this.lastName=lastName;
  }
  public String getLastName(){
   return lastName;
  }
  public void setDateOfBirth(String dateOfBirth){
   this.dateOfBirth=dateOfBirth;
  }
  public String getDateOfBirth(){
   return dateOfBirth;
  }
}