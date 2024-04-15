package com.globalsoftwaresupport.cryptocurrency;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class CryptographyHelper {

    public static String generateHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            StringBuilder hexDecimalString = new StringBuilder();
            for(int i = 0 ; i < hash.length ; i++) {
                String hexDecimal = Integer.toHexString(0xff & hash[i]);
                if(hexDecimal.length()==1) hexDecimalString.append('0');
                hexDecimalString.append(hexDecimal);
            }
            return hexDecimalString.toString();

        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[] sign(PrivateKey privateKey , String input) {
        Signature signature ;
        byte[] output = new byte[0];
        try{
            signature = Signature.getInstance("ECDSA","BC");
            signature.initSign(privateKey);
            signature.update(input.getBytes());
            output = signature.sign();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        return output ;
    }
    public static KeyPair ellipticCurveCrypto() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA","BC");
            ECGenParameterSpec params = new ECGenParameterSpec("prime256v1");
            keyPairGenerator.initialize(params);
            return keyPairGenerator.generateKeyPair();

        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static boolean verify(PublicKey publicKey , String data , byte[] signature) {

        try {
            Signature ecdsaSignature = Signature.getInstance("ECDSA","BC");
            ecdsaSignature.initVerify(publicKey);
            ecdsaSignature.update(data.getBytes());
            return ecdsaSignature.verify(signature);

        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }

    }
}
