package br.com.uniceplac.appvacina.models.enums;

public enum TipoUsuario {
    ADMINSTRADOR(1),
    USUARIO(2);

    private int value;

    TipoUsuario(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isValidValue(int value) {
        for (TipoUsuario usuario : TipoUsuario.values()) {
            if (usuario.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
