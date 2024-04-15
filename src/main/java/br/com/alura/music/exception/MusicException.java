package br.com.alura.music.exception;

public class MusicException extends RuntimeException{
    MusicException(){
        super();
    }

    public MusicException(String mensagem) {
        super(mensagem);
    }

    public MusicException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
