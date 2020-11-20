package com.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuarios.model.Usuario;

public interface UserTokenRepository extends JpaRepository<Usuario,Integer> {

	    Usuario findByUsername(String username);
}
