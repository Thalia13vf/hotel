package br.com.hotel.entity;

import java.util.Date;

public class Quarto {
    private Integer numeroQuarto;
    private String tipoQuarto;

    private boolean disponivel;

    private Date inicioReservaQuarto;

    private Date fimReservaQuarto;

    private Long idHotel;

    public Quarto(Integer numeroQuarto, String tipoQuarto) {
        this.numeroQuarto = numeroQuarto;
        this.tipoQuarto = tipoQuarto;
        this.disponivel = this.isDisponivel();
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

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public Quarto(Integer numeroQuarto, String tipoQuarto, boolean disponivel, Date inicioReservaQuarto, Date fimReservaQuarto, Long idHotel) {
        this.numeroQuarto = numeroQuarto;
        this.tipoQuarto = tipoQuarto;
        this.disponivel = disponivel;
        this.inicioReservaQuarto = inicioReservaQuarto;
        this.fimReservaQuarto = fimReservaQuarto;
        this.idHotel = idHotel;
    }
}
