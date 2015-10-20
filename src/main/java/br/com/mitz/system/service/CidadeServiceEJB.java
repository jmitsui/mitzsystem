package br.com.mitz.system.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.mitz.system.model.Cidade;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da entidade <code>Cidade</code>.
 * 
 * <p>Herda <code>AbstractPersistence</code> para resolver as operações básicas da persistência de <code>Cidade</code>.</p>
 * 
 * @see br.com.mitz.system.service.CidadeService
 */
@Stateless
public class CidadeServiceEJB extends AbstractPersistence<Cidade, Long> 
	implements CidadeService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public CidadeServiceEJB() {
		super(Cidade.class);
	}

	public List<Cidade> findByEstado(String estadoSigla) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<Cidade> rt = cq.from(Cidade.class);
		// cq.orderBy(getEntityManager().getCriteriaBuilder().desc(rt.get(field)));
		cq.select(getEntityManager().getCriteriaBuilder().equal(rt.get("sigla"), estadoSigla));
		Query q = getEntityManager().createQuery(cq);
		return q.getResultList();
	}	
}
