package persistencia.implementacion;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.Producto;
import persistencia.entidades.Rubro;
import persistencia.entidades.Usuario;
import persistencia.interfaces.ProductoDAO;

@Service
public class ProductoDAOImpl extends GenericDAOImpl<Producto> implements ProductoDAO {
	
	@Inject
	public ProductoDAOImpl(EM emu) {
		super(Producto.class, emu);
	}
	
	@Override
	public void Baja_logica(Producto entidad) {
		entidad.setEnable(false);
		super.Baja_logica(entidad);
	}

	@Override
	public List<Producto> listadoRubro(Long rid) {
		try {
			TypedQuery<Rubro> query = em.createQuery("SELECT r FROM Rubro r WHERE r.id = :rid", Rubro.class).setParameter("rid", rid);
			Rubro rubro = query.getSingleResult();
            TypedQuery<Producto> queryproductos = em.createQuery("SELECT p FROM Producto p WHERE p.rubro = :rubro", Producto.class).setParameter("rubro", rubro);

            List<Producto> productos = queryproductos.getResultList();
            return productos;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}
	
	@Override
	public List<Producto> listadoProductor(Long uid) {
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :uid", Usuario.class).setParameter("uid", uid);
			Usuario productor = query.getSingleResult();
            TypedQuery<Producto> queryproductos = em.createQuery("SELECT p FROM Producto p WHERE p.productor = :productor", Producto.class).setParameter("productor", productor);

            List<Producto> productos = queryproductos.getResultList();
            return productos;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}
}
