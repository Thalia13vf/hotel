package br.com.hotel.service;

import br.com.hotel.entity.Quarto;
import br.com.hotel.entity.Reserva;
import br.com.hotel.enums.TiposDeQuarto;
import br.com.hotel.repository.FuncionarioRepository;
import br.com.hotel.repository.QuartoRepositoy;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class ReservaService {
    private final PoliticaDeReservaService politicaDeReservaService;
    private final HotelService hotelService;
    private final FuncionarioRepository funcionarioRepository;
    private final QuartoRepositoy quartoRepositoy;

    public ReservaService(PoliticaDeReservaService politicaDeReservaService, HotelService hotelService, FuncionarioRepository funcionarioRepository, QuartoRepositoy quartoRepositoy) {
        this.politicaDeReservaService = politicaDeReservaService;
        this.hotelService = hotelService;
        this.funcionarioRepository = funcionarioRepository;
        this.quartoRepositoy = quartoRepositoy;
    }

    public Reserva reservar(Long idFuncionario, Long idHotel, TiposDeQuarto tipoDeQuarto, Date checkIn,
                            Date checkout){
        Reserva reserva = new Reserva();

        if (verificarReserva(idFuncionario, idHotel, tipoDeQuarto, checkIn, checkout)) {
            reserva.setInicioReserva(checkIn);
            reserva.setFimReserva(checkout);
            reserva.setIdFuncionario(idFuncionario);
            reserva.setIdHotel(idHotel);
            reserva.setTiposDeQuarto(tipoDeQuarto);
            System.out.println("Reserva confirmada com sucesso!" + reserva);
            return reserva;
        }
        System.out.println("Erro ao reservar!");
        return null;
    }

    private boolean verificarSePodeReservarDeAcordoComPoliticas(Long idFuncionario, TiposDeQuarto tiposDeQuarto) {
        return politicaDeReservaService.podeReservar(idFuncionario, tiposDeQuarto);
    }

    private Quarto quartoDisponivelDurantePeriodoDeReserva(Long idHotel, String tipoDeQuarto, Date checkIn, Date checkout) {
        return hotelService.reservarQuarto(idHotel, tipoDeQuarto, checkIn, checkout);
    }

    private boolean hotelExistir(Long idHotel) {
        return hotelService.buscarHotelPorId(idHotel) != null;
    }

    private boolean verificarDatasDeReserva(Date checkIn, Date checkout) {
        Instant umDiaDepois = checkIn.toInstant().plus(1, ChronoUnit.DAYS);
        return checkout.toInstant().isAfter(umDiaDepois);
    }

    private boolean verificarReserva(Long idHotel, Long idFuncionario, TiposDeQuarto tipoDeQuarto, Date checkIn, Date checkout) {
        boolean hotelExiste = hotelExistir(idHotel);
        boolean acordoComPoliticas = verificarSePodeReservarDeAcordoComPoliticas(idFuncionario, tipoDeQuarto);
        boolean data = verificarDatasDeReserva(checkIn, checkout);
        Quarto quarto = quartoDisponivelDurantePeriodoDeReserva(idHotel, tipoDeQuarto.name(), checkIn, checkout);
        boolean quartoDisponivel = quarto!= null;

        return hotelExiste && acordoComPoliticas && data && quartoDisponivel;
    }

}
