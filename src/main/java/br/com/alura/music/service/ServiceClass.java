package br.com.alura.music.service;

import br.com.alura.music.exception.MusicException;
import br.com.alura.music.model.Artista;
import br.com.alura.music.model.Musica;
import br.com.alura.music.repository.ArtistaRepository;
import br.com.alura.music.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceClass {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public ServiceClass(ArtistaRepository repository, MusicaRepository musicaRepository){
        this.artistaRepository = repository;
        this.musicaRepository = musicaRepository;
    }


    public void salvarArtista(String nome, String tipo){
        try{
            Artista artista = new Artista(nome, tipo);
            artistaRepository.save(artista);
            System.out.println("Artista: " + artista.getName() + " cadastrado!");
        } catch (RuntimeException e){
            throw new MusicException(e.getMessage());
        } 
    }

    public Artista procuraArtista(String nome){
        try {
            var artista = artistaRepository.findByNameContainingIgnoreCase(nome);
            if (artista.isPresent()){
                return artista.get();
            } else {
                throw new MusicException("Não foi possível achar um artista cadastrado com esse nome: " + nome);
            }
        } catch (RuntimeException e){
            throw new MusicException(e.getMessage());
        }
    }

    public void cadastrarMusica(Artista artista, String nome, LocalDate data){
        try {
            var musica = new Musica(nome, artista, data);
            musicaRepository.save(musica);
            System.out.println("Musica: " + nome + " cadastrada!");
        } catch (RuntimeException e){
            throw new MusicException("Não foi possível cadastrar a música: "+e.getMessage());
        }
    }

    public List<Artista> listarArtistas(){
        return artistaRepository.findAll();
    }

    public List<Musica> listaMusicasDeArtista(String artista){
        try{
            var artistaEncontrado = procuraArtista(artista);

            return artistaRepository.listaMusicasDeArtista(artistaEncontrado);
        } catch (MusicException e){
            throw new MusicException(e.getMessage());
        }
    }

    public List<Musica> listarMusicas(){
        return musicaRepository.findAll();
    }
}
