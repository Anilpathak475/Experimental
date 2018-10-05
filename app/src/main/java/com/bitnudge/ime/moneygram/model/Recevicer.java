package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Recevicer{
  @SerializedName("pastReceivers")
  @Expose
  private List<PastReceivers> pastReceivers;
  public List<PastReceivers> getPastReceivers(){
   return pastReceivers;
  }
}