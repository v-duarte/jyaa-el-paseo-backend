package persistencia.interfaces;
import java.util.List;

import org.jvnet.hk2.annotations.Contract;
import persistencia.entidades.ItemProducto;
import utility.FormaEntrega;

@Contract
public interface ItemProductoDAO extends GenericDAO<ItemProducto> {

	List<ItemProducto> buscar(Long pid);

	List<ItemProducto> listadoProductor(Long uid);

	List<ItemProducto> listadoFormaEntrega(FormaEntrega entrega);

	ItemProducto validar(ItemProducto item);

}
