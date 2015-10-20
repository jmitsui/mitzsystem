package br.com.mitz.system.service;

import java.util.List;
import javax.ejb.Local;
import br.com.mitz.system.model.Funcionario;

/**
 * Determina a interface de negócio para estipular o serviço da entidade <code>Funcionario</code>.
 * 
 * <p>Indica o uso da interface <code>Local</code> para o serviço <code>EJB</code>.
 */
@Local
public interface FuncionarioService {

	public Funcionario save(Funcionario funcionario);
	
	public void remove(Funcionario funcionario);
	
	public Funcionario find(Long id);
	
	public List<Funcionario> findAll();
}
