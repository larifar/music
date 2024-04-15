package br.com.alura.music.repository;

import br.com.alura.music.model.Artista;
import br.com.alura.music.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNameContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a = :artista")
    List<Musica> listaMusicasDeArtista(Artista artista);
}
