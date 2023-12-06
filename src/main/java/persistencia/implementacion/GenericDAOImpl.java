package persistencia.implementacion;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import persistencia.interfaces.GenericDAO;


public abstract class GenericDAOImpl<T> implements GenericDAO<T> {
	private Class <T> clase;
	
	
	protected EM emu;
	
	protected EntityManager em;
	
	public GenericDAOImpl(Class <T> clase) {
		this.clase = clase;
//		this.emu = EM.getEM();
		em = emu.getEntityManager();
	}
	
	public GenericDAOImpl(Class <T> clase, EM emu) {
		this.clase = clase;
		this.emu = EM.getEM();
		em = emu.getEntityManager();
	}
	

	public void Alta(T entidad) {
		// TODO Auto-generated method stub
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		try {
			em.persist(entidad);
			etx.commit();
		} catch (Exception e) {
			etx.rollback();
		}
	}

	public void Baja_logica(T entidad) {
		// TODO Auto-generated method stub
		this.Modificar(entidad);
	}

	public void Modificar(T entidad) {
		// TODO Auto-generated method stub
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		try {
			em.merge(entidad);
			etx.commit();
		} catch (Exception e) {
			etx.rollback();
		}
	}

	public T Obtener(Long id) {
		// TODO Auto-generated method stub
		
			return em.find(clase, id);
	}
	

	
	public List<T> getLista() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<T> listado = (List<T>)(em.createQuery("from "+this.clase.getName())).getResultList();
		//em.close();
		return listado;
	}

   //protected abstract void setEM(EM emu) ;
}
