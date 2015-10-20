package br.com.mitz.system.service;

import java.util.List;
import javax.ejb.Local;
import br.com.mitz.system.model.Estado;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Estado</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 */
@Local
public interface EstadoService {

	public Estado save(Estado estado);
	
	public void remove(Estado estado);
	
	public Estado find(Long id);
	
	public List<Estado> findAll();

}
