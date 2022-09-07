package com.letscode.ecommerce.services.impl;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.models.PerfilEnum;
import com.letscode.ecommerce.services.ClienteService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteDao clienteDao;

//    public ClienteServiceImpl(ClienteDao clienteRepository) {
//        this.clienteDao = clienteRepository;
//    }

    @Override
    public Cliente findByEmail(String email) {
        return clienteDao.findByEmail(email);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        cliente.setPerfil(PerfilEnum.CLIENTE);

        clienteDao.persist(cliente);

        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        cliente.setPerfil(PerfilEnum.CLIENTE);

        clienteDao.getEntityManager().merge(cliente);

        return cliente;
    }

    @Transactional
    public void excluir(long id) {

        clienteDao.deleteById(id);
    }

    @Transactional
    public Cliente getById(long id) {
        return clienteDao.findById(id);
    }

    @Transactional
    public List<Cliente> getAll() {
        return clienteDao.findAll().list();
    }
}
