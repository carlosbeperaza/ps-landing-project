package com.ps.landing.project.services;

import java.util.List;

import com.ps.landing.project.models.Usuario;

public interface UsuarioService {

	// Rest
	public List<Usuario> findAll();
	public Usuario save(Usuario usuario);
	public Usuario findById(Long id);
	public void delete(Long id);
	
	// Generic Methods
//	public Usuario findByEmailAndPassword(String email, String password);
//	public Usuario findByEmail(String email);
	public Usuario findByUsername(String username);

}
