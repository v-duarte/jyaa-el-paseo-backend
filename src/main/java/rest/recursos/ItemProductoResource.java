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
import persistencia.entidades.ItemProducto;
import persistencia.interfaces.ItemProductoDAO;
import utility.FormaEntrega;

@Path("/Items")
public class ItemProductoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private ItemProductoDAO dao;
	private String mensaje;
	
	/*CRUD*/
	@Operation (description = "Devuelve un listado de items.")
	@GET /*Listado de Productos*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemProducto> getItems(){		
		return dao.getLista();
	}
	
	@Operation (description = "Devuelve un item segun su id.")
	@GET /*Usuario especifico (id)*/
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@Parameter @PathParam("id") Long id){
		ItemProducto item = dao.Obtener(id);
		if (item != null){
			return Response
					.ok()
					.entity(item)
					.build();
		} else {
			mensaje=("No se encontró el item");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve items segun el id de pedido.")
	@GET /*Pedido especifico por id de cliente*/
	@Path("/pedido/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("pid") Long pid){
		List<ItemProducto> lista = dao.buscar(pid);
		if (lista != null){
			return Response
					.ok()
					.entity(lista)
					.build();
		} else {
			mensaje=("No se encontaron items para el pedido");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve items pedidos segun el id de productor.")
	@GET /*Pedido especifico por id de cliente*/
	@Path("/productor/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listadoPorProductor(@PathParam("uid") Long uid){
		List<ItemProducto> lista = dao.listadoProductor(uid);
		if (lista != null){
			return Response
					.ok()
					.entity(lista)
					.build();
		} else {
			mensaje=("No se encontaron items para el productor");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve items pedidos segun metodo de entrega.")
	@GET /*Pedido especifico por id de cliente*/
	@Path("/entrega/{entrega}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listadoPorFormaDeEntrega(@PathParam("entrega") FormaEntrega forma_entrega){
		List<ItemProducto> lista = dao.listadoFormaEntrega(forma_entrega);
		if (lista != null){
			return Response
					.ok()
					.entity(lista)
					.build();
		} else {
			mensaje=("No se encontaron items para el metodo de entrega seleccionado");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Alta a un nuevo item.")
	@POST /*Alta a un Usuario*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(ItemProducto item){
		ItemProducto aux = dao.validar(item);
		if(aux == null){ //podría validar si ya existe el usuario
			dao.Alta(item);
			return Response
					.status(Response.Status.CREATED)
					.entity(item)
					.build();
		} else {
			aux.setEnable(true);
			editar(aux);
		}
		return Response.status(Response.Status.CREATED).build();
	}
	
	@Operation (description = "Recibe un item y lo actualiza, si no lo encuentra informa.")
	@PUT /*Modificar un Usuario*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(ItemProducto item){
		ItemProducto aux = dao.Obtener(item.getId());
		if (aux != null){
			dao.Modificar(item);
			return Response.ok().entity(item).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation (description = "Recibe el id del item a eliminar de la base de datos")
	@DELETE /*Borrar un usuario*/
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@Parameter @PathParam("id") Long id){
		ItemProducto aux = dao.Obtener(id);
		if (aux != null){
			dao.Baja_logica(aux);
			return Response.noContent().build();
		} else {
			mensaje = ("No existe el item con ese id");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
