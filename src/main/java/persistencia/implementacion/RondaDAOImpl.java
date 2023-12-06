package persistencia.implementacion;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.Ronda;
import persistencia.interfaces.RondaDAO;

@Service
@RequestScoped
public class RondaDAOImpl extends GenericDAOImpl<Ronda> implements RondaDAO  {
	
	@Inject
	public RondaDAOImpl(EM emu) {
		super(Ronda.class, emu);
	}
	
	@Override
	public void Baja_logica(Ronda entidad) {
		entidad.setEnable(false);
		super.Baja_logica(entidad);
	}

	@Override
	public Ronda ObtenerActual() {
		TypedQuery<Ronda> query = em.createQuery("SELECT r FROM Ronda r WHERE r.enable = : enable", Ronda.class).setParameter("enable", true);
		try {
			Ronda actual = query.getSingleResult();
			return actual;
		} catch (NoResultException e) {
		    return null;
		}
	}
}
