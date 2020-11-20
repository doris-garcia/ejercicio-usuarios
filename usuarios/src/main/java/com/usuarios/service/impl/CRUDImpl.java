package com.usuarios.service.impl;

import java.util.List;

import com.usuarios.repository.IGenericRepository;
import com.usuarios.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepository<T, ID> getRepository();

	@Override
	public T registrar(T t) throws Exception {
		return getRepository().save(t);
	}

	@Override
	public T modificar(T t) throws Exception {
		return getRepository().save(t);
	}

	@Override
	public List<T> listar() throws Exception {
		return getRepository().findAll();
	}

	@Override
	public T listarPorId(ID id) throws Exception {
		return getRepository().findById(id).orElse(null);
	}

	@Override
	public void eliminar(ID id) throws Exception {
		getRepository().deleteById(id);
	}

}
