package br.com.hotel.repository;

import br.com.hotel.entity.Empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpresaRepository {
    private List<Empresa> empresas;

    public EmpresaRepository() {
        this.empresas = new ArrayList<>();
    }

    public void criarEmpresa(Empresa empresa) {
        if (empresa != null) {
            this.empresas.add(empresa);
        }
    }

    public Optional<Empresa> buscarEmpresaPorId(Long idEmpresa) {
        return this.empresas
                .stream()
                .filter(empresa -> empresa.getIdEmpresa().equals(idEmpresa))
                .findFirst();
    }
}
