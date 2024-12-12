package com.testone.util;

import com.testone.config.EncryptionConfig;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Converter
public class BankAccountNumberEncryptor implements AttributeConverter<String, String> {

    private static final String ALGORITHM = "AES";
    private final String secretKey;

    public BankAccountNumberEncryptor(EncryptionConfig encryptionConfig) {
        this.secretKey = encryptionConfig.getSecretKey();
    }

    private SecretKeySpec generateKey() {
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalStateException("Encryption secret key must be 32 characters long.");
        }
        return new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
    }

    private Cipher getCipher(int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(mode, generateKey());
        return cipher;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            if (attribute == null) return null;
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
            byte[] encryptedValue = cipher.doFinal(attribute.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during encryption", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) return null;
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
            byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(dbData));
            return new String(decryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during decryption", e);
        }
    }
}
