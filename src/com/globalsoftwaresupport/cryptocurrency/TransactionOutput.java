package com.globalsoftwaresupport.cryptocurrency;

import java.security.PublicKey;

public class TransactionOutput {

    private String id ;
    private String parentTransactionId ;
    private PublicKey receiver ;
    private double amount ;

    public TransactionOutput(PublicKey receiver, double amount ,String parentTransactionId ) {
        this.parentTransactionId = parentTransactionId ;
        this.amount = amount ;
        this.receiver = receiver ;
        generateId();
    }
    public void generateId() {

        this.id = CryptographyHelper.generateHash(receiver.toString() + Double.toString(amount) + parentTransactionId);
    }
    public boolean isMine(PublicKey publickey) {
        return publickey == receiver ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public void setReceiver(PublicKey receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
