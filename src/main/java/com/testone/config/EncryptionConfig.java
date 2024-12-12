package com.testone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionConfig {

    @Value("${encryption.secret-key}")
    private String secretKey;

    @Value("${encryption.salt}")
    private String salt;

    public String getSecretKey() {
        return secretKey;
    }

    public String getSalt() {
        return salt;
    }
}
