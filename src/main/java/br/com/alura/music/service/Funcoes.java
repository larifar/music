package br.com.alura.music.service;

import br.com.alura.music.exception.MusicException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcoes {

    public static LocalDate formatarStringParaData(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(string, formatter);
            return data;
        } catch (RuntimeException e){
            throw new MusicException("Não foi possivel formatar a data, tente escrever no padrão: 'dd/MM/yyyy'");
        }

    }
}
