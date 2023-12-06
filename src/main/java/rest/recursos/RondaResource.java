package rest.recursos;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import persistencia.entidades.Ronda;
import persistencia.interfaces.RondaDAO;

@Path("/Rondas")
public class RondaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private RondaDAO dao;
	private String mensaje;
	
	/*CRUD*/
	@Operation (description = "Devuelve un listado de rondas.")
	@GET /*Listado de Productos*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ronda> getRondas(){		
		return dao.getLista();
	}
	
	@Operation (description = "Devuelve una ronda segun su id.")
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@Parameter @PathParam("id") Long id){
		Ronda ronda = dao.Obtener(id);
		if (ronda != null){
			return Response
					.ok()
					.entity(ronda)
					.build();
		} else {
			mensaje=("No se encontro la ronda");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve la ronda actual, o null en caso contrario")
	@GET
	@Path("/actual")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(){
		Ronda actual = dao.ObtenerActual();
		return Response
				.ok()
				.entity(actual)
				.build();
	}
	
	@Operation (description = "Alta a un nueva ronda.")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Ronda ronda){
		//if(validar(user)){ //podría validar si ya existe el usuario
		dao.Alta(ronda);
		return Response.status(Response.Status.CREATED)
				.entity(ronda)
				.build();
		/*} else {
			return Response.status(Response.Status.CONFLICT).build();
		}*/
	}
	
	@Operation (description = "Recibe una ronda y la actualiza, si no la encuentra informa.")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Ronda ronda){
		Ronda aux = dao.Obtener(ronda.getId());
		if (aux != null){
			dao.Modificar(ronda);
			return Response.ok().entity(ronda).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation (description = "Recibe el id de la ronda a eliminar de la base de datos")
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@Parameter @PathParam("id") Long id){
		Ronda aux = dao.Obtener(id);
		if (aux != null){
			dao.Baja_logica(aux);
			return Response.noContent().build();
		} else {
			mensaje = ("No existe el rubro con ese id");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
