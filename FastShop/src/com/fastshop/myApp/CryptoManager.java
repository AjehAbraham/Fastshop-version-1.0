package com.fastshop.myApp;


import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.security.Key;
import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoManager {
    private static final String KEY_ALIAS = "my_key_alias";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int IV_SIZE = 12;
    private static final int TAG_SIZE = 128;

    private KeyStore keyStore;
    private Key key;

    public CryptoManager(Context context) throws Exception {
        keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (!keyStore.containsAlias(KEY_ALIAS)) {
            generateKey();
        }
        key = keyStore.getKey(KEY_ALIAS, null);
    }

    private void generateKey() throws Exception {
        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .build();

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        keyGenerator.init(keyGenParameterSpec);
        keyGenerator.generateKey();
    }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] iv = cipher.getIV();
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        byte[] result = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, result, iv.length, encryptedBytes.length);
        return Base64.encodeToString(result, Base64.DEFAULT);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] encryptedBytes = Base64.decode(encryptedData, Base64.DEFAULT);
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(encryptedBytes, 0, iv, 0, IV_SIZE);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes, IV_SIZE, encryptedBytes.length - IV_SIZE);
        return new String(decryptedBytes);
    }

    public String encryptInt(int data) throws Exception {
        return encrypt(String.valueOf(data));
    }

    public int decryptInt(String encryptedData) throws Exception {
        return Integer.parseInt(decrypt(encryptedData));
    }
}

//CALLING IT IN ANY CLASS 

/*
 CryptoManager cryptoManager = new CryptoManager(context);
String encryptedData = cryptoManager.encrypt("Hello World");
String decryptedData = cryptoManager.decrypt(encryptedData);

String encryptedInt = cryptoManager.encryptInt(123);
int decryptedInt = cryptoManager.decryptInt(encryptedInt);

 */