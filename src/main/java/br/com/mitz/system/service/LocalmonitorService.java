package br.com.mitz.system.service;

import java.util.List;
import javax.ejb.Local;
import br.com.mitz.system.model.Localmonitor;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Localmonitor</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 */
@Local
public interface LocalmonitorService {

	public Localmonitor save(Localmonitor localmonitor);
	
	public void remove(Localmonitor localmonitor);
	
	public Localmonitor find(Long id);
	
	public List<Localmonitor> findAll();

}
