package br.letscode.services;

import br.letscode.dto.ClienteDto;
import br.letscode.models.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarTodosClientes();

    Cliente novoCliente(ClienteDto cliente);

    boolean atualizarCliente(Cliente cliente);

    boolean removerCliente(long id);

}
