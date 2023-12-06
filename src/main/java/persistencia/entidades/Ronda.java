package persistencia.entidades;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ronda {
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="Ronda_ID")
	private Long id;
	private boolean enable=true;
	private LocalDateTime f_inicio;    			/*Fecha en la que Inicia la ronda*/
	private LocalDateTime f_fin;     			/*Fecha en la que cierra la ronda*/
	private LocalDateTime f_retiro_inicio; 		/*Rango horario en el que se van a estar entregando los pedidos*/ 
	private LocalDateTime f_retiro_fin;
	
	
	/*Relaciones*/
	@OneToMany(mappedBy="ronda")
	@JsonIgnore
	private List<Pedido> listadepedidos; 		/* Por productores o mod entrega.*/
	
	
	/*Get&Set*/
	public LocalDateTime getF_inicio() {
		return f_inicio;
	}
	public void setF_inicio(LocalDateTime f_inicio) {
		this.f_inicio = f_inicio;
	}
	public LocalDateTime getF_fin() {
		return f_fin;
	}
	public void setF_fin(LocalDateTime f_fin) {
		this.f_fin = f_fin;
	}
	public LocalDateTime getF_retiro_inicio() {
		return f_retiro_inicio;
	}
	public void setF_retiro_inicio(LocalDateTime f_retiro_inicio) {
		this.f_retiro_inicio = f_retiro_inicio;
	}
	public LocalDateTime getF_retiro_fin() {
		return f_retiro_fin;
	}
	public void setF_retiro_fin(LocalDateTime f_retiro_fin) {
		this.f_retiro_fin = f_retiro_fin;
	}
	public List<Pedido> getListadepedidos() {
		return listadepedidos;
	}
	public void setListadepedidos(List<Pedido> listadepedidos) {
		this.listadepedidos = listadepedidos;
	}
	public Long getId() {
		return id;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "Ronda [id=" + id + ", enable=" + enable + ", f_inicio=" + f_inicio + ", f_fin=" + f_fin
				+ ", f_retiro_inicio=" + f_retiro_inicio + ", f_retiro_fin=" + f_retiro_fin + ", listadepedidos="
				+ listadepedidos + "]";
	}
}
