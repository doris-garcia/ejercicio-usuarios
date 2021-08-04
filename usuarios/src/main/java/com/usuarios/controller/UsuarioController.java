package com.usuarios.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.usuarios.exception.ModeloNotFoundException;
import com.usuarios.model.Usuario;
import com.usuarios.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() throws Exception {
		List<Usuario> listUser = service.listar();
		return new ResponseEntity<List<Usuario>>(listUser, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Usuario user = service.listarPorId(id);
		if (user.getIdUsuario() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@GetMapping("/usuario/{id}")
	public EntityModel<Usuario> listarUsuarioPorId(@PathVariable("id") Integer id) throws Exception {
		Usuario user = service.listarPorId(id);
		if (user.getIdUsuario() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		EntityModel<Usuario> usuario = EntityModel.of(user);
		WebMvcLinkBuilder userLink = linkTo(methodOn(this.getClass()).listarPorId(id));
		usuario.add(userLink.withRel(("recurso-usuario")));
		return usuario;
	}

	@GetMapping("/usuario/pruebas/{id}")
	public EntityModel<Usuario> listarPruebas(@PathVariable("id") Integer id) throws Exception {
		Usuario user = service.listarPorId(id);
		if (user.getIdUsuario() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		EntityModel<Usuario> usuario = EntityModel.of(user);
		WebMvcLinkBuilder userLink = linkTo(methodOn(this.getClass()).listarPorId(id));
		usuario.add(userLink.withRel(("recurso-usuario")));
		return usuario;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> registrar(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@Valid @RequestBody Usuario request) throws Exception {
		Usuario usuario = service.registrarTransaccional(authorization, request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuario.getIdUsuario()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Usuario> modificar(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@Valid @RequestBody Usuario user) throws Exception {
		Usuario usuario = service.modificar(authorization, user);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(usuario.getIdUsuario()).toUri();
//		return ((HeadersBuilder<BodyBuilder>) ResponseEntity.ok(location)).build();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Usuario usuario = service.listarPorId(id);
		if (usuario.getIdUsuario() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
