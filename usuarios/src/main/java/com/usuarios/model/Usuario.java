package com.usuarios.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Usuario Modelo")
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
//	@Column(name = "id_usuario", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@Column(name = "token", nullable = true)
	private String token;

	@NotNull
	@Schema(description = "Nombre del usuario")
	@Size(min = 1, message = "Este campo debe tener mínimo 1 caracter")
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@NotNull
	@Size(message = "Este campo debe tener mínimo 1 caracter")

	@Email
	@Column(name = "email", nullable = false, length = 55, unique = true)
	private String email;

	@NotNull
	@Size(message = "usuario de la persona")
	@Column(name = "username", nullable = false, length = 20, unique = true)
	private String username;

	@NotNull
//	@Pattern(regexp = "([0-9].{2})([a-z].{12, 15})(?=.*[A-Z].{1}).{6,15}")//regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$"
	@Size(min = 6, max = 15, message = "Este campo debe tener entre 6 a 15 caracteres")
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "created", updatable = false)
	@CreationTimestamp
	private LocalDateTime created;

	@Column(name = "modified")
	@UpdateTimestamp
	private LocalDateTime modified;

	@Column(name = "last_login")
	private LocalDateTime last_login;

	@Column(name = "isactive", nullable = false)
	private Boolean isactive;

	@OneToMany(mappedBy = "usuario", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Phones> phones;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getLast_login() {
		return last_login;
	}

	public void setLast_login(LocalDateTime last_login) {
		this.last_login = last_login;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public List<Phones> getPhones() {
		return phones;
	}

	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

}
