package persistencia.entidades;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Direccion {
	/*Atributos*/
	@Id @GeneratedValue
	@Column(name="Direccion_ID")
	private Long id;
	private boolean enable=true;
	private String calle;    /*Calle o avenida*/
	private String calle_1;  /*�Entre qu� calles est�? (opcional)*/
	private String calle_2;
	private int numero;		 /*Numero de la casa o edificio*/
	private String n_dep;    /*Numero de departamento. ej : 2b...*/
	private String ciudad;
	private int codigo_postal;
	private String barrio;
	private String lugar; 	 /*�Es tu trabajo o tu casa?*/
	private double latitud;  /*Solo para puntosderetiro*/
	private double longitud; /*Solo para puntosderetiro*/
	private String referencia;  /*Informacion adicional, para localizar el lugar*/
	
	
	/*Relaciones*/
	@ManyToOne
	@JoinColumn(name="Usuario_ID")
	private Usuario usuario;          /*Puede ser null*/
	@OneToOne
	@JsonIgnore
	private PuntoRetiro puntoretiro;  /*es uno o el otro..*/
	
	
	/*Get&Set*/
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public int getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getCalle_1() {
		return calle_1;
	}
	public void setCalle_1(String calle_1) {
		this.calle_1 = calle_1;
	}
	public String getCalle_2() {
		return calle_2;
	}
	public void setCalle_2(String calle_2) {
		this.calle_2 = calle_2;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getN_dep() {
		return n_dep;
	}
	public void setN_dep(String n_dep) {
		this.n_dep = n_dep;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario cliente) {
		this.usuario = cliente;
	}
	public PuntoRetiro getPuntoretiro() {
		return puntoretiro;
	}
	public void setPuntoretiro(PuntoRetiro puntoretiro) {
		this.puntoretiro = puntoretiro;
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
		return "Direccion [id=" + id + ", enable=" + enable + ", calle=" + calle + ", calle_1=" + calle_1 + ", calle_2="
				+ calle_2 + ", numero=" + numero + ", n_dep=" + n_dep + ", piso_dep=" + ", ciudad=" + ciudad
				+ ", codigo_postal=" + codigo_postal + ", barrio=" + barrio + ", lugar=" + lugar + ", latitud="
				+ latitud + ", longitud=" + longitud + ", referencia=" + referencia + ", cliente=" + usuario
				+ ", puntoretiro=" + puntoretiro + "]";
	}	
}
