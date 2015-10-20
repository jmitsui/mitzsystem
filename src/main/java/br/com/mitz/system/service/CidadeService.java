package br.com.mitz.system.service;

import java.util.List;
import javax.ejb.Local;
import br.com.mitz.system.model.Cidade;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Estado</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 */
@Local
public interface CidadeService {

	public Cidade save(Cidade cidade);
	
	public void remove(Cidade cidade);
	
	public Cidade find(Long id);
	
	public List<Cidade> findAll();

}
