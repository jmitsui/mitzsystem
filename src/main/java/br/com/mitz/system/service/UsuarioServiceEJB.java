package br.com.mitz.system.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;

import com.mysql.jdbc.Security;

import br.com.mitz.system.model.Usuario;
import br.com.mitz.system.security.*;;

/**
 * Componente <code>EJB</code> que implementa as operações de negócio da
 * entidade <code>Usuario</code>.
 * 
 * <p>
 * Herda <code>AbstractPersistence</code> para resolver as operações básicas da
 * persistência de <code>Usuario</code>.
 * </p>
 * 
 * @see br.com.mitz.system.service.UsuarioService
 */
@Stateless
public class UsuarioServiceEJB extends AbstractPersistence<Usuario, Long> implements UsuarioService {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public UsuarioServiceEJB() {
		super(Usuario.class);
	}

	public List<Usuario> findUsuarioByLoginSenha(String login, String senha) {
		return (List<Usuario>) em.createNamedQuery("findUsuarioByLoginSenha")
	                                      .setParameter("login", login)
	                                      .setParameter("senha", senha)
	                                      .getResultList();
	  }
	
	public boolean authenticate(Usuario usuario) {
		/*
		br.com.mitz.system.security.Security encrypt = new br.com.mitz.system.security.Security();
		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		Root<Usuario> c = cq.from(Usuario.class);
		Query q = em.createQuery(cq);
		ParameterExpression<String> pLogin = em.getCriteriaBuilder().parameter(String.class);
		ParameterExpression<String> pSenha = em.getCriteriaBuilder().parameter(String.class);
		q.setParameter(pLogin, usuario.getLogin());
		q.setParameter(pSenha, encrypt.encryptString(usuario.getSenha()));
		cq.select(c);
		cq.where(
				em.getCriteriaBuilder().equal(c.get("login"), pLogin),
				em.getCriteriaBuilder().equal(c.get("senha"), pSenha));
		return ((Long) q.getSingleResult()).intValue() > 0;
		*/
		return true;
	}
}
