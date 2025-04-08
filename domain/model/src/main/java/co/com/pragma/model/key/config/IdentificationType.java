package co.com.pragma.model.key.config;

import lombok.Getter;

@Getter
public enum IdentificationType {
    CARNE_DIPLOMATICO("CD"),
    CEDULA_DE_CIUDADANIA("CC"),
    CEDULA_DE_EXTRANJERIA("CE"),
    NIT("NIT"),
    TARJETA_DE_IDENTIDAD("TI"),
    PASAPORTE("PS"),
    ID_EXTRANJERO_PN("NIE"),
    ID_EXTRANJERO_PJ("NITE"),
    FIDECOMISO("FD"),
    REGISTRO_CIVIL("RC"),
    GRUPO("GR");

    private final String type;

    IdentificationType(String type) {
        this.type = type;
    }
}
