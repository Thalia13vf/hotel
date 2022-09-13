package br.com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private Long idEmpresa;
    private Politicas politicasDeReservaDeQuarto;

    private List<Funcionario> funcionarios;

    private String nome;

    public Empresa(Long idEmpresa, Politicas politicasDeReservaDeQuarto) {
        this.idEmpresa = idEmpresa;
        this.politicasDeReservaDeQuarto = politicasDeReservaDeQuarto;
        this.funcionarios = new ArrayList<>();
    }

    public Empresa(Long idEmpresa, String nome) {
        this.idEmpresa = idEmpresa;
        this.funcionarios = new ArrayList<>();
        this.nome = nome;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Politicas getPoliticasDeReservaDeQuarto() {
        return politicasDeReservaDeQuarto;
    }

    public void setPoliticasDeReservaDeQuarto(Politicas politicasDeReservaDeQuarto) {
        this.politicasDeReservaDeQuarto = politicasDeReservaDeQuarto;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Empresa() {
        this.funcionarios = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "idEmpresa=" + idEmpresa +
                ", politicasDeReservaDeQuarto=" + politicasDeReservaDeQuarto +
                ", funcionarios=" + this.funcionarios +
                ", nome='" + nome + '\'' +
                '}';
    }
}
