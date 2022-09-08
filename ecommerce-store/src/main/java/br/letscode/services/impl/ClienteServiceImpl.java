package br.letscode.services.impl;

import br.letscode.dao.ClienteDao;
import br.letscode.dto.ClienteDto;
import br.letscode.models.Cliente;
import br.letscode.models.PerfilEnum;
import br.letscode.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    private final PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Cliente> listarTodosClientes(){
        return clienteDao.findAll();
    }

    //Usando o DTO pq nosso caso (de mentirinha), precisamos de algum trabalho nele antes de chegar a camada de persistencia
    public Cliente novoCliente(ClienteDto clienteDto) {
        try {
            if (clienteDto.getId() == 0) {
                clienteDto.setSenha(passwordEncoder.encode(clienteDto.getSenha()));
            }
            Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getSobrenome(), clienteDto.getEmail(), clienteDto.getSexo(), clienteDto.getCpf(), clienteDto.getSenha(), PerfilEnum.CLIENTE);
            clienteDao.save(cliente);
            cliente.setSenha("");
            return cliente;
        }
        catch (Exception e){
            return null;
        }
    }

    public boolean atualizarCliente(Cliente cliente) {
        try {
            clienteDao.save(cliente);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean removerCliente(long id) {
        try {
            clienteDao.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}