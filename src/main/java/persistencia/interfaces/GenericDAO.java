package persistencia.interfaces;
import java.util.List;

public interface GenericDAO<T> {
	void Alta(T entidad);
	void Baja_logica (T entidad);
	void Modificar(T entidad);
	T Obtener(Long id);
	List<T> getLista();
}
