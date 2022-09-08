package br.letscode.configuration.security;

import br.letscode.dao.ClienteDao;
import br.letscode.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

//Implementacaoo da classe UserDetailsService do Spring Security framework, usada para retornar as informacoes
//de autenticacao e autorizacao. A interface soh tem um metodo (loadUserByUsername()), que nos iimplementamos para
//"alimentar" informacoes sobre oo usuario para o Spring security API.
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    ClienteDao clienteDao;

    //Implementacao da mesma, utilizando o DAO da aplicacao (credenciais salvas no banco).
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = clienteDao.findByEmail(email);

        if (Objects.isNull(cliente)) {
            throw new UsernameNotFoundException(email);
        }

        return new UserPrincipalDetails(cliente); //Retornando somente o que eh importante para a autenticacao.
    }

}
