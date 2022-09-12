package br.com.hotel;

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

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class HotelTest {
    public static void main(String[] args) {
        HotelService hotelService = new HotelService(new HotelRepository(), new QuartoRepositoy());
        EmpresaRepository empresaRepository = new EmpresaRepository();
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository(empresaRepository);
        EmpresaService empresaService = new EmpresaService(funcionarioRepository, empresaRepository);
        PoliticaDeReservaService politicaDeReservaService = new PoliticaDeReservaService(empresaService, funcionarioRepository);
        QuartoRepositoy quartoRepositoy = new QuartoRepositoy();
        ReservaService reservaService = new ReservaService(politicaDeReservaService, hotelService, funcionarioRepository, quartoRepositoy);

        hotelService.criarHotel(1L, "hotel1");
        hotelService.criarHotel(2L, "hotel2");

        hotelService.definirQuarto(1L, 1, TiposDeQuarto.COMUM.name());
        hotelService.definirQuarto(1L, 2, TiposDeQuarto.COMUM.name());
        hotelService.definirQuarto(1L, 3, TiposDeQuarto.DELUXE.name());

        hotelService.definirQuarto(2L, 1, TiposDeQuarto.COMUM.name());
        hotelService.definirQuarto(2L, 5, TiposDeQuarto.DELUXE.name());


        Empresa empresa = new Empresa(1L, "Company one");
        empresaService.criarEmpresa(empresa);

        Funcionario funcionarioSemPolitica = new Funcionario(1L, empresa, null, "Maria");
        System.out.println("FUNCIONARIO CRIADO " + funcionarioSemPolitica);
        empresaService.criarFuncionario(1L, 1L);

        System.out.println("Empresa sem politicas " + empresa);


        System.out.println("Hotel" + hotelService.listarHoteis());

        //Reservar

        politicaDeReservaService.definirPoliticaDeEmpresa(1L, Arrays.asList(TiposDeQuarto.COMUM, TiposDeQuarto.DUPLO));
        politicaDeReservaService.definirPoliticaDeFuncionario(1L, Arrays.asList(TiposDeQuarto.DELUXE));

        System.out.println("Empresa com politicas " + empresa);


        Reserva reserva = reservaService.reservar(1L, 1L, TiposDeQuarto.DELUXE, Date.from(Instant.now()), java.util.Date.from(Instant.now().plus(2, ChronoUnit.DAYS)));

        System.out.println("Fim!");
    }
}
