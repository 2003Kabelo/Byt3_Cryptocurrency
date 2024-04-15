package com.globalsoftwaresupport.cryptocurrency;

import com.globalsoftwaresupport.blockchain.Block;
import com.globalsoftwaresupport.blockchain.Blockchain;
import com.globalsoftwaresupport.constants.Constants;

public class Miner {

    private double reward ;

    public void mine(Block block , Blockchain blockchain) {
        while(!isGoldenHash(block)) {
            block.incrementNonce();
            block.generateHash();
        }
        System.out.println(block + " has just mined...........");
        System.out.println("Hash is : "+ block.getHash());
        blockchain.addBlock(block);
        reward += Constants.REWARD ;
    }
    public boolean isGoldenHash(Block block) {

        String leadingZeroes = new String(new char[Constants.DIFFICULTY]).replace('\0','0');
        return block.getHash().substring(0,Constants.DIFFICULTY).equals(leadingZeroes);

    }
    public double getReward() {
        return this.reward;
    }

}
