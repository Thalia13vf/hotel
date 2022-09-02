package br.com.hotel.service;

import br.com.hotel.entity.Funcionario;
import br.com.hotel.repository.FuncionarioRepository;

public class EmpresaService {

    private FuncionarioRepository funcionarioRepository;
    public void criarFuncionario(Long idEmpresa, Long idFuncionario) {
        if (funcionarioJaExiste(idEmpresa, idFuncionario)) {
            throw new RuntimeException("Funcionario já existe!");
        }
        funcionarioRepository.adicionarFuncionario(new Funcionario(idFuncionario));
    }

    private boolean funcionarioJaExiste(Long idEmpresa, Long idFuncionario) {
        return funcionarioRepository.funcionarioJaExiste(idFuncionario);
    }

    public void excluirFuncionarios(Long idFuncionario) {
        funcionarioRepository.excluirFuncionario(idFuncionario);
    }
}
