package com.globalsoftwaresupport.blockchain;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.cryptocurrency.CryptographyHelper;
import com.globalsoftwaresupport.cryptocurrency.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {

    private int id ;
    private int nonce ;
    private long timeStamp ;
    private String hash ;
    private String previousHash ;
    public List<Transaction> transactions ;

    public Block(String previousHash) {
        this.transactions = new ArrayList<Transaction>();
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        generateHash();
    }
    public void generateHash() {
        String hashData = Integer.toString(id) + Long.toString(timeStamp) + transactions.toString() + Integer.toString(nonce);
        this.hash = CryptographyHelper.generateHash(hashData);
    }
    public boolean addTransaction(Transaction transaction) {
        if(transaction == null) return false ;
        if((!previousHash.equals(Constants.GENESIS_PREV_HASH))){
            if((!transaction.verifyTransaction())){
                System.out.println("The Transaction is invalid>>>>");
                return false ;
            }
        }
        transactions.add(transaction);
        System.out.println("The Transaction is Valid And is appended to the block<<<<<<<<<"+this);
        return true ;
    }
    public void incrementNonce() {
        this.nonce++;
    }
    public String getHash() {
        return this.hash;
    }
}
