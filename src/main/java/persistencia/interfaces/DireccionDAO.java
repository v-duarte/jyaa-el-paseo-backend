package persistencia.interfaces;
import java.util.List;

import org.jvnet.hk2.annotations.Contract;
import persistencia.entidades.Direccion;

@Contract
public interface DireccionDAO extends GenericDAO<Direccion>{
	public List<Direccion> buscar(Long uid);

	public List<Direccion> getListaDireccionesPuntos();
}
