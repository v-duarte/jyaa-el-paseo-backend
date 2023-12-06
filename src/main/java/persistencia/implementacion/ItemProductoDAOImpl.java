package persistencia.implementacion;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.ItemProducto;
import persistencia.entidades.Pedido;
import persistencia.entidades.Usuario;
import persistencia.interfaces.ItemProductoDAO;
import utility.FormaEntrega;

@Service
@RequestScoped
public class ItemProductoDAOImpl extends GenericDAOImpl<ItemProducto> implements ItemProductoDAO {
	
	@Inject
	public ItemProductoDAOImpl(EM emu) {
		super(ItemProducto.class, emu);
	}
	
	@Override
	public void Baja_logica(ItemProducto entidad) {
		entidad.setEnable(false);
		entidad.setCantidad(1);
		super.Baja_logica(entidad);
	}

	@Override
	public List<ItemProducto> buscar(Long pid) {
		try {
			TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.id = :pid", Pedido.class).setParameter("pid", pid);
			Pedido pedido = query.getSingleResult();
            TypedQuery<ItemProducto> queryitems = em.createQuery("SELECT i FROM ItemProducto i WHERE i.pedido = :pedido AND i.enable = :enable", ItemProducto.class).setParameter("pedido", pedido).setParameter("enable", true);

            List<ItemProducto> items = queryitems.getResultList();
            return items;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}

	@Override
	public List<ItemProducto> listadoProductor(Long uid) {
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :uid", Usuario.class).setParameter("uid", uid);
			Usuario productor = query.getSingleResult();
            TypedQuery<ItemProducto> queryitems = em.createQuery("SELECT i FROM ItemProducto i WHERE i.producto.productor = :productor", ItemProducto.class).setParameter("productor", productor);

            List<ItemProducto> items = queryitems.getResultList();
            return items;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}
	
	@Override
	public List<ItemProducto> listadoFormaEntrega(FormaEntrega forma_entrega) {
		try {
            TypedQuery<ItemProducto> query = em.createQuery("SELECT i FROM ItemProducto i WHERE i.pedido.forma_entrega = :forma_entrega AND i.pedido.entregado = :entregado", ItemProducto.class).setParameter("forma_entrega", forma_entrega).setParameter("entregado", false);

            List<ItemProducto> items = query.getResultList();
            return items;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}

	@Override
	public ItemProducto validar(ItemProducto item) {
		try {
		TypedQuery<ItemProducto> query = em.createQuery("SELECT i FROM ItemProducto i WHERE i.producto.id = :pid AND i.pedido.confirmado = :confirmado", ItemProducto.class).setParameter("pid", item.getProducto().getId()).setParameter("confirmado", false);
		ItemProducto resultado = query.getSingleResult();
		return resultado;
		}
		catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}
}
