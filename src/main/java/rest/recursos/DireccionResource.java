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
import persistencia.entidades.Direccion;
import persistencia.interfaces.DireccionDAO;

@Path("/Direcciones")
public class DireccionResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private DireccionDAO dao;
	private String mensaje;
	
	/*CRUD*/
	@Operation (description = "Devuelve un listado de direcciones.")
	@GET /*Listado de Productos*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Direccion> getDirecciones(){		
		return dao.getLista();
	}
	@Path("/puntos")
	@Operation (description = "Devuelve un listado de direcciones de puntos de retiro.")
	@GET /*Listado de Productos*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Direccion> listadoDireccionesPuntos(){		
		return dao.getListaDireccionesPuntos();
	}
	
	@Operation (description = "Devuelve una direccion segun su id.")
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@Parameter @PathParam("id") Long id){
		Direccion direccion = dao.Obtener(id);
		if (direccion != null){
			return Response
					.ok()
					.entity(direccion)
					.build();
		} else {
			mensaje=("No se encontró la direccion");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve las direcciones de un usuario segun su id.")
	@GET /*Pedido especifico por id de cliente*/
	@Path("/cliente/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("uid") Long uid){
		List<Direccion> ped = dao.buscar(uid);
		if (ped != null){
			return Response
					.ok()
					.entity(ped)
					.build();
		} else {
			mensaje=("No se encontraron direcciones para este usuario.");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Alta a una nueva direccion.")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Direccion direccion){
		//if(validar(user)){ //podría validar si ya existe el usuario
		dao.Alta(direccion);
		return Response.status(Response.Status.CREATED).build();
		/*} else {
			return Response.status(Response.Status.CONFLICT).build();
		}*/
	}
	
	@Operation (description = "Recibe una direccion y lo actualiza, si no lo encuentra informa.")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Direccion direccion){
		Direccion aux = dao.Obtener(direccion.getId());
		if (aux != null){
			dao.Modificar(direccion);
			return Response.ok().entity(direccion).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation (description = "Recibe el id de la direccion a eliminar de la base de datos")
	@DELETE
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@Parameter @PathParam("id") Long id){
		Direccion aux = dao.Obtener(id);
		if (aux != null){
			dao.Baja_logica(aux);
			return Response.noContent().build();
		} else {
			mensaje = ("No existe la direccion con ese id");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
