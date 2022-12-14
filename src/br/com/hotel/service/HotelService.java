package br.com.hotel.service;

import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.Quarto;
import br.com.hotel.repository.HotelRepository;
import br.com.hotel.repository.QuartoRepositoy;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HotelService {

    private final HotelRepository hotelRepository;
    private final QuartoRepositoy quartoRepositoy;

    public HotelService(HotelRepository hotelRepository, QuartoRepositoy quartoRepositoy) {
        this.hotelRepository = hotelRepository;
        this.quartoRepositoy = quartoRepositoy;
    }

    public void criarHotel(Long idHotel, String nomeHotel) {
        boolean existe = hotelRepository.jaExisteEsseHotel(idHotel);
        if(existe){
            throw new RuntimeException("Hotel já existe");
        }

        hotelRepository.adicionarHotel(new Hotel(idHotel, nomeHotel));
    }

    public void definirQuarto(Long idHotel, Integer numeroQuarto, String tipoQuarto) {
        Hotel hotel = buscarHotelPorId(idHotel);

        if (hotelRepository.existeEsseQuartoNesseHotel(idHotel, numeroQuarto)){
            quartoRepositoy.alterarQuarto(numeroQuarto, tipoQuarto, hotel);
        }
        quartoRepositoy.adicionarQuarto(new Quarto(numeroQuarto, tipoQuarto), hotel);
    }

    public Hotel buscarHotelPorId(Long idHotel) {
        if (hotelRepository.buscarPorId(idHotel).isPresent()) {
            return hotelRepository.buscarPorId(idHotel).get();
        }
        return null;
    }

    public List<Hotel> listarHoteis() {
        return hotelRepository.listarHoteis();
    }

    public Quarto reservarQuarto(Long idHotel, String tipoQuarto,Date inicioReserva, Date fimReserva) {

        Optional<Quarto> quarto = buscarQuarto(tipoQuarto, idHotel);
        if (quarto.isPresent() ) {
            if (quarto.get().isDisponivel()) {
                quarto.get().setInicioReservaQuarto(inicioReserva);
                quarto.get().setFimReservaQuarto(fimReserva);
                quarto.get().setDisponivel(false);
                return quarto.get();
            }
        }
        return null;
    }

    private Optional<Quarto> buscarQuarto(String tipoQuarto, Long idHotel) {
        Hotel hotel = buscarHotelPorId(idHotel);
        if (hotel != null){
            return hotel.getQuartos()
                    .stream()
                    .filter(quarto -> verificarDisponibilidade(quarto) && quarto.getTipoQuarto().equals(tipoQuarto))
                    .findFirst();
        }
        return Optional.empty();
    }

    private boolean verificarDisponibilidade(Quarto quarto) {
        if (quarto.getInicioReservaQuarto() == null && quarto.getFimReservaQuarto() == null) {
            quarto.setDisponivel(true);
        }
        return quarto.isDisponivel();
    }
}
