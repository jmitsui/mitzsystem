package br.com.mitz.system.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.mitz.system.model.Empresa;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da entidade <code>Empresa</code>.
 * 
 * <p>Herda <code>AbstractPersistence</code> para resolver as operações básicas da persistência de <code>Empresa</code>.</p>
 * 
 * @see br.com.mitz.system.service.EmpresaService
 */
@Stateless
public class EmpresaServiceEJB extends AbstractPersistence<Empresa, Long> 
	implements EmpresaService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public EmpresaServiceEJB() {
		super(Empresa.class);
	}
	
}
