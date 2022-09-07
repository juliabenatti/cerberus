package com.letscode.ecommerce.endpoints;

import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.services.ClienteService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cliente")
public class ClienteEndpoints {

    @Inject
    ClienteService clienteService;

    @Operation(description = "Salvar um cliente")
    @POST
    public Response salvar(@Valid Cliente cliente) {
        return Response.ok(clienteService.salvar(cliente)).build();
    }

    @Operation(description = "Atualizar um cliente")
    @PUT
    public Response atualizar(@Valid Cliente cliente) {
        return Response.ok(clienteService.atualizar(cliente)).build();
    }

    @Operation(description = "Retorna todos os clientes")
    @GET
    public Response listarTodos() {
        return Response.ok(clienteService.getAll()).build();
    }

    @Operation(description = "Retorna cliente pelo id")
    @GET
    @Path("/{id}")
    public Response listarPorId(@PathParam("id") Long id) {
        return Response.ok(clienteService.getById(id)).build();
    }

    @Operation(description = "Excluir um cliente")
    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {

        clienteService.excluir(id);
    }
}
