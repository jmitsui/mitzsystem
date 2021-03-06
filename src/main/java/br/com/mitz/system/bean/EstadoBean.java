package br.com.mitz.system.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.mitz.system.model.Estado;
import br.com.mitz.system.service.EstadoService;

/**
 * Componente responsável por integrar o front-end (páginas JSF) c/ camada de serviço (EJB), para resolver o cadastro de <code>Estado</code>.
 * 
 * <p>Trata-se de um <code>Managed Bean</code>, ou seja, as instâncias dessa classe são controladas pelo <code>JSF</code>. 
 * Objetos de <code>EstadoBean</code> são criados e gerenciados pelo <code>CDI</code>, no escopo de <code>Request<code>.</p>
 * 
 * <p>Esse componente atua com um papel parecido com o <code>Controller</code> de outros frameworks <code>MVC</code>, ele resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 */
@Named
@RequestScoped
public class EstadoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Referência para o componente EJB, injetado pelo container.
	 */
	@Inject
	private EstadoService service;
	
	/**
	 * Referência para a Mercadoria utiliza na inclusão (nova) ou edição.
	 */
	@Inject
	private Estado estado;
	
	private Long idSelecionado;
	
	private List<Estado> estados;
	
	public EstadoBean() {
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		estado = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Estado> getEstados() {
		System.out.println("service: "+service);
		if (estados == null) {
			estados = service.findAll();
		}
		return estados;
	}

	
	public String salvar() {
		try {
			System.out.println("service: "+service);
			service.save(estado);
		} catch(Exception ex) {
			//log.error("Erro ao salvar estado.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.estado"), ex.getMessage());
			return "";
		}
		//log.debug("Salvou estado "+estado.getId());
		return "listaEstados";
	}
	
	public String remover() {
		try {
			service.remove(estado);
		} catch(Exception ex) {
			//log.error("Erro ao remover estado.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.mercadoria"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu estado "+estado.getId());
		return "listaEstados";
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
