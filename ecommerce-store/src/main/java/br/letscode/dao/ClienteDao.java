package br.letscode.dao;

import br.letscode.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByIdOrEmail(long id, String email); //SELECT * FROM CLIENTE WHERE id = {id} OR email = {email
    Cliente findByEmail(String email);
}
