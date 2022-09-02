package br.com.hotel.entity;

public class Quarto {
    private Integer numeroQuarto;
    private String tipoQuarto;

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

/*    @Override
    public String toString() {
        return "\n" +"     Quarto{" +
                "   numeroQuarto:" + numeroQuarto +
                "   tipoQuarto:" + tipoQuarto + " " +
                "}" + "\n";
    }*/

    @Override
    public String toString() {
        return "Quarto{" +
                "numeroQuarto=" + numeroQuarto +
                ", tipoQuarto='" + tipoQuarto + '\'' +
                '}';
    }
}
