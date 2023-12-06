package persistencia.entidades;
import javax.persistence.*;



@Entity
public class Producto {
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="Producto_ID")
	private Long id;
	private boolean enable=true;
	private String nombre;     		/*Nombre del producto*/
	private int precio_unitario; 	/*Precio por unidad del producto*/
	private int stock;           	/*Cantidad del producto disponibles*/
	private String descripcion;  	/*Descripcion del producto*/
	private int calificacion;    	/*Calificacion de los consumidores sobre el produto*/
	
	
	/*Relaciones*/
	@ManyToOne
	@JoinColumn(name="Productor_ID")
	private Usuario productor; 	/*Datos del productor que vende el producto*/
	@ManyToOne
	@JoinColumn(name="Rubro_ID")
	private Rubro rubro;         	/*Rubro al que pertenece el producto*/
	/*Se podria agregar relacion con item_producto
	  si quisiera saber cantidad de productos vendidos*/
	
	
	/*Get&Set*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(int precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Usuario getProductor() {
		return productor;
	}
	public void setProductor(Usuario productor) {
		this.productor = productor;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Rubro getRubro() {
		return rubro;
	}
	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
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
		return "Producto [id=" + id + ", enable=" + enable + ", nombre=" + nombre + ", precio_unitario="
				+ precio_unitario + ", stock=" + stock + ", descripcion=" + descripcion + ", calificacion="
				+ calificacion + ", productor=" + productor + ", rubro=" + rubro + "]";
	}
}
