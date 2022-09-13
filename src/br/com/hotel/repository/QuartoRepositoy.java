package br.com.hotel.repository;

import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.Quarto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuartoRepositoy {

    private List<Quarto> quartos;

    public QuartoRepositoy() {
       this.quartos = new ArrayList<>();
    }

    public Quarto adicionarQuarto(Quarto quarto, Hotel hotel) {
        hotel.adicionarQuarto(quarto);
        quarto.setIdHotel(hotel.getIdHotel());
        this.quartos.add(quarto);
        return quarto;
    }

    public Quarto alterarQuarto(Integer numeroQuarto, String tipoQuarto, Hotel hotel) {
        Quarto quartoAlterado = buscarPorId(numeroQuarto, hotel).orElse(new Quarto());
        quartoAlterado.setNumeroQuarto(numeroQuarto);
        quartoAlterado.setTipoQuarto(tipoQuarto);
        return quartoAlterado;
    }

    public Optional<Quarto> buscarPorId(Integer numeroQuarto, Hotel hotel) {
        return hotel.getQuartos()
                .stream()
                .filter(quarto -> quarto.getNumeroQuarto().equals(numeroQuarto))
                .findFirst();
    }
}
