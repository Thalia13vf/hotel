package br.com.hotel.repository;

import java.util.List;

public interface GenericCrudRepository {
    Object buscarPorId(Long id);
    Boolean contem(Long id);
    List<Object> buscarTodas();
    Object alterar(Object object);
    Object deletarPorId(Long id);
}
