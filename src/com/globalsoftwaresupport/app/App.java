package com.globalsoftwaresupport.app;

import com.globalsoftwaresupport.blockchain.Block;
import com.globalsoftwaresupport.blockchain.Blockchain;
import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.cryptocurrency.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.Security;

public class App {
    public static void main(String[]args) {
        Security.addProvider(new BouncyCastleProvider());
        Wallet userA = new Wallet();
        Wallet userB = new Wallet();
        Wallet lender = new Wallet();
        Blockchain chain = new Blockchain();
        Miner miner = new Miner();

        Transaction genesisTransaction = new Transaction(lender.getPublickey(), userA.getPublickey(), 500,null);
        genesisTransaction.generateSignature(lender.getPrivatekey());
        genesisTransaction.setTransactionId("0");
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.getReceiver(),genesisTransaction.getAmount(),genesisTransaction.getTransactionId()));
        Blockchain.UTXOs.put(genesisTransaction.outputs.get(0).getId(),genesisTransaction.outputs.get(0));

        System.out.println(">>>>>........CONSTRUCTING....THE.....GENESIS....BLOCK.......>>>>>>>>");
        Block genesis = new Block(Constants.GENESIS_PREV_HASH);
        genesis.addTransaction(genesisTransaction);
        miner.mine(genesis,chain);

        Block block1 = new Block(genesis.getHash());
        System.out.println("\n UserA balance =:"+userA.calculateBalance());
        System.out.println("\n USerA tries to send money (120 AfriCoin) to User B");
        block1.addTransaction(userA.transferMoney(userB.getPublickey(), 120));
        miner.mine(block1,chain);
        System.out.println("\n UserA balance =:"+userA.calculateBalance());
        System.out.println("User B balance =:"+userB.calculateBalance());

        Block block2 = new Block(block1.getHash());
        System.out.println("\n UserA balance =:"+userA.calculateBalance());
        System.out.println("\n USerA tries to send more funds (600 AfriCoin) to User B");
        block1.addTransaction(userA.transferMoney(userB.getPublickey(), 600));
        miner.mine(block2,chain);
        System.out.println("\n UserA balance =:"+userA.calculateBalance());
        System.out.println("User B balance =:"+userB.calculateBalance());

        Block block3 = new Block(block2.getHash());

        System.out.println("\n USerB tries to send money (120 AfriCoin) to User A");
        block1.addTransaction(userB.transferMoney(userB.getPublickey(), 120));
        miner.mine(block3,chain);
        System.out.println("\n UserA balance =:"+userA.calculateBalance());
        System.out.println("User B balance =:"+userB.calculateBalance());

        System.out.println("Miner's reward: "+miner.getReward());

    }
}
