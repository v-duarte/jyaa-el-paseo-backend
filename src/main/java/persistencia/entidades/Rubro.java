package persistencia.entidades;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
public class Rubro {
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="Rubro_ID")
	private Long id;
	private boolean enable=true;
	private String nombre;
	private String descripcion;
	
	
	/*Relaciones*/
	@JsonIgnore
	@OneToMany(mappedBy="rubro")
	private List<Producto> productos;


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


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Rubro [id=" + id + ", enable=" + enable + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", productos=" + productos + "]";
	}
}
