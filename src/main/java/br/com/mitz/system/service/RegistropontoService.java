package br.com.mitz.system.service;

import java.util.List;
import javax.ejb.Local;
import br.com.mitz.system.model.Registroponto;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Registroponto</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 */
@Local
public interface RegistropontoService {

	public Registroponto save(Registroponto registroponto);
	
	public void remove(Registroponto registroponto);
	
	public Registroponto find(Long id);
	
	public List<Registroponto> findAll();

//	public Long getLastId();

	public String maxByField(String field);

}
