package br.com.mitz.system.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.mitz.system.model.Estado;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da entidade <code>Estado</code>.
 * 
 * <p>Herda <code>AbstractPersistence</code> para resolver as operações básicas da persistência de <code>Estado</code>.</p>
 * 
 * @see br.com.mitz.system.service.EstadoService
 */
@Stateless
public class EstadoServiceEJB extends AbstractPersistence<Estado, Long> 
	implements EstadoService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public EstadoServiceEJB() {
		super(Estado.class);
	}
	
}
