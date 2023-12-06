package persistencia.interfaces;
import org.jvnet.hk2.annotations.Contract;
import persistencia.entidades.Ronda;

@Contract
public interface RondaDAO extends GenericDAO<Ronda>{

	Ronda ObtenerActual();

}
