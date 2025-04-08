package co.com.pragma.model.key.config;

import lombok.Getter;

@Getter
public enum KeyStatus {
    BLOQUEAR("BLOQUEAR"),
    DESBLOQUEAR("DESBLOQUEAR"),
    CANCELAR("CANCELAR");

    private final String status;

    KeyStatus(String status) {
        this.status = status;
    }
}
