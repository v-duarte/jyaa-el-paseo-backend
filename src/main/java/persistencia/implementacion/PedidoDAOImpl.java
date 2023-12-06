package persistencia.implementacion;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.Pedido;
import persistencia.entidades.PuntoRetiro;
import persistencia.entidades.Usuario;
import persistencia.interfaces.PedidoDAO;

@Service
@RequestScoped
public class PedidoDAOImpl extends GenericDAOImpl<Pedido> implements PedidoDAO {
	
	@Inject
	public PedidoDAOImpl(EM emu) {
		super(Pedido.class, emu);
	}
	
	@Override
	public void Baja_logica(Pedido entidad) {
		entidad.setEnable(false);
		super.Baja_logica(entidad);
	}

	@Override
	public Pedido buscar(Long uid) {
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :uid", Usuario.class).setParameter("uid", uid);
			Usuario usuario = query.getSingleResult();
            TypedQuery<Pedido> queryped = em.createQuery("SELECT p FROM Pedido p WHERE p.usuario = :usuario AND p.confirmado = :condicion", Pedido.class).setParameter("usuario", usuario).setParameter("condicion", false);

            Pedido pedido = queryped.getSingleResult();
            return pedido;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}
	
	@Override
	public List<Pedido> listadoCompras(Long uid) {
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :uid", Usuario.class).setParameter("uid", uid);
			Usuario usuario = query.getSingleResult();
            TypedQuery<Pedido> queryped = em.createQuery("SELECT p FROM Pedido p WHERE p.usuario = :usuario AND p.confirmado = :condicion", Pedido.class).setParameter("usuario", usuario).setParameter("condicion", true);

            List<Pedido> compras = queryped.getResultList();
            return compras;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}

	@Override
	public List<Pedido> buscarPunto(Long pid) {
		try {
			TypedQuery<PuntoRetiro> query = em.createQuery("SELECT p FROM PuntoRetiro p WHERE p.id = :pid", PuntoRetiro.class).setParameter("pid", pid);
			PuntoRetiro punto_retiro = query.getSingleResult();
            TypedQuery<Pedido> querypedidos = em.createQuery("SELECT p FROM Pedido p WHERE p.direccion = :direccion", Pedido.class).setParameter("punto_retiro", punto_retiro.getDireccion());

            List<Pedido> pedidos = querypedidos.getResultList();
            return pedidos;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}
}
