package com.cefet.ds_projeto.security;

public class UsuarioDetails implements UserDetails {
	private final Usuario usuario;
	
	public UsuarioDetails(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getNivelAcesso().name()));
	}
	
	@Override
	public String getPassword() {return usuario.getSenha();}
	
	@Override
	public String getUsername() {return usuario.getLogin();}
	
	@Override
	public boolean isAccountNonExpired() {return true;}
	
	@Override
	public boolean isAccountNonLocked() {return true;}
	
	@Override
	public boolean isCredentialsNonExpired() {return true;}
	
	@Override
	public boolean isEnabled() {return true;}
}