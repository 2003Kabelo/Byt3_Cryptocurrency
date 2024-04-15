package com.globalsoftwaresupport.cryptocurrency;

import com.globalsoftwaresupport.blockchain.Blockchain;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey ;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Wallet {

    PrivateKey privatekey ;
    PublicKey publickey ;

    public Wallet() {

        KeyPair keys = CryptographyHelper.ellipticCurveCrypto();
        this.privatekey = keys.getPrivate();
        this.publickey = keys.getPublic();

    }
    public Transaction transferMoney(PublicKey receiver ,double amount) {
        if(calculateBalance() < amount) {
            System.out.println("Insufficient funds!!!!");
            return null;
        }
        List<TransactionInput> inputs = new ArrayList<TransactionInput>();
        for(Map.Entry<String,TransactionOutput> items : Blockchain.UTXOs.entrySet()) {
            TransactionOutput UTXO = items.getValue();
            if(UTXO.isMine(this.publickey))
                inputs.add(new TransactionInput(UTXO.getId()));
        }
        Transaction newTrans = new Transaction(publickey,receiver,amount,inputs);
        newTrans.generateSignature(privatekey);
        return newTrans;

    }
    public double calculateBalance() {
        double balance = 0;

        for (Map.Entry<String, TransactionOutput> item : Blockchain.UTXOs.entrySet()) {
            TransactionOutput transactionOutput = item.getValue();
            if (transactionOutput.isMine(publickey))
                balance += transactionOutput.getAmount();

        }
        return balance;
    }

    public PrivateKey getPrivatekey() {
        return privatekey;
    }

    public PublicKey getPublickey() {
        return publickey;
    }
}
