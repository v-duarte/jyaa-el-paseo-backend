package persistencia.interfaces;
import java.util.List;

import org.jvnet.hk2.annotations.Contract;
import persistencia.entidades.Producto;

@Contract
public interface ProductoDAO extends GenericDAO<Producto> {

	List<Producto> listadoRubro(Long rid);

	List<Producto> listadoProductor(Long pid);

}
