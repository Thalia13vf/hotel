package br.com.hotel.service;

import br.com.hotel.entity.Empresa;
import br.com.hotel.entity.Funcionario;
import br.com.hotel.entity.Politicas;
import br.com.hotel.enums.TiposDeQuarto;
import br.com.hotel.repository.FuncionarioRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PoliticaDeReservaService {

    private final EmpresaService empresaService;
    private final FuncionarioRepository funcionarioService;

    public PoliticaDeReservaService(EmpresaService empresaService, FuncionarioRepository funcionarioService) {
        this.empresaService = empresaService;
        this.funcionarioService = funcionarioService;
    }

    public void definirPoliticaDeEmpresa(Long idEmpresa, List<TiposDeQuarto> tipoDeQuarto) {
        Empresa empresa = empresaService.buscarEmpresaPorId(idEmpresa)
                .orElseThrow(() -> new RuntimeException
                        ("Não foi possível atribuir politica, pois empresa informada não existe"));
        Politicas politicas = new Politicas(tipoDeQuarto);
        empresa.setPoliticasDeReservaDeQuarto(politicas);
    }

    public void definirPoliticaDeFuncionario(Long idFuncionario, List<TiposDeQuarto> tiposDeQuarto) {
        Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorId(idFuncionario);
        if (funcionario.isPresent()) {
            Politicas politicaDeFuncionario = new Politicas(tiposDeQuarto);
            funcionario.get().setPoliticasDeReservaDeQuartos(politicaDeFuncionario);
        }

    }

    public boolean podeReservar(Long idFuncionario, TiposDeQuarto tiposDeQuarto) {
        Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorId(idFuncionario);
        if (funcionario.isPresent() && essaPoliticaEhDeFuncionario(funcionario.get())) {
           return funcionario.get().getPoliticasDeReservaDeQuartos().podeReservar(tiposDeQuarto);
        } else if(funcionarioDeveReceberPoliticaDeEmpresa(funcionario.get())) {
            return Objects.requireNonNull(buscarPoliticasEmpresa(funcionario.get().getEmpresa().getIdEmpresa())).podeReservar(tiposDeQuarto);
        }
        return true;
    }

    private boolean essaPoliticaEhDeFuncionario(Funcionario funcionario) {
        return funcionario.getPoliticasDeReservaDeQuartos() != null;
    }

    private boolean funcionarioDeveReceberPoliticaDeEmpresa(Funcionario funcionario) {
        Optional<Empresa> empresa = empresaService.buscarEmpresaPorId(funcionario.getEmpresa().getIdEmpresa());
        return !essaPoliticaEhDeFuncionario(funcionario) && empresa.get().getPoliticasDeReservaDeQuarto() != null;
    }

    private Politicas buscarPoliticasEmpresa(Long idEmpresa) {
        Optional<Empresa> empresa = empresaService.buscarEmpresaPorId(idEmpresa);
        if(empresa.isPresent() && empresa.get().getPoliticasDeReservaDeQuarto() != null) {
            return empresa.get().getPoliticasDeReservaDeQuarto();
        }
        return null;
    }
}
