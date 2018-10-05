package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class TransactionDetail{
  public Transaction getTransaction() {
    return transaction;
  }

  @SerializedName("transaction")
  @Expose
  private Transaction transaction;
}