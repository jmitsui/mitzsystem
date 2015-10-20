package br.com.mitz.system.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.mitz.system.model.Funcionario;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da entidade <code>Funcionario</code>.
 * 
 * <p>Herda <code>AbstractPersistence</code> para resolver as operações básicas da persistência de <code>Funcionario</code>.</p>
 * 
 * @see br.com.mitz.system.service.FuncionarioService
 */
@Stateless
public class FuncionarioServiceEJB extends AbstractPersistence<Funcionario, Long> 
	implements FuncionarioService {

    @PersistenceContext(unitName = "appCDIUnit")
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public void setEntityManager(EntityManager entityManager){
		this.em = entityManager;
	}
	
	public FuncionarioServiceEJB() {
		super(Funcionario.class);
	}

}
