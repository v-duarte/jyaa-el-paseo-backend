package persistencia.implementacion;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.Rubro;
import persistencia.interfaces.RubroDAO;

@Service
@RequestScoped
public class RubroDAOImpl extends GenericDAOImpl<Rubro> implements RubroDAO  {
	
	@Inject
	public RubroDAOImpl(EM emu) {
		super(Rubro.class, emu);
	}
	
	@Override
	public void Baja_logica(Rubro entidad) {
		entidad.setEnable(false);
		super.Baja_logica(entidad);
	}
}
