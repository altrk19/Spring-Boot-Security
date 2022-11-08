package com.spring.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        keyGenerator();

        encryptDecrypt();


    }

    private void keyGenerator() {
        StringKeyGenerator keyGenerator = KeyGenerators.string();
        String salt = keyGenerator.generateKey();

        System.out.println(salt);

        BytesKeyGenerator keyGeneratorByte = KeyGenerators.secureRandom();
        byte[] key = keyGeneratorByte.generateKey();
        int keyLength = keyGeneratorByte.getKeyLength();
//        for 16 byte key generation
//        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom(16);
    }


    private void encryptDecrypt() {
        String salt = KeyGenerators.string().generateKey();
        String secret = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.standard(secret, salt);     //AES encryptor
        byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
        System.out.println("Encrypted data is :" + new String(encrypted, StandardCharsets.UTF_8));
        byte[] decrypted = e.decrypt(encrypted);
        System.out.println("Decrypted data is :" + new String(decrypted, StandardCharsets.UTF_8));

        TextEncryptor textEncryptor = Encryptors.queryableText(secret, salt);
        String encrypted1 = textEncryptor.encrypt(valueToEncrypt);
        System.out.println("Encrypted data is :" + encrypted1);
        String decrypted1 = textEncryptor.decrypt(encrypted1);
        System.out.println("Decrypted data is :" + decrypted1);


    }
}
