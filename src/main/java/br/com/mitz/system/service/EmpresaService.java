package br.com.mitz.system.service;

import java.util.List;
import javax.ejb.Local;
import br.com.mitz.system.model.Empresa;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Empresa</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 */
@Local
public interface EmpresaService {

	public Empresa save(Empresa empresa);
	
	public void remove(Empresa empresa);
	
	public Empresa find(Long id);
	
	public List<Empresa> findAll();

}
