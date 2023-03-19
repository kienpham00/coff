package com.kienpham.coffee.security;

import org.apache.commons.ssl.PKCS8Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Configuration
public class KeyGeneratorConfig {


    @Value("${jwt.privateKeyPath}")
    private String privateKeyPath; //Encoded private key string
    @Value("${jwt.passphrase}")
    String passphrase;


    @Bean
    public PrivateKey generatePrivateKey() throws IOException, GeneralSecurityException {

         InputStream in = getFileAsIOStream(privateKeyPath);
         return generatePrivateKey(in);
    }


    public PrivateKey generatePrivateKey(InputStream inputStream) throws GeneralSecurityException, IOException {

        PKCS8Key pkcs8 = new PKCS8Key(inputStream, passphrase.toCharArray());

        byte[] decrypted = pkcs8.getDecryptedBytes();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decrypted);
        RSAPrivateKey privKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);
        return privKey;

    }

    private InputStream getFileAsIOStream(String fileName) {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            try {
                URL fileUrl = this.getClass().getResource(fileName);
                File file = new File(fileUrl.getFile());
                return new FileInputStream(file);
            } catch (FileNotFoundException var5) {
                throw new IllegalArgumentException(var5.getMessage());
            }
        } else {
            return stream;
        }
    }
}
