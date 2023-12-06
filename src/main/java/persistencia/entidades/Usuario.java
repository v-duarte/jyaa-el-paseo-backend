package persistencia.entidades;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utility.TipoUsuario;

@Entity
public class Usuario{
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="Usuario_ID")
	private Long id;
	private TipoUsuario tipo_usuario;
	/*Atributos persona*/
	private String nombre;
	private String apellido;
	private int DNI;
	private String email; 
	private int telefono;
	/*Atributos de Usuario*/
	private String nom_usuario;
	private String clave;
	/*Atributos de Administrador-Cliente*/
	private boolean enable=true;
	/*Atributos Productor*/
	String descripcion;
	
	
	/*Relaciones*/
	
	/*Relaciones de Cliente*/
	
	@OneToMany(mappedBy="usuario")
	@JsonIgnore
	private List <Direccion> direcciones;	/*Todas las direcciones del cliente*/
	@OneToMany(mappedBy="usuario")
	@JsonIgnore						/*Anotacion para prevenir loops*/
	private List <Pedido> compras;  /*Todos los pedidos realizados por el cliente*/
	
	/*Relaciones de Productor*/
	@JsonIgnore
	@OneToMany(mappedBy="productor",cascade = CascadeType.ALL)
	private List<Producto>productos = new ArrayList<>();
	
	
	/*Get&Set*/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getNom_usuario() {
		return nom_usuario;
	}
	public void setNom_usuario(String nom_usuario) {
		this.nom_usuario = nom_usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Direccion> getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}
	public List<Pedido> getCompras() {
		return compras;
	}
	public void setCompras(List<Pedido> compras) {
		this.compras = compras;
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
	public TipoUsuario getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(TipoUsuario tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", tipo_usuario=" + tipo_usuario + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", DNI=" + DNI + ", email=" + email + ", telefono=" + telefono + ", enable=" + enable
				+ ", descripcion=" + descripcion + "]";
	}
}
