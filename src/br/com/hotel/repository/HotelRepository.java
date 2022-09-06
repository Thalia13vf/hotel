package br.com.hotel.repository;

import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.Quarto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HotelRepository {
    private List<Hotel> hoteis;

    public HotelRepository() {
        this.hoteis = new ArrayList<>();
    }

    public Hotel adicionarHotel(Hotel hotel) {
        this.hoteis.add(hotel);
        return hotel;
    }

    public Optional<Hotel> buscarPorId(Long idHotel) {
        return this.hoteis
                .stream()
                .filter(hotel -> hotel.getIdHotel().equals(idHotel))
                .findFirst();
    }

    public Hotel alterarHotel(Hotel hotel) {
        Hotel hotelAlterado = new Hotel();
        hotelAlterado.setQuartos(hotel.getQuartos());
        hotelAlterado.setNome(hotel.getNome());
        return hotelAlterado;
    }

    public boolean jaExisteEsseHotel(Long idHotel) {
        boolean jaExitse= buscarPorId(idHotel).isPresent();
        return jaExitse;
    }

    public boolean existeEsseQuartoNesseHotel(Long idHotel, Integer numeroQuarto) {
        if(!jaExisteEsseHotel(idHotel)) {
            throw new RuntimeException("Não foi possível criar o quarto! Pois esse hotel não existe.");
        }
        List<Quarto> quartos = buscarPorId(idHotel).get().getQuartos();
        return buscarQuarto(quartos, numeroQuarto).isPresent();
    }
    public Optional<Quarto> buscarQuarto(List<Quarto> quartos, Integer numeroQuarto) {
        if (Objects.nonNull(quartos)){
            return quartos
                    .stream()
                    .peek(quarto -> quarto.getNumeroQuarto().equals(numeroQuarto)).findFirst();
        }
        return Optional.empty();
    }
    public List<Hotel> listarHoteis() {
        return hoteis;
    }
}
