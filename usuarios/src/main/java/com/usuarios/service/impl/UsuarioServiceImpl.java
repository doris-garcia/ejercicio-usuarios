package com.usuarios.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarios.model.Usuario;
import com.usuarios.repository.IGenericRepository;
import com.usuarios.repository.IPhonesRepository;
import com.usuarios.repository.IUsuarioRepository;
import com.usuarios.service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService {

	@Autowired
	private IUsuarioRepository repository;
	
	@Autowired
	protected IGenericRepository<Usuario, Integer> getRepository() {
		return repository;
	}

	@Override
	public Usuario registrarTransaccional(String authorization, Usuario user) throws Exception {
		String[] token = authorization.split(" ");
		String jwt = token[1];
		user.setToken(jwt);
		user.setLast_login(LocalDateTime.now());
		user.setIsactive(true);
		user.getPhones().forEach(phones -> phones.setUsuario(user));
		repository.save(user);
		return user;
	}

	@Override
	public Usuario modificar(String authorization, Usuario user) throws Exception {
		Optional<Usuario> usuario = repository.findById(user.getIdUsuario());
		
		String[] token = authorization.split(" ");
		String jwt = token[1];
		user.setToken(jwt);
		user.setLast_login(LocalDateTime.now());
		user.getPhones().forEach(phones -> phones.setUsuario(user));
		repository.save(user);
		return user;
	}
}