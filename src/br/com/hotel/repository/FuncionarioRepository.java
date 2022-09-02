package br.com.hotel.repository;

import br.com.hotel.entity.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private List<Funcionario> funcionarios;

    public FuncionarioRepository() {
        this.funcionarios = new ArrayList<>();
    }

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
        return funcionario;
    }

    public boolean funcionarioJaExiste(Long idFuncionario) {
        return this.funcionarios.stream().anyMatch(funcionario -> funcionario.getIdFuncionario().equals(idFuncionario));
    }

    public void excluirFuncionario(Long idFuncionario) {
        
    }
}
