package co.com.pragma.model.key.config;

import lombok.Getter;

@Getter
public enum KeyType {
    IDENTIFICATION("IDENTIFICATION"),
    BBAN("BBAN"),
    EMAIl("EMAIL"),
    MSISDN("MSISDN"),
    MERCHANTID("MERCHANTID");

    private final String type;

    KeyType(String type) {
        this.type = type;
    }
}
