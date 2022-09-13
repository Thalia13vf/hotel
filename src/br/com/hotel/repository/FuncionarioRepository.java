package br.com.hotel.repository;

import br.com.hotel.entity.Empresa;
import br.com.hotel.entity.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioRepository {
    private List<Funcionario> funcionarios;
    private final EmpresaRepository empresaRepository;

    public FuncionarioRepository(EmpresaRepository empresaRepository) {
        this.funcionarios = new ArrayList<>();
        this.empresaRepository = empresaRepository;
    }

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        Empresa empresa = this.empresaRepository.buscarEmpresaPorId(funcionario.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa não existe por isso não foi possivel adicionar funcionario."));
        funcionario.setIdEmpresa(empresa.getIdEmpresa());
        empresa.setFuncionarios(funcionario);
        funcionarios.add(funcionario);
        return funcionario;
    }

    public boolean funcionarioJaExiste(Long idFuncionario) {
        return this.funcionarios.stream().anyMatch(funcionario -> funcionario.getIdFuncionario().equals(idFuncionario));
    }

    public void excluirFuncionario(Long idFuncionario) {
        this.funcionarios
                .removeIf(funcionario -> funcionario.getIdFuncionario().equals(idFuncionario));
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long idFuncionario){
        //Optional<Empresa> empresa = empresaRepository.buscarPorId(idEmpresa);
        Optional<Funcionario> funcionarioEncontrado =
                this.funcionarios
                .stream()
                .filter(funcionario -> funcionario.getIdFuncionario().equals(idFuncionario))
                .findFirst();
        return funcionarioEncontrado;
    }
}
