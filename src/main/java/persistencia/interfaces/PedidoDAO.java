package persistencia.interfaces;
import java.util.List;

import org.jvnet.hk2.annotations.Contract;
import persistencia.entidades.Pedido;

@Contract
public interface PedidoDAO extends GenericDAO<Pedido>{
	public Pedido buscar(Long uid);
	public List<Pedido> buscarPunto(Long pid);
	List<Pedido> listadoCompras(Long uid);
}
