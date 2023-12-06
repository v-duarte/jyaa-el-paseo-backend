package rest;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
//import org.glassfish.jersey.process.internal.RequestScoped;

import persistencia.implementacion.*;
import persistencia.interfaces.*;

public class Binder extends AbstractBinder {
@Override
	protected void configure() {
		bind(UsuarioDAOImpl.class).to(UsuarioDAO.class);
		bind(DireccionDAOImpl.class).to(DireccionDAO.class);	
		bind(ItemProductoDAOImpl.class).to(ItemProductoDAO.class);
		bind(PedidoDAOImpl.class).to(PedidoDAO.class);
		bind(ProductoDAOImpl.class).to(ProductoDAO.class);
		bind(PuntoRetiroDAOImpl.class).to(PuntoRetiroDAO.class);
		bind(RondaDAOImpl.class).to(RondaDAO.class);
		bind(RubroDAOImpl.class).to(RubroDAO.class);
		bind(EM.class).to(EM.class);
		
	}
}
