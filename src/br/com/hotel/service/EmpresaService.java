package br.com.hotel.service;

import br.com.hotel.entity.Empresa;
import br.com.hotel.entity.Funcionario;
import br.com.hotel.repository.EmpresaRepository;
import br.com.hotel.repository.FuncionarioRepository;

import java.util.Optional;

public class EmpresaService {
    private FuncionarioRepository funcionarioRepository;
    private EmpresaRepository empresaRepository;

    public EmpresaService(FuncionarioRepository funcionarioRepository, EmpresaRepository empresaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.empresaRepository = empresaRepository;
    }

    public void criarFuncionario(Long idEmpresa, Long idFuncionario) {
        Empresa empresa = empresaRepository.buscarEmpresaPorId(idEmpresa).get();

        if (funcionarioJaExiste(idFuncionario)) {
            throw new RuntimeException("Funcionario já existe!");
        }
        funcionarioRepository.adicionarFuncionario(new Funcionario(idEmpresa, idFuncionario));
    }

    public boolean funcionarioJaExiste( Long idFuncionario) {
        return funcionarioRepository.funcionarioJaExiste(idFuncionario);
    }

    public void excluirFuncionarios(Long idFuncionario) {
        funcionarioRepository.excluirFuncionario(idFuncionario);
    }

    public Optional<Empresa> buscarEmpresaPorId(Long idEmpresa){
        return empresaRepository.buscarEmpresaPorId(idEmpresa);
    }

    public void criarEmpresa(Empresa empresa) {
        empresaRepository.criarEmpresa(empresa);
    }
}
