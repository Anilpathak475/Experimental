package com.bitnudge.ime.moneygram.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Transaction{
  @SerializedName("sendCurrency")
  @Expose
  private String sendCurrency;
  @SerializedName("biller")
  @Expose
  private Object biller;
  @SerializedName("onlineTransaction")
  @Expose
  private Boolean onlineTransaction;
  @SerializedName("receiveOption")
  @Expose
  private String receiveOption;
  @SerializedName("receiveAgentId")
  @Expose
  private Object receiveAgentId;
  @SerializedName("sourceSite")
  @Expose
  private String sourceSite;
  @SerializedName("paymentProviderOrderId")
  @Expose
  private Integer paymentProviderOrderId;
  @SerializedName("authorizationId")
  @Expose
  private Integer authorizationId;
  @SerializedName("mgoTransactionStatus")
  @Expose
  private String mgoTransactionStatus;
  @SerializedName("sendAmount")
  @Expose
  private Integer sendAmount;
  @SerializedName("sendCountry")
  @Expose
  private String sendCountry;
  @SerializedName("addressId")
  @Expose
  private Integer addressId;
  @SerializedName("australiaBpay")
  @Expose
  private Object australiaBpay;
  @SerializedName("receiveCurrency")
  @Expose
  private String receiveCurrency;
  @SerializedName("exchangeRate")
  @Expose
  private Integer exchangeRate;
  @SerializedName("referenceNumber")
  @Expose
  private Integer referenceNumber;
  @SerializedName("dateReceived")
  @Expose
  private Object dateReceived;
  @SerializedName("requiredFields")
  @Expose
  private Object requiredFields;
  @SerializedName("senderEmail")
  @Expose
  private String senderEmail;
  @SerializedName("senderMiddleName")
  @Expose
  private String senderMiddleName;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("sendFee")
  @Expose
  private Integer sendFee;
  @SerializedName("receiverRegistrationNumber")
  @Expose
  private Object receiverRegistrationNumber;
  @SerializedName("senderLastName")
  @Expose
  private String senderLastName;
  @SerializedName("productType")
  @Expose
  private String productType;
  @SerializedName("receiver")
  @Expose
  private Receiver receiver;
  @SerializedName("dateReadyForPickup")
  @Expose
  private String dateReadyForPickup;
  @SerializedName("validCurrencyIndicator")
  @Expose
  private Boolean validCurrencyIndicator;
  @SerializedName("senderFirstName")
  @Expose
  private String senderFirstName;
  @SerializedName("dynamicFields")
  @Expose
  private Object dynamicFields;
  @SerializedName("dateInitiated")
  @Expose
  private String dateInitiated;
  @SerializedName("subStatus")
  @Expose
  private String subStatus;
  @SerializedName("transactionType")
  @Expose
  private String transactionType;
  @SerializedName("receiveAmount")
  @Expose
  private Integer receiveAmount;
  @SerializedName("dateCanceled")
  @Expose
  private Object dateCanceled;
  @SerializedName("accountId")
  @Expose
  private Integer accountId;
  @SerializedName("profileId")
  @Expose
  private Integer profileId;
  @SerializedName("senderSecondLastName")
  @Expose
  private Object senderSecondLastName;
  @SerializedName("totalSendAmount")
  @Expose
  private Integer totalSendAmount;
  @SerializedName("paymentProduct")
  @Expose
  private String paymentProduct;
  @SerializedName("deliveryOption")
  @Expose
  private Integer deliveryOption;
  @SerializedName("senderPlusNumber")
  @Expose
  private Object senderPlusNumber;

    public String getSendCurrency() {
        return sendCurrency;
    }

    public Object getBiller() {
        return biller;
    }

    public Boolean getOnlineTransaction() {
        return onlineTransaction;
    }

    public String getReceiveOption() {
        return receiveOption;
    }

    public Object getReceiveAgentId() {
        return receiveAgentId;
    }

    public String getSourceSite() {
        return sourceSite;
    }

    public Integer getPaymentProviderOrderId() {
        return paymentProviderOrderId;
    }

    public Integer getAuthorizationId() {
        return authorizationId;
    }

    public String getMgoTransactionStatus() {
        return mgoTransactionStatus;
    }

    public Integer getSendAmount() {
        return sendAmount;
    }

    public String getSendCountry() {
        return sendCountry;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public Object getAustraliaBpay() {
        return australiaBpay;
    }

    public String getReceiveCurrency() {
        return receiveCurrency;
    }

    public Integer getExchangeRate() {
        return exchangeRate;
    }

    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    public Object getDateReceived() {
        return dateReceived;
    }

    public Object getRequiredFields() {
        return requiredFields;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getSenderMiddleName() {
        return senderMiddleName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSendFee() {
        return sendFee;
    }

    public Object getReceiverRegistrationNumber() {
        return receiverRegistrationNumber;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public String getProductType() {
        return productType;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public String getDateReadyForPickup() {
        return dateReadyForPickup;
    }

    public Boolean getValidCurrencyIndicator() {
        return validCurrencyIndicator;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public Object getDynamicFields() {
        return dynamicFields;
    }

    public String getDateInitiated() {
        return dateInitiated;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Integer getReceiveAmount() {
        return receiveAmount;
    }

    public Object getDateCanceled() {
        return dateCanceled;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public Object getSenderSecondLastName() {
        return senderSecondLastName;
    }

    public Integer getTotalSendAmount() {
        return totalSendAmount;
    }

    public String getPaymentProduct() {
        return paymentProduct;
    }

    public Integer getDeliveryOption() {
        return deliveryOption;
    }

    public Object getSenderPlusNumber() {
        return senderPlusNumber;
    }

    public String getStatus() {
        return status;
    }

    @SerializedName("status")
  @Expose
  private String status;
}