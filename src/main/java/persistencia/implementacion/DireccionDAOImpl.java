package persistencia.implementacion;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.Direccion;
import persistencia.entidades.Usuario;
import persistencia.interfaces.DireccionDAO;

@Service
@RequestScoped
public class DireccionDAOImpl extends GenericDAOImpl<Direccion> implements DireccionDAO{
	
	@Inject
	public DireccionDAOImpl(EM emu) {
		super(Direccion.class, emu);
	}
	
	@Override
	public void Baja_logica(Direccion entidad) {
		entidad.setEnable(false);
		super.Baja_logica(entidad);
	}

	@Override
	public List<Direccion> buscar(Long uid) {
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :uid", Usuario.class).setParameter("uid", uid);
			Usuario usuario = query.getSingleResult();
            TypedQuery<Direccion> querydirecciones = em.createQuery("SELECT d FROM Direccion d WHERE d.usuario = :usuario", Direccion.class).setParameter("usuario", usuario);

            List<Direccion> direcciones = querydirecciones.getResultList();
            return direcciones;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
	}

	@Override
	public List<Direccion> getListaDireccionesPuntos() {
		TypedQuery<Direccion> query = em.createQuery("SELECT d FROM Direccion d WHERE d.usuario is null", Direccion.class);
        List<Direccion> direcciones = query.getResultList();
        return direcciones;
	}
}
