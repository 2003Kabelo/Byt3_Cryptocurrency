package com.globalsoftwaresupport.blockchain;

import com.globalsoftwaresupport.cryptocurrency.TransactionOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Blockchain {
    public static ArrayList<Block> blockChain ;
    public static Map<String, TransactionOutput> UTXOs ;

    public Blockchain() {
        Blockchain.UTXOs = new HashMap<String,TransactionOutput>();
        Blockchain.blockChain = new ArrayList<>();
    }
    public void addBlock(Block block) {
        Blockchain.blockChain.add(block);
    }
    public int size() {
        return Blockchain.blockChain.size();
    }
    @Override
    public String toString() {
        String blockChain = " ";
        for(Block block : Blockchain.blockChain)
            blockChain += block.toString() + "\n";

        return blockChain;

    }
}

