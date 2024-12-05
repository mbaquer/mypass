package com.mypass.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class EncryptUtils {

    private static final String ALGORITHM = "AES";
    private static final int KEYSIZE = 128;

    public static String encrypt(String data, String key) throws GeneralSecurityException {
        SecretKeySpec secretKey = new SecretKeySpec(padKey(key).getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String encryptedData, String key) throws GeneralSecurityException {
        SecretKeySpec secretKey = new SecretKeySpec(padKey(key).getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] cipherText = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(cipherText));
    }

    public static String generateKey() throws GeneralSecurityException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEYSIZE);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    private static String padKey(String key) {
        return String.format("%1$-" + KEYSIZE / 8 + "s", key).replace(' ', '0');
    }
}