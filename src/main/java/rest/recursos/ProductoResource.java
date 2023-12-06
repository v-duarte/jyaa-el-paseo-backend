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
import persistencia.entidades.Producto;
import persistencia.interfaces.ProductoDAO;

@Path("/Productos")
public class ProductoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private ProductoDAO dao;
	private String mensaje;
	
	/*CRUD*/
	@Operation (description = "Devuelve un listado de productos.")
	@GET /*Listado de Productos*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos(){		
		return dao.getLista();
	}
	
	@Operation (description = "Devuelve un producto segun su id.")
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@Parameter @PathParam("id") Long id){
		Producto producto = dao.Obtener(id);
		if (producto != null){
			return Response
					.ok()
					.entity(producto)
					.build();
		} else {
			mensaje=("No se encontró el producto");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve una lista de productos segun el rubro.")
	@GET
	@Path("/rubro/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listadoPorRubro(@Parameter @PathParam("rid") Long rid){
		List<Producto> producto = dao.listadoRubro(rid);
		if (producto != null){
			return Response
					.ok()
					.entity(producto)
					.build();
		} else {
			mensaje=("No se encontraron productos para ese rubro");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve el listado de productos segun el productor.")
	@GET
	@Path("/productor/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listadoPorProductor(@PathParam("uid") Long uid){
		List<Producto> lista = dao.listadoProductor(uid);
		if (lista != null){
			return Response
					.ok()
					.entity(lista)
					.build();
		} else {
			mensaje=("No se encontaron productos para dicho productor");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Alta a un nuevo producto.")
	@POST /*Alta a un Usuario*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Producto producto){
		//if(validar(user)){ //podría validar si ya existe el usuario
		dao.Alta(producto);
		return Response.status(Response.Status.CREATED)
				.entity(producto)
				.build();
		/*} else {
			return Response.status(Response.Status.CONFLICT).build();
		}*/
	}
	
	@Operation (description = "Recibe un producto y lo actualiza, si no lo encuentra informa.")
	@PUT /*Modificar un Usuario*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Producto producto){
		Producto aux = dao.Obtener(producto.getId());
		if (aux != null){
			dao.Modificar(producto);
			return Response.ok().entity(producto).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation (description = "Recibe el id del producto a eliminar de la base de datos")
	@DELETE /*Borrar un usuario*/
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@Parameter @PathParam("id") Long id){
		Producto aux = dao.Obtener(id);
		if (aux != null){
			dao.Baja_logica(aux);
			return Response.noContent().build();
		} else {
			mensaje = ("No existe el producto con ese id");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
