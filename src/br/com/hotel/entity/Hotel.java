package br.com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private Long idHotel;
    private String nome;
    private List<Quarto> quartos;

    public Hotel(Long idHotel, String nome) {
        this.idHotel = idHotel;
        this.nome = nome;
        this.quartos = new ArrayList<>();
    }

    public Hotel(){

    }
    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public void setQuartos(List<Quarto> quartos) {
        this.quartos = quartos;
    }

    public Quarto adicionarQuarto(Quarto quarto) {
        this.quartos.add(quarto);
        return quarto;
    }


    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", nome='" + nome + '\'' +
                ", quartos=" + quartos +
                '}';
    }
}
