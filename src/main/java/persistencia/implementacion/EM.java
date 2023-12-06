package persistencia.implementacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

@Service
@RequestScoped
public class EM {
	private  EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");
	private  EntityManager em = emf.createEntityManager();
	private static EM instancia;
		
	public static EM getEM() {
		if (instancia == null)
			instancia = new EM();
		return instancia;
	}

	public EntityManager getEntityManager() {
		return em;
	}
	

}
