package br.com.asilva.dao;

import br.com.asilva.domain.Cliente;

import java.util.Collection;
import java.util.stream.Collector;

public interface IClienteDAO {
    public Boolean cadastrar(Cliente cliente);

    public void excluir(Long cpf);

    public void alterar(Cliente cliente);

    public Cliente consultar(Long cpf);

    public Collection<Cliente> buscarTodos();
}
