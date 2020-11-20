package com.usuarios.service;
import com.usuarios.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer>{

	Usuario registrarTransaccional(String authorization, Usuario user) throws Exception;
	Usuario modificar(String authorization, Usuario user) throws Exception;
	
}
