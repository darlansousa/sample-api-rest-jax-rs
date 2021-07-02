package br.com.getcriativa.amgdevapi.pessoa.resource;

import java.util.List;

import br.com.getcriativa.amgdevapi.pessoa.entity.Pessoa;
import br.com.getcriativa.amgdevapi.pessoa.repo.PessoaRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pessoas")
public class PessoaResource {
	
	private PessoaRepository _repository = new PessoaRepository();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pessoa> get(){
		return _repository.GetAll();
	}
	
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa findById(@PathParam("id") Integer id) {
		return _repository.Get(id);
	}
	
	public Response post(Pessoa pessoa) {
		try {
			_repository.Add(pessoa);
			return Response.status(Response.Status.CREATED).entity(pessoa).build();
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(Pessoa pessoa) {
		
		try {
			_repository.Add(pessoa);
			return Response.status(Response.Status.CREATED).entity(pessoa).build();
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(@PathParam("id") Integer id, Pessoa pessoa) {
		Pessoa p = _repository.Get(id);
		
		if(p == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		try {
			pessoa.setIdentificator(id);
			_repository.Edit(pessoa);
			return Response.status(Response.Status.CREATED).entity(pessoa).build();
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {
		Pessoa p = _repository.Get(id);
		
		if(p == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		try {
			_repository.Delete(id);
			return Response.status(Response.Status.OK).build();
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	

}
