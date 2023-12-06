package persistencia.entidades;
import java.util.List;
import java.time.LocalTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import utility.FormaEntrega;

@Entity
public class Pedido {
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="Pedido_ID")
	private Long id;
	private boolean enable=true;
	private boolean entregado=false;				 /*Admin : �Fue entregado? -- al marcarse como entregado, debe eliminarse el pedido del carrito del cliente*/
	private boolean confirmado=false;	 			 /*Cliente: �El cliente confirmo que lo quiere?*/
	private FormaEntrega forma_entrega;		 /*Eleccion entre domicilio o punto de entrega*/
	@JsonFormat(pattern="HH:mm")
	private LocalTime hora_inicio;  		 /*En caso de que se lo envien, Franja horaria en la que se encuentra en el lugar*/
	@JsonFormat(pattern="HH:mm")
	private LocalTime hora_fin;  			 /*En caso de que se lo envien, Franja horaria en la que se encuentra en el lugar*/
	private double preciototal;	 			 /*Precio total del pedido*/
	
	
	/*Relaciones*/
	@ManyToOne
	@JoinColumn(name="Cliente_ID")								
	private Usuario usuario;	 			 /*Cliente al que pertenece dicho pedido*/
	@ManyToOne
	@JoinColumn(name="Direccion_ID")
	private Direccion direccion;		 /*Direccion donde se entregara o retirara dicho pedido (CAMBIAR TIPO A DIRECCION)*/
	@ManyToOne
	@JoinColumn(name="Ronda_ID")
	private Ronda ronda;
	@OneToMany(mappedBy="pedido")
	@JsonIgnore								/*Anotacion para prevenir loops*/
	private List <ItemProducto> productos;	 /*Productos que componen el pedido*/
	
	
	
	/*Get&Set*/
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario cliente) {
		this.usuario = cliente;
	}
	public boolean isEntregado() {
		return entregado;
	}
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	public boolean isConfirmado() {
		return confirmado;
	}
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
	public FormaEntrega getForma_entrega() {
		return forma_entrega;
	}
	public void setForma_entrega(FormaEntrega forma_entrega) {
		this.forma_entrega = forma_entrega;
	}
	public LocalTime getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public LocalTime getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(LocalTime hora_fin) {
		this.hora_fin = hora_fin;
	}
	public List<ItemProducto> getProductos() {
		return productos;
	}
	public void setProductos(List<ItemProducto> productos) {
		this.productos = productos;
	}
	public double getPreciototal() {
		return preciototal;
	}
	public void setPreciototal(double d) {
		this.preciototal = d;
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
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public Ronda getRonda() {
		return ronda;
	}
	public void setRonda(Ronda ronda) {
		this.ronda = ronda;
	}
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", enable=" + enable + ", entregado=" + entregado + ", confirmado=" + confirmado
				+ ", forma_entrega=" + forma_entrega + ", franjahora=" + ", preciototal=" + preciototal
				+ ", cliente=" + usuario + ", direccion=" + direccion + ", ronda=" + ronda + ", productos="
				+ productos + "]";
	}
}
