package br.com.hotel.test;

import br.com.hotel.entity.Empresa;
import br.com.hotel.entity.Funcionario;
import br.com.hotel.entity.Politicas;
import br.com.hotel.enums.TiposDeQuarto;
import br.com.hotel.repository.EmpresaRepository;
import br.com.hotel.repository.FuncionarioRepository;
import br.com.hotel.service.EmpresaService;
import br.com.hotel.service.PoliticaDeReservaService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

public class PoliticasDeReservaTest {

    EmpresaRepository empresaRepository = new EmpresaRepository();
    FuncionarioRepository funcionarioRepository = new FuncionarioRepository(empresaRepository);

    @Mock
    private EmpresaService empresaService = new EmpresaService(funcionarioRepository,empresaRepository);

    @InjectMocks
    private PoliticaDeReservaService politicaDeReservaService =
            new PoliticaDeReservaService(empresaService, funcionarioRepository);

    @Test
    public void criarPoliticaParaEmpresa(){
        Empresa empresa = mockEmpresa();
        List<TiposDeQuarto> quartosPermitidos = List.of(TiposDeQuarto.COMUM, TiposDeQuarto.DUPLO);
        politicaDeReservaService.definirPoliticaDeEmpresa(1L, quartosPermitidos);

        Empresa empresaBuscada = empresaService.buscarEmpresaPorId(1L).get();
        Assert.assertEquals(empresaBuscada, empresa);
    }


    @Test
    public void criarPoliticaParaFuncionario(){
        Empresa empresa = mockEmpresa();
        empresaService.criarFuncionario(1L, 1L);

        List<TiposDeQuarto> quartosPermitidos = List.of(TiposDeQuarto.COMUM);
        politicaDeReservaService.definirPoliticaDeEmpresa(1L, quartosPermitidos);

        politicaDeReservaService.definirPoliticaDeFuncionario(1L, quartosPermitidos);

        Funcionario funcionario = funcionarioRepository.buscarFuncionarioPorId(1L).get();
        Assert.assertEquals(funcionario.getPoliticasDeReservaDeQuartos(), new Politicas(quartosPermitidos));
    }

    @Test
    public void funcionarioUsarSuaPoliticaMesmoTendoPoliticaDeEmpresa(){
        Empresa empresa = mockEmpresa();
        empresaService.criarFuncionario(1L, 1L);

        List<TiposDeQuarto> quartosPermitidosFuncionario = List.of(TiposDeQuarto.DUPLO);
        List<TiposDeQuarto> quartosPermitidosEmpresa = List.of(TiposDeQuarto.COMUM);
        politicaDeReservaService.definirPoliticaDeEmpresa(1L, quartosPermitidosEmpresa);

        politicaDeReservaService.definirPoliticaDeFuncionario(1L, quartosPermitidosFuncionario);

       boolean podeReservar = politicaDeReservaService.podeReservar(1L, TiposDeQuarto.DUPLO);

       Assert.assertTrue(podeReservar);
    }

    @Test
    public void funcionarioUsarPoliticaEmpresa() {
        Empresa empresa = mockEmpresa();
        Funcionario funcionario = new Funcionario(1L, 1L);
        empresaService.criarFuncionario(funcionario.getIdEmpresa(), funcionario.getIdFuncionario());

        List<TiposDeQuarto> quartosPermitidos = List.of(TiposDeQuarto.COMUM);
        politicaDeReservaService.definirPoliticaDeEmpresa(1L, quartosPermitidos);

        boolean podeReservar = politicaDeReservaService.podeReservar(1L, TiposDeQuarto.COMUM);

        Assert.assertTrue(podeReservar);
        Assert.assertNull(funcionario.getPoliticasDeReservaDeQuartos());
        Assert.assertNotNull(empresa.getPoliticasDeReservaDeQuarto());
    }

    @Test
    public void funcionarioSemPoliticasDeEmpresaESemPolitcasDeFuncionarioPodeReservarQualqueQuarto(){
        Empresa empresa = mockEmpresa();
        Funcionario funcionario = new Funcionario(1L, 1L);
        empresaService.criarFuncionario(funcionario.getIdEmpresa(), funcionario.getIdFuncionario());

        Assert.assertNull(funcionario.getPoliticasDeReservaDeQuartos());
        Assert.assertNull(empresa.getPoliticasDeReservaDeQuarto());
        Assert.assertTrue(politicaDeReservaService.podeReservar(funcionario.getIdFuncionario(), TiposDeQuarto.DELUXE));
    }

    private Empresa mockEmpresa() {
        Empresa empresa = new Empresa(1L, "Company Test");
        empresaService.criarEmpresa(empresa);
        return empresa;
    }

}
