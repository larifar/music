package br.com.alura.music.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    private Artista artista;

    private LocalDate dataLancamento;

    public Musica(String nome, Artista artista, LocalDate data){
        this.name = nome;
        this.artista = artista;
        this.dataLancamento = data;
    }

    public Musica() {

    }

    //get e set

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Música: " + name +
                "; Artista: " + artista.getName() +
                "; Lançamento: " + dataLancamento.toString();
    }
}
