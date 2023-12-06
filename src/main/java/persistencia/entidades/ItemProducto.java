package persistencia.entidades;
import javax.persistence.*;

@Entity
/*nombre de la tabla se genera solo*/
public class ItemProducto {
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="ItemProducto_ID")
	private Long id;
	private boolean enable=true;
	private double precio_total;
	private int cantidad;
	
	
	/*Relaciones*/
	@ManyToOne
	@JoinColumn(name="Pedido_ID")
	private Pedido pedido;
	@OneToOne
	@JoinColumn(name="Producto_ID")
	private Producto producto;
	

	/*Get&Set*/
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(double precio_total) {
		this.precio_total = precio_total;
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
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	@Override
	public String toString() {
		return "ItemProducto [id=" + id + ", enable=" + enable + ", precio_total=" + precio_total + ", cantidad="
				+ cantidad + ", pedido=" + pedido + ", producto=" + producto + "]";
	}
}
