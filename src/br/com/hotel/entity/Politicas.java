package br.com.hotel.entity;

import br.com.hotel.enums.TiposDeQuarto;

import java.util.List;
import java.util.Objects;

public class Politicas {
    private List<TiposDeQuarto> tiposDeQuartoPermitidos;

    public Politicas(List<TiposDeQuarto> tiposDeQuartoPermitidos) {
        this.tiposDeQuartoPermitidos = tiposDeQuartoPermitidos;
    }

    public boolean podeReservar(TiposDeQuarto tipoQuarto) {
        return tiposDeQuartoPermitidos.contains(tipoQuarto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Politicas politicas = (Politicas) o;
        return Objects.equals(tiposDeQuartoPermitidos, politicas.tiposDeQuartoPermitidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tiposDeQuartoPermitidos);
    }

    @Override
    public String toString() {
        return "Politicas{" +
                "tiposDeQuartoPermitidos=" + tiposDeQuartoPermitidos +
                '}';
    }
}
