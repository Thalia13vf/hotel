package br.com.hotel.test;

import br.com.hotel.entity.Empresa;
import br.com.hotel.entity.Funcionario;
import br.com.hotel.entity.Politicas;
import br.com.hotel.enums.TiposDeQuarto;
import br.com.hotel.repository.EmpresaRepository;
import br.com.hotel.repository.FuncionarioRepository;
import br.com.hotel.service.EmpresaService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class EmpresaServiceTest {

    EmpresaRepository empresaRepository = new EmpresaRepository();
    FuncionarioRepository funcionarioRepository = new FuncionarioRepository(empresaRepository);

    @Mock
    private EmpresaService empresaService = new EmpresaService(funcionarioRepository,empresaRepository);


    @Test
    public void deveCriarUmFuncionarioNessaEmpresaComSucesso() {
        Funcionario funcionario =
                new Funcionario(1L, 1L, new Politicas(List.of(TiposDeQuarto.COMUM)), "Maria");
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(1L);
        empresa.setNome("Company");
        empresa.setFuncionarios(funcionario);

        empresaService.criarEmpresa(empresa);
        empresaService.criarFuncionario(1L, 1L);

        Assert.assertTrue(empresaService.funcionarioJaExiste(1L));
    }

    @Test(expected = RuntimeException.class)
    public void deveLancarExceptionPoisFuncionarioJaExiste() {
        Funcionario funcionario =
                new Funcionario(1L, 1L, new Politicas(List.of(TiposDeQuarto.COMUM)), "Maria");
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(1L);
        empresa.setNome("Company");
        empresa.setFuncionarios(funcionario);

        empresaService.criarFuncionario(1L, 1L);
        empresaService.criarFuncionario(1L, 1L);
    }

    @Test
    public void deveExcluirFuncionario() {

        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(1L);
        empresa.setNome("Company");
        empresaService.criarEmpresa(empresa);
        empresaService.criarFuncionario(1L, 1L);

        empresaService.excluirFuncionarios(1L);

        Assert.assertFalse(empresaService.funcionarioJaExiste(1L));
    }

    @Test
    public void deveBuscarEmpresaPorId(){
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(1L);
        empresa.setNome("Company");

        empresaService.criarEmpresa(empresa);

        Assert.assertEquals(empresa, empresaService.buscarEmpresaPorId(1L).get());
    }
}
