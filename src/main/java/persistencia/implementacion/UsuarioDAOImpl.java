package persistencia.implementacion;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import persistencia.entidades.Usuario;
import persistencia.interfaces.UsuarioDAO;
import utility.TipoUsuario;

@Service
@RequestScoped
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO{
	
	@Inject
	public UsuarioDAOImpl(EM emu) {
		super(Usuario.class, emu);
		
	}
	
	@Override
	public void Baja_logica(Usuario entidad) {
		entidad.setEnable(false);
		super.Baja_logica(entidad);
	}
	
	public Usuario autenticarUsuario(String email) {
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);

            Usuario usuario = query.getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            // Usuario no encontrado
            //throw new NoResultException("Usuario no encontrado.");
        	return null;
        }
   }

	@Override
	public List<Usuario> getProductores() {
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.tipo_usuario = :tipo_usuario", Usuario.class).setParameter("tipo_usuario", TipoUsuario.Productor);
		List<Usuario> lista = query.getResultList();
		return lista;
	}
}
