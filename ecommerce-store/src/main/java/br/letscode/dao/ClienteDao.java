package br.letscode.dao;

import br.letscode.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long>{

}
