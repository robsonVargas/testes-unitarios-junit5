package br.com.treinaweb.twbiblioteca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.treinaweb.twbiblioteca.enums.Reputacao;
import br.com.treinaweb.twbiblioteca.enums.Tipo;
import br.com.treinaweb.twbiblioteca.models.Autor;
import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Obra;

public class EmprestimoServiceTest {
    
    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.133-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(cliente, emprestimo.getCliente());
        assertEquals(List.of(obra), emprestimo.getLivros());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoRuimDeveRetornarUmEmprestimoComDevolucaoParaUmDia() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.133-11", Reputacao.RUIM);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        //execuçao
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoRegularDeveRetornarUmEmprestimoComDevolucaoParaTresDias() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.133-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        //execuçao
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputaçãoBoaDeveRetornarUmEmprestimoComDevolucaoParaCincoDias() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.133-11", Reputacao.BOA);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        //execuçao
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        //verificação
        assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraNuloDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.133-11", Reputacao.REGULAR);
        var mensagemEsperada = "Obra não pode ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

     @Test
    void quandoMetodoNovoForChamadoComObraVaziaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.133-11", Reputacao.REGULAR);
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obra não pode ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, obras));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);
        var mensagemEsperada = "Cliente não pode ser nulo";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
