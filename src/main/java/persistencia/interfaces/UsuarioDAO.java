package persistencia.interfaces;
import persistencia.entidades.Usuario;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface UsuarioDAO extends GenericDAO<Usuario> {
	public Usuario autenticarUsuario(String email);

	public List<Usuario> getProductores();

}
