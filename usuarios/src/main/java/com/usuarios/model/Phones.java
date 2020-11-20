package com.usuarios.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Phones Modelo")
@Entity
@Table(name = "phones")
public class Phones {

	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
//	@Column(name = "id_phones", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPhones;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_phones"))
	private Usuario usuario;

	@Schema(description = "Número de teléfono o celular")
	@Size(min = 7, max = 9, message = "Este campo debe tener entre 7 y 9 dígitos números")
	@Column(name = "number", nullable = false, length = 9)
	private String number;

	@Schema(description = "Código de la ciudad")
	@Size(min = 1, max = 6, message = "Este campo debe tener entre 1 y 6 dígitos números")
	@Column(name = "citycode", nullable = false, length = 6)
	private String citycode;

	@Schema(description = "Código del país")
	@Size(min = 1, max = 4, message = "Este campo debe tener entre 1 y 4 dígitos números")
	@Column(name = "contrycode", nullable = false, length = 4)
	private String contrycode;

	public Integer getIdPhones() {
		return idPhones;
	}

	public void setIdPhones(Integer idPhones) {
		this.idPhones = idPhones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}
}
