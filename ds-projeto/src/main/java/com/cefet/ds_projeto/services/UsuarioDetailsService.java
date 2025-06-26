package com.cefet.ds_projeto.services;

@Service
public class UsuarioDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioDetails loadUserByUsername(String login) throws UsernameNotFoundException{
		Usuario usuario = usuarioRepository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
		return new UsuarioDetails(usuario); 
	}
}