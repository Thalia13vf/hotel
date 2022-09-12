package br.com.hotel.entity;

import java.util.Date;

public class Quarto {
    private Integer numeroQuarto;
    private String tipoQuarto;

    private boolean disponivel;

    private Date inicioReservaQuarto;

    private Date fimReservaQuarto;

    public Quarto(Integer numeroQuarto, String tipoQuarto) {
        this.numeroQuarto = numeroQuarto;
        this.tipoQuarto = tipoQuarto;
    }

    public Quarto() {
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public boolean isDisponivel() {
        if (this.inicioReservaQuarto == null && this.fimReservaQuarto == null) {
            this.disponivel = true;
        }
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getInicioReservaQuarto() {
        return inicioReservaQuarto;
    }

    public void setInicioReservaQuarto(Date inicioReservaQuarto) {
        this.inicioReservaQuarto = inicioReservaQuarto;
    }

    public Date getFimReservaQuarto() {
        return fimReservaQuarto;
    }

    public void setFimReservaQuarto(Date fimReservaQuarto) {
        this.fimReservaQuarto = fimReservaQuarto;
    }


    @Override
    public String toString() {
        return "Quarto{" +
                "numeroQuarto=" + numeroQuarto +
                ", tipoQuarto='" + tipoQuarto + '\'' +
                ", disponivel=" + disponivel +
                ", inicioReservaQuarto=" + inicioReservaQuarto +
                ", fimReservaQuarto=" + fimReservaQuarto +
                '}';
    }
}
