package br.com.mitz.system.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.mitz.system.model.Usuario;
import br.com.mitz.system.service.UsuarioService;

/**
 * Componente responsável por integrar o front-end (páginas JSF) c/ camada de serviço (EJB), para resolver o cadastro de <code>Usuario</code>.
 * 
 * <p>Trata-se de um <code>Managed Bean</code>, ou seja, as instâncias dessa classe são controladas pelo <code>JSF</code>. 
 * Objetos de <code>UsuarioBean</code> são criados e gerenciados pelo <code>CDI</code>, no escopo de <code>Request<code>.</p>
 * 
 * <p>Esse componente atua com um papel parecido com o <code>Controller</code> de outros frameworks <code>MVC</code>, ele resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 */
@Named
@RequestScoped
public class UsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Referência para o componente EJB, injetado pelo container.
	 */
	@Inject
	private UsuarioService service;
	
	/**
	 * Referência para o funcionario utiliza na inclusão (nova) ou edição.
	 */
	@Inject
	private Usuario usuario;
	
	private Long idSelecionado;
	
	private List<Usuario> usuarios;
	
	public UsuarioBean() {
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		usuario = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Usuario> getUsuarios() {
		System.out.println("service: "+service);
		if (usuarios == null) {
			usuarios = service.findAll();
		}
		return usuarios;
	}
	
	public String salvar() {
		try {
			System.out.println("service: "+service);
			service.save(usuario);
		} catch(Exception ex) {
			System.out.println("Erro ao salvar usuario:" + ex.getMessage());
			//log.error("Erro ao salvar usuario.", ex);
			//addMessage(getMessageFromI18N("msg.erro.salvar.usuario"), ex.getMessage());
			return "";
		}
		//log.debug("Salvou usuario "+usuario.getId());
		return "listaUsuarios";
	}
	
	public String remover() {
		try {
			service.remove(usuario);
		} catch(Exception ex) {
			System.out.println("Erro ao remover usuario:" + ex.getMessage());
			//log.error("Erro ao remover usuario.", ex);
			//addMessage(getMessageFromI18N("msg.erro.remover.usuario"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu usuario "+usuario.getId());
		return "listaUsuarios";
	}
	
	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties <code>ResourceBundle</code>.
	 */
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}
	
	/**
	 * Adiciona um mensagem no contexto do Faces (<code>FacesContext</code>).
	 * @param summary
	 * @param detail
	 */
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}
	
}
