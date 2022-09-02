package br.com.hotel.service;

import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.Quarto;
import br.com.hotel.repository.HotelRepository;
import br.com.hotel.repository.QuartoRepositoy;

import java.util.List;

public class HotelService {

    private final HotelRepository hotelRepository;
    private final QuartoRepositoy quartoRepositoy;

    public HotelService(HotelRepository hotelRepository, QuartoRepositoy quartoRepositoy) {
        this.hotelRepository = hotelRepository;
        this.quartoRepositoy = quartoRepositoy;
    }

    public void criarHotel(Long idHotel, String nomeHotel) {
        if(!hotelRepository.jaExisteEsseHotel(idHotel)){
            hotelRepository.adicionarHotel(new Hotel(idHotel, nomeHotel));
        }
    }

    public void definirQuarto(Long idHotel, Integer numeroQuarto, String tipoQuarto) {
        Hotel hotel = buscarHotelPorId(idHotel);

        if (hotelRepository.existeEsseQuartoNesseHotel(idHotel, numeroQuarto)){
            quartoRepositoy.alterarQuarto(numeroQuarto, tipoQuarto, hotel);
        }
        quartoRepositoy.adicionarQuarto(new Quarto(numeroQuarto, tipoQuarto), hotel);
    }

    public Hotel buscarHotelPorId(Long idHotel) {
        return hotelRepository.buscarPorId(idHotel)
                .get();
    }

    public List<Hotel> listarHoteis() {
        return hotelRepository.listarHoteis();
    }
}
