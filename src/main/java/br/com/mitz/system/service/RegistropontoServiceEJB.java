package br.com.mitz.system.service;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.com.mitz.system.model.Registroponto;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da
 * entidade <code>Registroponto</code>.
 * 
 * <p>
 * Herda <code>AbstractPersistence</code> para resolver as operações básicas da
 * persistência de <code>Registroponto</code>.
 * </p>
 * 
 * @see br.com.mitz.system.service.RegistropontoService
 */
@Stateful
public class RegistropontoServiceEJB extends AbstractPersistence<Registroponto, Long>implements RegistropontoService {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public RegistropontoServiceEJB() {
		super(Registroponto.class);
	}

}
