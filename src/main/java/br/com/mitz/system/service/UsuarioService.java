package br.com.mitz.system.service;

import java.util.List;
import javax.ejb.Local;
import br.com.mitz.system.model.Usuario;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Usuario</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 */
@Local
public interface UsuarioService {

	public Usuario save(Usuario usuario);
	
	public void remove(Usuario usuario);
	
	public Usuario find(Long id);
	
	public List<Usuario> findAll();
	
	public List<Usuario> findUsuarioByLoginSenha(String login, String senha);
}
