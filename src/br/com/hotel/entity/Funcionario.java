package br.com.hotel.entity;

public class Funcionario {
    private Long idFuncionario;

    private Empresa empresa;

    private Politicas politicasDeReservaDeQuartos;

    private String nome;
    public Funcionario() {
    }

    public Funcionario(Long idFuncionario, Empresa empresa) {
        this.idFuncionario = idFuncionario;
        this.empresa = empresa;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public Funcionario(Long idFuncionario,Empresa empresa, Politicas politicasDeReservaDeQuartos, String nome) {
        this.idFuncionario = idFuncionario;
        this.empresa = empresa;
        this.politicasDeReservaDeQuartos = politicasDeReservaDeQuartos;
        this.nome = nome;
    }

}
