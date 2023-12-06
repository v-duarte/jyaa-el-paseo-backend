package rest.recursos;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
import persistencia.interfaces.UsuarioDAO;
import utility.UserDTo;
import persistencia.entidades.Usuario;

@Path("/Usuarios")
public class UsuarioResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/*variables*/
	@Inject private UsuarioDAO dao;
	private String mensaje;
	
	
	/*CRUD*/
	@Operation (description = "Devuelve un listado de usuarios.")
	@GET /*Listado de Usuarios*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios(){
		
		return dao.getLista();
	}
	@Path("/productores")
	@Operation (description = "Devuelve un listado de usuarios.")
	@GET /*Listado de Usuarios*/
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listadoProductores(){
		
		return dao.getProductores();
	}
	
	@Operation (description = "Devuelve un usuario segun su id.")
	@GET /*Usuario especifico (id)*/
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response encontrar(@Parameter @PathParam("id") Long id){
		Usuario user = dao.Obtener(id);
		if (user != null){
			return Response
					.ok()
					.entity(user)
					.build();
		} else {
			mensaje=("No se encontró el usuario");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}
	//@Operation (description = "Buscar un usuario por mail y contraseña")	
	@POST /*Alta a un Usuario*/
	//@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@RequestBody UserDTo userDto ){
		Usuario user =dao.autenticarUsuario(userDto.getEmail());
		if (user != null){
			if (user.getClave().equals(userDto.getClave())) {
				return Response
						.ok()
						.entity(user)
						.build();
			}
			else {
				mensaje=("Clave incorrecta.");
				return Response
						.status(Response.Status.UNAUTHORIZED)
						.entity(mensaje)
						.build();
			}
		} else {
			mensaje=("No se encontró el usuario");
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}	
	}
		
	@Operation (description = "Alta a un nuevo usuario.")
	@POST /*Alta a un Usuario*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Usuario user){
		//if(validar(user)){ //podría validar si ya existe el usuario
		dao.Alta(user);
		return Response
				.status(Response.Status.CREATED)
				.entity(user)
				.build();
		/*} else {
			return Response.status(Response.Status.CONFLICT).build();
		}*/
	}
	
	@Operation (description = "Recive un usuario y lo actualiza, si no lo encuentra informa.")
	@PUT /*Modificar un Usuario*/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editar(Usuario user){
		Usuario aux = dao.Obtener(user.getId());
		if (aux != null){
			dao.Modificar(user);
			return Response.ok().entity(user).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("[]").build();
		}
	}
	
	@Operation (description = "Recive el id del usuario a eliminar de la base de datos")
	@DELETE /*Borrar un usuario*/
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response borrar(@Parameter @PathParam("id") Long id){
		Usuario aux = dao.Obtener(id);
		if (aux != null){
			dao.Baja_logica(aux);
			return Response.noContent().build();
		} else {
			mensaje = ("No existe el usuario con ese id");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(mensaje)
					.build();
		}
	}


}
