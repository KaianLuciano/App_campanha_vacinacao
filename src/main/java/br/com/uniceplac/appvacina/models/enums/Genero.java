package br.com.uniceplac.appvacina.models.enums;

public enum Genero {
    HOMEM(1),
    MULHER(2);

    private int value;

    Genero(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isValidValue(int value) {
        for (Genero genero : Genero.values()) {
            if (genero.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
