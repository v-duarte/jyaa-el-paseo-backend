package inicializacion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistencia.entidades.*;
import persistencia.implementacion.*;
import persistencia.interfaces.*;

import utility.*;

/**
 * Servlet implementation class IniciarBD
 */
@WebServlet("/IniciarBD")
public class IniciarBD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO dao = new ProductoDAOImpl(EM.getEM());
	private UsuarioDAO udao = new UsuarioDAOImpl(EM.getEM());
	private PedidoDAO pdao = new PedidoDAOImpl(EM.getEM());
	private ItemProductoDAO idao = new ItemProductoDAOImpl(EM.getEM());
	private DireccionDAO ddao = new DireccionDAOImpl(EM.getEM());
	private PuntoRetiroDAO prdao = new PuntoRetiroDAOImpl(EM.getEM());
    /**
     * Default constructor. 
     */
    public IniciarBD() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Alta de un productor*/
		Usuario productor = new Usuario();
		productor.setNombre("Marcos");
		productor.setApellido("Lopez");
		productor.setTipo_usuario(TipoUsuario.Productor);
		productor.setDNI(37147852);
		productor.setEmail("Marcos@gmail.com");
		productor.setTelefono(221154679);
		productor.setNom_usuario("mplopez");
		productor.setClave("marcos");
		productor.setDescripcion("Vendo productos de campo");
		udao.Alta(productor);
		
		/*Direccion para el productor*/
		Direccion dir1 = new Direccion();
		dir1.setBarrio("La Plata");
		dir1.setCalle("56");
		dir1.setCalle_1("15");
		dir1.setCalle_2("16");
		dir1.setCiudad("La Plata");
		dir1.setCodigo_postal(1900);
		dir1.setNumero(451);
		dir1.setReferencia("Una direccion.");
		dir1.setUsuario(productor);
		ddao.Alta(dir1);
		/*Alta de 3 productos*/
		Producto producto1 = new Producto();
		producto1.setNombre("Bananas Ecuatorianas");
		producto1.setPrecio_unitario(100);
		producto1.setStock(30);
		producto1.setCalificacion(5);
		producto1.setProductor(productor);
		dao.Alta(producto1);
		
		Producto producto2 = new Producto();
		producto2.setNombre("Mandarinas");
		producto2.setPrecio_unitario(200);
		producto2.setStock(50);
		producto2.setCalificacion(5);
		producto2.setProductor(productor);
		dao.Alta(producto2);
		
		Producto producto3 = new Producto();
		producto3.setNombre("Manzanas");
		producto3.setPrecio_unitario(150);
		producto3.setStock(20);
		producto3.setCalificacion(4);
		producto3.setProductor(productor);
		dao.Alta(producto3);
		
		/*Alta de un administrador y dos clientes*/
		Usuario admin = new Usuario();
		admin.setNombre("Pepe");
		admin.setApellido("Perez");
		admin.setTipo_usuario(TipoUsuario.Administrador);
		admin.setDNI(38452789);
		admin.setEmail("Pepe@gmail.com");
		admin.setNom_usuario("pepeadmin");
		admin.setClave("pepeclave");
		admin.setTelefono(221345678);
		udao.Alta(admin);
		
		/*Direccion para el administrador*/
		Direccion dir2 = new Direccion();
		dir2.setBarrio("La Plata");
		dir2.setCalle("15");
		dir2.setCalle_1("42");
		dir2.setCalle_2("43");
		dir2.setCiudad("La Plata");
		dir2.setCodigo_postal(1900);
		dir2.setNumero(544);
		dir2.setReferencia("Una direccion.");
		dir2.setUsuario(admin);
		ddao.Alta(dir2);
		
		Usuario cliente = new Usuario();
		cliente.setNombre("Pedro");
		cliente.setApellido("Gomez");
		cliente.setTipo_usuario(TipoUsuario.Cliente);
		cliente.setDNI(35128963);
		cliente.setEmail("pgomez@gmail.com");
		cliente.setNom_usuario("gomez");
		cliente.setClave("clvegomez");
		cliente.setTelefono(221444555);
		udao.Alta(cliente);
		
		/*Direccion para el primer cliente*/
		Direccion dir3 = new Direccion();
		dir3.setBarrio("La Plata");
		dir3.setCalle("15");
		dir3.setCalle_1("42");
		dir3.setCalle_2("43");
		dir3.setCiudad("La Plata");
		dir3.setCodigo_postal(1900);
		dir3.setNumero(544);
		dir3.setReferencia("Una direccion.");
		dir3.setUsuario(cliente);
		ddao.Alta(dir3);
		
		Usuario cliente2 = new Usuario();
		cliente2.setNombre("Jose");
		cliente2.setApellido("Gonzalez");
		cliente2.setTipo_usuario(TipoUsuario.Cliente);
		cliente2.setDNI(35128963);
		cliente2.setEmail("jgonza@gmail.com");
		cliente2.setNom_usuario("gonzalez");
		cliente2.setClave("clvegonza");
		cliente2.setTelefono(221778896);
		udao.Alta(cliente2);
		
		/*Dos Direcciones para el segundo cliente*/
		Direccion dir4 = new Direccion();
		dir4.setBarrio("La Plata");
		dir4.setCalle("41");
		dir4.setCalle_1("12");
		dir4.setCiudad("La Plata");
		dir4.setCodigo_postal(1900);
		dir4.setNumero(835);
		dir4.setReferencia("Una direccion.");
		dir4.setUsuario(cliente2);
		ddao.Alta(dir4);
		
		Direccion dir5 = new Direccion();
		dir5.setBarrio("La Plata");
		dir5.setCalle("51");
		dir5.setCalle_1("9");
		dir3.setCalle_2("10");
		dir5.setCiudad("La Plata");
		dir5.setCodigo_postal(1900);
		dir5.setNumero(986);
		dir5.setReferencia("Una direccion.");
		dir5.setUsuario(cliente2);
		ddao.Alta(dir5);
		
		/*Punto de retiro*/
		PuntoRetiro punto = new PuntoRetiro();
		punto.setNombre("Central");
		punto.setTelefono(1173619930);
		prdao.Alta(punto);
		
		Direccion dir6 = new Direccion();
		dir6.setBarrio("La Plata");
		dir6.setCalle("7");
		dir6.setCalle_1("47");
		dir6.setCalle_2("48");
		dir6.setCiudad("La Plata");
		dir6.setCodigo_postal(1900);
		dir6.setNumero(781);
		dir6.setReferencia("Una direccion de punto de retiro.");
		dir6.setPuntoretiro(punto);
		ddao.Alta(dir6);
		
		//Actualizo el punto con la informacion de la direccion
		punto.setDireccion(dir6);
		prdao.Modificar(punto);
		
		Pedido pedido = new Pedido();
		pedido.setForma_entrega(FormaEntrega.Domicilio);
		pedido.setUsuario(cliente2);
		pdao.Alta(pedido);
		ItemProducto item = new ItemProducto();
		item.setProducto(producto1);
		item.setCantidad(1);
		item.setPrecio_total(producto1.getPrecio_unitario()*item.getCantidad());
		item.setPedido(pedido);
		idao.Alta(item);
		ItemProducto item2 = new ItemProducto();
		item2.setProducto(producto2);
		item2.setCantidad(2);
		item2.setPrecio_total(producto2.getPrecio_unitario()*item2.getCantidad());
		item2.setPedido(pedido);
		idao.Alta(item2);
		pedido.setPreciototal(item.getPrecio_total()+item2.getPrecio_total());
		pdao.Modificar(pedido);
		response.getWriter().append("Se cargo la base de datos con algunos datos. Ingrese a http://localhost:8080/Trabajo/swagger/ para probar la API REST. \n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
