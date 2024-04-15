package com.globalsoftwaresupport.cryptocurrency;

import java.security.PublicKey;

public class TransactionOutput {

    private String id ;
    private String parentTransactionId ;
    private PublicKey receiver ;
    private double amount ;

    public TransactionOutput(String parentTransactionId , double amount , PublicKey receiver) {
        this.parentTransactionId = parentTransactionId ;
        this.amount = amount ;
        this.receiver = receiver ;
        generateId();
    }


}
