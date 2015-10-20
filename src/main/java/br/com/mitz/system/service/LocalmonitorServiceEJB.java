package br.com.mitz.system.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.mitz.system.model.Localmonitor;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da entidade <code>Localmonitor</code>.
 * 
 * <p>Herda <code>AbstractPersistence</code> para resolver as operações básicas da persistência de <code>Localmonitor</code>.</p>
 * 
 * @see br.com.mitz.system.service.LocalmonitorService
 */
@Stateless
public class LocalmonitorServiceEJB extends AbstractPersistence<Localmonitor, Long> 
	implements LocalmonitorService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public LocalmonitorServiceEJB() {
		super(Localmonitor.class);
	}
	
}
