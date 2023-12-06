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
import persistencia.entidades.Rubro;
import persistencia.interfaces.RubroDAO;

@Path("/Rubros")
public class RubroResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private RubroDAO dao;
	private String mensaje;
	
	/*CRUD*/
	@Operation (description = "Devuelve un listado de rubros.")
	@GET /*Listado de Productos*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rubro> getRubros(){		
		return dao.getLista();
	}
	
	@Operation (description = "Devuelve un rubro segun su id.")
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@Parameter @PathParam("id") Long id){
		Rubro rubro = dao.Obtener(id);
		if (rubro != null){
			return Response
					.ok()
					.entity(rubro)
					.build();
		} else {
			mensaje=("No se encontró el rubro");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Alta a un nuevo rubro.")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Rubro rubro){
		//if(validar(user)){ //podría validar si ya existe el usuario
		dao.Alta(rubro);
		return Response.status(Response.Status.CREATED)
				.entity(rubro)
				.build();
		/*} else {
			return Response.status(Response.Status.CONFLICT).build();
		}*/
	}
	
	@Operation (description = "Recibe un rubro y lo actualiza, si no lo encuentra informa.")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Rubro rubro){
		Rubro aux = dao.Obtener(rubro.getId());
		if (aux != null){
			dao.Modificar(rubro);
			return Response.ok().entity(rubro).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation (description = "Recibe el id del rubro a eliminar de la base de datos")
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@Parameter @PathParam("id") Long id){
		Rubro aux = dao.Obtener(id);
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
