package persistencia.entidades;
import javax.persistence.*;

@Entity
public class PuntoRetiro {
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="PuntoRetiro_ID")
	private Long id;
	private boolean enable=true;
	private String nombre; 				/*Nombre de la persona que espera la entregar o del PE */
	private int telefono;      			/*Contacto de dicha persona*/
	
	
	/*Relaciones*/
	@OneToOne
	private Direccion direccion; 		/*Lugar donde retirar el pedido y a cargo de quien*/
	
	
	/*Get&Set*/
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "PuntoRetiro [id=" + id + ", enable=" + enable + ", nombre=" + nombre + ", telefono=" + telefono
				+ ", direccion=" + direccion  + "]";
	}	
}
