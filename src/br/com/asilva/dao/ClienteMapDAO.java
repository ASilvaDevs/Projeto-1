package br.com.asilva.dao;

import br.com.asilva.domain.Cliente;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ClienteMapDAO implements IClienteDAO {

    private Map<Long, Cliente> map;

    public ClienteMapDAO() {
        map = new TreeMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {

        if (map.containsKey(cliente.getCpf())) {
            return false; //
        }
        map.put(cliente.getCpf(), cliente);
        return true; //
    }

    @Override
    public void excluir(Long cpf) {
        map.remove(cpf);
    }

    @Override
    public void alterar(Cliente cliente) {

        if (map.containsKey(cliente.getCpf())) {
            map.put(cliente.getCpf(), cliente);
        }

    }

    @Override
    public Cliente consultar(Long cpf) {
        return map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return map.values();
    }
}
