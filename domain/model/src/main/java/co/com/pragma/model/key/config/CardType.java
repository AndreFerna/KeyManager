package co.com.pragma.model.key.config;

import lombok.Getter;

@Getter
public enum CardType {
    CUENTA_DE_AHORRO("CUENTA_DE_AHORRO"),
    CUENTA_CORRIENTE("CUENTA_CORRIENTE"),
    TARJETA_DE_CREDITO("TARJETA_CREDITO");

    private final String type;

    CardType(String type) {
        this.type = type;
    }
}
