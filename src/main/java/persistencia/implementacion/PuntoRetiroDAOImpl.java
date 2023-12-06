package persistencia.implementacion;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.PuntoRetiro;
import persistencia.interfaces.PuntoRetiroDAO;

@Service
@RequestScoped
public class PuntoRetiroDAOImpl extends GenericDAOImpl<PuntoRetiro> implements PuntoRetiroDAO  {
	
	@Inject
	public PuntoRetiroDAOImpl(EM emu) {
		super(PuntoRetiro.class, emu);
	}
	
	@Override
	public void Baja_logica(PuntoRetiro entidad) {
		entidad.setEnable(false);
		super.Baja_logica(entidad);
	}
}
