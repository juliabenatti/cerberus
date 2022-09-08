package com.letscode.ecommerce.endpoints;

import com.letscode.ecommerce.dto.login.LoginDto;
import com.letscode.ecommerce.dto.login.TokenDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.security.JwtUtils;
import com.letscode.ecommerce.security.PasswordUtils;
import com.letscode.ecommerce.services.ClienteService;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.Set;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/login")
public class LoginResource {

    final ClienteService clienteService;

    public LoginResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(description = "Realizar login")
    @POST
    public Response salvar(@Valid LoginDto loginDto) {

        Cliente cliente = clienteService.findByEmail(loginDto.getEmail());

        if (Objects.isNull(cliente) || !cliente.getSenha().equals(PasswordUtils.encode(loginDto.getSenha()))) {
            return Response.status(HttpStatus.SC_FORBIDDEN).build();
        }

        String token = JwtUtils.generateToken(cliente.getEmail(), Set.of(cliente.getPerfil().name()));

        return Response.ok(new TokenDto(cliente.getEmail(), token)).build();
    }

}