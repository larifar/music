package br.com.alura.music.model;

public enum TipoArtista {
    SOLO("solo"),
    DUO("duo"),
    BAND("band");

    private String tipo;

    TipoArtista(String tipo){
        this.tipo = tipo;
    }

    public static TipoArtista fromString(String text) {
        for (TipoArtista tipoArtista : TipoArtista.values()) {
            if (tipoArtista.tipo.equalsIgnoreCase(text)) {
                return tipoArtista;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
