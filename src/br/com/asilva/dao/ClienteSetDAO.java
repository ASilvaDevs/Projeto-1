package br.com.asilva.dao;

import br.com.asilva.domain.Cliente;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> set;

    public ClienteSetDAO() {
        this.set = new HashSet<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        return set.add(cliente);
    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteParaExcluir = null;
        for (Cliente cliente : set) {
            if (cliente.getCpf().equals(cpf)) {
                clienteParaExcluir = cliente;
                break;
            }
        }
        if (clienteParaExcluir != null) {
            set.remove(clienteParaExcluir);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        for (Cliente c : set) {
            if (c.getCpf().equals(cliente.getCpf())) {
                c.setNome(cliente.getNome());
                c.setTel(cliente.getTel());
                c.setEnd(cliente.getEnd());
                // Atualize outros atributos conforme necessário
                break;
            }
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        for (Cliente cliente : set) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null; // Retorna null se o cliente não for encontrado
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return set;
    }
}
