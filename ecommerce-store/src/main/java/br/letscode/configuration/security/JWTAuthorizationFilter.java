package br.letscode.configuration.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

//Classe para autorizacao (validar o JWT token)
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    //Indica que a classe eh de autorizacao (recebendo um AuthenticationManager)
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    //Filtro interno (a ser aplicada em todas as requisicoes, a nao ser as URLs marcadas como excessao)
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        //Se nao houver token, o filtro eh aplicado (nao autorizado, HTTP 403)
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        //Caso nulo, o token adicionado eh nulo e a pagina em questao nao sera acessada
        //Caso tenha um valor (JWT foi validado), adiciona ao context da request e continua o processamento (indo para
        //    a URL chamada em questao
        UsernamePasswordAuthenticationToken authenticationToken = this.getAuth(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);

    }
    //Get Auth verifica a autenticidade do Token, atraves de "traduzir" o email e roles que foram gerados para esse token
    // Caso nao seja possivel, retorna null
    private UsernamePasswordAuthenticationToken getAuth(String token) {

        final String email = JwtUtils.getEmail(token);
        final List<String> roles = JwtUtils.getRoles(token);

        if (Objects.nonNull(email)) {
            return new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(new SimpleGrantedAuthority(roles.get(0))));
        } else {
            return null;
        }

    }
}
