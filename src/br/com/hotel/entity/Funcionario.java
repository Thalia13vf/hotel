package br.com.hotel.entity;

public class Funcionario {
    private Long idFuncionario;

    private Long idEmpresa;

    private Politicas politicasDeReservaDeQuartos;

    private String nome;
    public Funcionario() {
    }

    public Funcionario(Long idFuncionario, Long idEmpresa) {
        this.idFuncionario = idFuncionario;
        this.idEmpresa = idEmpresa;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Politicas getPoliticasDeReservaDeQuartos() {
        return politicasDeReservaDeQuartos;
    }

    public void setPoliticasDeReservaDeQuartos(Politicas politicasDeReservaDeQuartos) {
        this.politicasDeReservaDeQuartos = politicasDeReservaDeQuartos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario(Long idFuncionario,Long idEmpresa, Politicas politicasDeReservaDeQuartos, String nome) {
        this.idFuncionario = idFuncionario;
        this.idEmpresa = idEmpresa;
        this.politicasDeReservaDeQuartos = politicasDeReservaDeQuartos;
        this.nome = nome;
    }

}
