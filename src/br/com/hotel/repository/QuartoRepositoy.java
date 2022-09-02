package br.com.hotel.repository;

import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.Quarto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuartoRepositoy {

    public Quarto adicionarQuarto(Quarto quarto, Hotel hotel) {
        hotel.adicionarQuarto(quarto);
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
