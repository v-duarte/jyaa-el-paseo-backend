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
import persistencia.entidades.PuntoRetiro;
import persistencia.interfaces.PuntoRetiroDAO;

@Path("/PuntosRetiro")
public class PuntoRetiroResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private PuntoRetiroDAO dao;
	private String mensaje;
	
	/*CRUD*/
	@Operation (description = "Devuelve un listado de puntos de retiro.")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PuntoRetiro> getPuntosRetiro(){		
		return dao.getLista();
	}
	
	@Operation (description = "Devuelve un punto de retiro segun su id.")
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@Parameter @PathParam("id") Long id){
		PuntoRetiro item = dao.Obtener(id);
		if (item != null){
			return Response
					.ok()
					.entity(item)
					.build();
		} else {
			mensaje=("No se encontró el punto de retiro");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Alta a un nuevo punto de retiro.")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(PuntoRetiro punto){
		//if(validar(user)){ //podría validar si ya existe el usuario
		dao.Alta(punto);
		return Response.status(Response.Status.CREATED)
				.entity(punto)
				.build();
		/*} else {
			return Response.status(Response.Status.CONFLICT).build();
		}*/
	}
	
	@Operation (description = "Recibe un punto de retiro y lo actualiza, si no lo encuentra informa.")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(PuntoRetiro item){
		PuntoRetiro aux = dao.Obtener(item.getId());
		if (aux != null){
			dao.Modificar(item);
			return Response.ok().entity(item).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation (description = "Recibe el id del punto de retiro a eliminar de la base de datos")
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@Parameter @PathParam("id") Long id){
		PuntoRetiro aux = dao.Obtener(id);
		if (aux != null){
			dao.Baja_logica(aux);
			return Response.noContent().build();
		} else {
			mensaje = ("No existe el punto de retiro con ese id");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
