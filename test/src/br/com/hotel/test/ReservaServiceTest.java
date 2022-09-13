package br.com.hotel.test;

import br.com.hotel.entity.*;
import br.com.hotel.enums.TiposDeQuarto;
import br.com.hotel.repository.EmpresaRepository;
import br.com.hotel.repository.FuncionarioRepository;
import br.com.hotel.repository.HotelRepository;
import br.com.hotel.repository.QuartoRepositoy;
import br.com.hotel.service.EmpresaService;
import br.com.hotel.service.HotelService;
import br.com.hotel.service.PoliticaDeReservaService;
import br.com.hotel.service.ReservaService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class ReservaServiceTest {

    EmpresaRepository empresaRepository = new EmpresaRepository();
    FuncionarioRepository funcionarioRepository = new FuncionarioRepository(empresaRepository);
    QuartoRepositoy quartoRepositoy = new QuartoRepositoy();
    HotelRepository hotelRepository = new HotelRepository();
    HotelService hotelService = new HotelService(hotelRepository, quartoRepositoy);
    EmpresaService empresaService = new EmpresaService(funcionarioRepository, empresaRepository);
    PoliticaDeReservaService politicaDeReservaService = new PoliticaDeReservaService(empresaService, funcionarioRepository);

    @InjectMocks
    private ReservaService reservaService = new ReservaService(politicaDeReservaService,hotelService, funcionarioRepository, quartoRepositoy);

    @Test
    public void reservarQuartoDentroPoliticaEmpresa() {
        hotelService.criarHotel(1L, "Test");
        hotelService.definirQuarto(1L, 1, TiposDeQuarto.COMUM.name());

        Empresa empresa = new Empresa(1L, "Company Test");
        empresaService.criarEmpresa(empresa);

        Funcionario funcionario = new Funcionario(1L, 1L);
        empresaService.criarFuncionario(funcionario.getIdEmpresa(), funcionario.getIdFuncionario());

        politicaDeReservaService.definirPoliticaDeEmpresa(1l, List.of(TiposDeQuarto.COMUM));

        Date dataCheckIn = Date.from(Instant.now());
        Date dataCheckOut = Date.from(Instant.now().plus(2, ChronoUnit.DAYS));

        Reserva reserva = reservaService.reservar(1L, 1L, TiposDeQuarto.COMUM, dataCheckIn, dataCheckOut);
        Assert.assertNotNull(reserva);
    }

    @Test
    public void naoDeveReservarPoisDataCheckoutInvalida(){
        hotelService.criarHotel(1L, "Test");
        hotelService.definirQuarto(1L, 1, TiposDeQuarto.COMUM.name());

        Empresa empresa = new Empresa(1L, "Company Test");
        empresaService.criarEmpresa(empresa);

        Funcionario funcionario = new Funcionario(1L, 1L);
        empresaService.criarFuncionario(funcionario.getIdEmpresa(), funcionario.getIdFuncionario());

        politicaDeReservaService.definirPoliticaDeEmpresa(1l, List.of(TiposDeQuarto.COMUM));

        Date dataCheckIn = Date.from(Instant.now());
        Date dataCheckOut = dataCheckIn;

        Reserva reserva = reservaService.reservar(1L, 1L, TiposDeQuarto.COMUM, dataCheckIn, dataCheckOut);
        Assert.assertNull(reserva);
    }

    @Test
    public void naoDeveReservarPoisHotelNaoExiste() {

        Empresa empresa = new Empresa(1L, "Company Test");
        empresaService.criarEmpresa(empresa);

        Funcionario funcionario = new Funcionario(1L, 1L);
        empresaService.criarFuncionario(funcionario.getIdEmpresa(), funcionario.getIdFuncionario());

        politicaDeReservaService.definirPoliticaDeEmpresa(1l, List.of(TiposDeQuarto.COMUM));

        Date dataCheckIn = Date.from(Instant.now());
        Date dataCheckOut = Date.from(Instant.now().plus(10, ChronoUnit.DAYS));

        Reserva reserva = reservaService.reservar(1L, 1L, TiposDeQuarto.COMUM, dataCheckIn, dataCheckOut);
        Assert.assertNull(reserva);
    }

    @Test
    public void reservarQuartoDentroPoliticaFuncionario() {
        hotelService.criarHotel(1L, "Test");
        hotelService.definirQuarto(1L, 1, TiposDeQuarto.COMUM.name());

        Empresa empresa = new Empresa(1L, "Company Test");
        empresaService.criarEmpresa(empresa);

        Funcionario funcionario = new Funcionario(1L, 1L);
        empresaService.criarFuncionario(funcionario.getIdEmpresa(), funcionario.getIdFuncionario());

        politicaDeReservaService.definirPoliticaDeFuncionario(1L, List.of(TiposDeQuarto.COMUM));

        Date dataCheckIn = Date.from(Instant.now());
        Date dataCheckOut = Date.from(Instant.now().plus(2, ChronoUnit.DAYS));

        Reserva reserva = reservaService.reservar(1L, 1L, TiposDeQuarto.COMUM, dataCheckIn, dataCheckOut);
        Assert.assertEquals(reserva.getIdFuncionario(), funcionario.getIdFuncionario());
    }
}
