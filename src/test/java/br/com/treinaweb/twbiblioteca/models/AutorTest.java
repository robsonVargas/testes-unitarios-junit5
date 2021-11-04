package br.com.treinaweb.twbiblioteca.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutorTest {

    @Test
    public void quandoMetodoEstaVivoForChamadoComDataFalecimentoNulaDeveRetornarTrue() {
        // cenário
        var autor = new Autor();

        // execuçao
        var estaVivo = autor.estaVivo();

        // verificação
        assertEquals(true, estaVivo);
    }

    @Test
    public void quandoMetodoEstaVivoForChamadoComDataFalecimentoPreenchidaDeveRetornarFalse() {
        // cenario
        var autor = new Autor();
        autor.setDataFalecimento(LocalDate.now());

        // execução
        var estaVivo = autor.estaVivo();

        // verificação
        assertEquals(false, estaVivo);
    }
}
