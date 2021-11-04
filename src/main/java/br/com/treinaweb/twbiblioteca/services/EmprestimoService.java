package br.com.treinaweb.twbiblioteca.services;

import java.time.LocalDate;
import java.util.List;

import br.com.treinaweb.twbiblioteca.enums.Reputacao;
import br.com.treinaweb.twbiblioteca.models.Cliente;
import br.com.treinaweb.twbiblioteca.models.Emprestimo;
import br.com.treinaweb.twbiblioteca.models.Obra;

public class EmprestimoService {
    
    public Emprestimo novo(Cliente cliente, List<Obra> obras) {
    if (cliente == null) {
        throw new IllegalArgumentException("Cliente não pode ser nulo");
    }

    if(obras == null || obras.isEmpty()) {
        throw new IllegalArgumentException("Obra não pode ser nulo e nem vazio");
    }

        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        var diasParaDevolucao = cliente.getReputacao().obterDiasParaDevolucao();
        var dataDevolocao = dataEmprestimo.plusDays(diasParaDevolucao);
        
        emprestimo.setCliente(cliente);
        emprestimo.setLivros(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolocao);

        return emprestimo;
    }

}
