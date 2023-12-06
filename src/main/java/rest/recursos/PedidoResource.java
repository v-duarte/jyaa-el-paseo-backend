package rest.recursos;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
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
import persistencia.entidades.Pedido;
import persistencia.interfaces.PedidoDAO;

@Path("/Pedidos")
public class PedidoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private PedidoDAO dao;
	private String mensaje;
	
	
	/*CRUD*/
	@Operation
	@GET /*Listado de Pedidos*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> getPedido(){
		
		return dao.getLista();
	}
	
	@Operation
	@GET /*Pedido especifico (id)*/
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@PathParam("id") Long id){
		Pedido ped = dao.Obtener(id);
		if (ped != null){
			return Response
					.ok()
					.entity(ped)
					.build();
		} else {
			mensaje=("No se encontró el Pedido");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation
	@GET /*Pedido especifico por id de cliente*/
	@Path("/cliente/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("uid") Long uid){
		Pedido ped = dao.buscar(uid);
		if (ped != null){
			return Response
					.ok()
					.entity(ped)
					.build();
		} else {
			mensaje=("No se encontró el Pedido");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve las compras hechas por un cliente.")
	@GET /*Pedido especifico por id de cliente*/
	@Path("/compras/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listadoComprasHechas(@PathParam("uid") Long uid){
		List<Pedido> compras = dao.listadoCompras(uid);
		if (compras != null){
			return Response
					.ok()
					.entity(compras)
					.build();
		} else {
			mensaje=("No se encontraron compras hechas para el cliente ingresado");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation (description = "Devuelve los pedidos segun el id del punto de retiro.")
	@GET
	@Path("/punto/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPunto(@PathParam("pid") Long pid){
		List<Pedido> lista = dao.buscarPunto(pid);
		if (lista != null){
			return Response
					.ok()
					.entity(lista)
					.build();
		} else {
			mensaje=("No se encontaron pedidos para el punto de retiro");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	
	@Operation
	@POST /*Alta a un Pedido*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Pedido ped){
		//if(validar(ped)){ //podría validar si ya existe el pedido
		dao.Alta(ped);
		return Response
				.status(Response.Status.CREATED)
				.entity(ped)
				.build();
		/*} else {
			return Response.status(Response.Status.CONFLICT).build();
		}*/
	}
	
	@Operation
	@PUT /*Modificar un Pedido*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Pedido ped){
		Pedido aux = dao.Obtener(ped.getId());
		if (aux != null){
			dao.Modificar(ped);
			return Response.ok().entity(ped).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation
	@DELETE /*Borrar un Pedido*/
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@PathParam("id") Long id){
		Pedido aux = dao.Obtener(id);
		if (aux != null){
			dao.Baja_logica(aux);
			return Response.noContent().build();
		} else {
			mensaje = ("No existe el pedido con ese id");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
}
