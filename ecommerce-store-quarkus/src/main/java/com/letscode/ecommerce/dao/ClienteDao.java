package com.letscode.ecommerce.dao;

import com.letscode.ecommerce.models.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteDao implements PanacheRepositoryBase<Cliente, Long> {

    public Cliente findByEmail(String email) {
        return find("email", email).firstResult();
    }

}