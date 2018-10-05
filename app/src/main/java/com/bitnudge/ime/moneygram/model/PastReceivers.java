package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class PastReceivers{
  @SerializedName("receiver")
  @Expose
  private Receiver receiver;
  @SerializedName("transactionSummaryVOS")
  @Expose
  private List<TransactionSummaryVOS> transactionSummaryVOS;
  public void setReceiver(Receiver receiver){
   this.receiver=receiver;
  }
  public Receiver getReceiver(){
   return receiver;
  }
  public void setTransactionSummaryVOS(List<TransactionSummaryVOS> transactionSummaryVOS){
   this.transactionSummaryVOS=transactionSummaryVOS;
  }
  public List<TransactionSummaryVOS> getTransactionSummaryVOS(){
   return transactionSummaryVOS;
  }
}