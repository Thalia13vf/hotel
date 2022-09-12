package br.com.hotel.entity;

import br.com.hotel.enums.TiposDeQuarto;

import java.util.Date;

public class Reserva {
    private Long IdHotel;
    private Long idFuncionario;
    private Date inicioReserva;
    private Date fimReserva;
    private Integer idQuarto;
    private TiposDeQuarto tiposDeQuarto;

    public Reserva() {
    }

    public Reserva(Long idHotel, Long idFuncionario, Date inicioReserva, Date fimReserva, Integer idQuarto, TiposDeQuarto tiposDeQuarto) {
        IdHotel = idHotel;
        this.idFuncionario = idFuncionario;
        this.inicioReserva = inicioReserva;
        this.fimReserva = fimReserva;
        this.idQuarto = idQuarto;
        this.tiposDeQuarto = tiposDeQuarto;
    }

    public Long getIdHotel() {
        return IdHotel;
    }

    public void setIdHotel(Long idHotel) {
        IdHotel = idHotel;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Date getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(Date inicioReserva) {
        this.inicioReserva = inicioReserva;
    }

    public Date getFimReserva() {
        return fimReserva;
    }

    public void setFimReserva(Date fimReserva) {
        this.fimReserva = fimReserva;
    }

    public Integer getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Integer idQuarto) {
        this.idQuarto = idQuarto;
    }

    public TiposDeQuarto getTiposDeQuarto() {
        return tiposDeQuarto;
    }

    public void setTiposDeQuarto(TiposDeQuarto tiposDeQuarto) {
        this.tiposDeQuarto = tiposDeQuarto;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "IdHotel=" + IdHotel +
                ", idFuncionario=" + idFuncionario +
                ", inicioReserva=" + inicioReserva +
                ", fimReserva=" + fimReserva +
                ", idQuarto=" + idQuarto +
                ", tiposDeQuarto=" + tiposDeQuarto +
                '}';
    }
}
