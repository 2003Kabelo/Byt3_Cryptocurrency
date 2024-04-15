package com.globalsoftwaresupport.app;

import com.globalsoftwaresupport.cryptocurrency.CryptographyHelper;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.Security;

public class App {
    public static void main(String[]args) {
        Security.addProvider(new BouncyCastleProvider());
        KeyPair keys = CryptographyHelper.ellipticCurveCrypto();
        System.out.println(keys.getPrivate().toString());
    }
}
