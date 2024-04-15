package br.com.alura.music.principal;

import br.com.alura.music.exception.MusicException;
import br.com.alura.music.repository.ArtistaRepository;
import br.com.alura.music.repository.MusicaRepository;
import br.com.alura.music.service.Funcoes;
import br.com.alura.music.service.ServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private Scanner scan = new Scanner(System.in);

    private ServiceClass service;
    private ArtistaRepository repository;
    private MusicaRepository musicaRepository;

    @Autowired
    public Menu(ArtistaRepository artistaRepository, ServiceClass service, MusicaRepository music){
        this.service = service;
        this.repository = artistaRepository;
        this.musicaRepository = music;
    }


    public void showMenu(){
        int op;
        do{
            var menu = """
                    \n*******************************************\n
                    1 - Cadastrar artista
                    2 - Cadastrar música
                    3 - Listar artistas
                    4 - Listar músicas por artistas
                    5 - Listar músicas
                    
                    0 - Sair
                    \n*******************************************\n
                    """;

            System.out.println(menu);
            op = scan.nextInt();
            scan.nextLine();

            switch (op){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarArtistas();
                    break;
                case 4:
                    listarMusicasDeArtista();
                    break;
                case 5:
                    listarMusicas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }

    public void cadastrarArtista(){
        var op = "S";
        do{
            System.out.println("Digite o nome do artista: ");
            var artista = scan.nextLine();
            System.out.println("Qual categoria: solo, duo ou band? ");
            var categoria = scan.nextLine().toLowerCase();
            try {
                service.salvarArtista(artista, categoria);
            } catch (MusicException e){
                System.out.println("Não foi possível cadastrar o artista, porque: " + e.getMessage());
            }
            System.out.println("Deseja cadastrar outro artista? (S/N)");
            op = scan.nextLine().toUpperCase();

        } while(op.equals("S"));


    }
    public void cadastrarMusica(){
        listarArtistas();
        System.out.println("Digite o nome do artista para cadastrar a música: ");
        var artista = scan.nextLine();
        try {
            var artistaEncontrado = service.procuraArtista(artista);
            System.out.println("Digite o nome da música: ");
            var nomeMusica = scan.nextLine();
            System.out.println("Digite a data de lançamento da música (DD/MM/YYYY)");
            var data = scan.nextLine();
            var dataFormata = Funcoes.formatarStringParaData(data);
            try {
                service.cadastrarMusica(artistaEncontrado, nomeMusica, dataFormata);
            } catch (RuntimeException e){
                throw new MusicException(e.getMessage());
            }
        } catch (MusicException e){
            System.out.println(e.getMessage());
        }


    }
    public void listarArtistas(){
        System.out.println(
                """
                       **************************************************************
                       \n     Artistas Cadastrados:
                        """
        );
        var lista = service.listarArtistas();
        lista.forEach(System.out::println);
    }
    public void listarMusicasDeArtista(){
        System.out.println("Digite o nome do artista que deseja buscar as músicas: ");
        var artista = scan.nextLine();

        try {
            var musicas = service.listaMusicasDeArtista(artista);
            if(!musicas.isEmpty()){
                musicas.forEach(System.out::println);
            } else {
                System.out.println("Esse artista ainda não tem nenhuma música cadastrada!");
            }
        } catch (MusicException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarMusicas(){
        System.out.println(
                """
                       **************************************************************
                       \n     Músicas Cadastrados:
                        """
        );
        var lista = service.listarMusicas();
        lista.forEach(System.out::println);
    }
}
