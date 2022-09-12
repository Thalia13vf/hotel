package br.com.hotel.entity;

import br.com.hotel.enums.TiposDeQuarto;

import java.util.List;

public class Politicas {
    private List<TiposDeQuarto> tiposDeQuartoPermitidos;

    public Politicas(List<TiposDeQuarto> tiposDeQuartoPermitidos) {
        this.tiposDeQuartoPermitidos = tiposDeQuartoPermitidos;
    }

    public boolean podeReservar(TiposDeQuarto tipoQuarto) {
        return tiposDeQuartoPermitidos.contains(tipoQuarto);
    }

}
