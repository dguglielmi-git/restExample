package dev.dag.service;

import dev.dag.model.Client;
import dev.dag.repository.ClientMemoryRepository;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clients")
public class ClientResource {

    private ClientMemoryRepository clientRepository = new ClientMemoryRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Client getById(@PathParam("id") Long id) {
        return clientRepository.getById(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Client insert(Client client) {
        return clientRepository.insert(client);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Client client) {
        if (!clientRepository.exists(client.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).entity(client.getId() + "Doesn't exists").build();
        }
        Client clie = clientRepository.update(client);
        return Response.ok().entity(clie).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        if (id == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalidad ID 0").build();
        }
        clientRepository.delete(id);
        return Response.ok().entity("Item has been deleted successfully.").build();
    }

}
