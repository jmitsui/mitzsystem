package br.com.mitz.system.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.mitz.system.model.Localmonitor;
import br.com.mitz.system.service.LocalmonitorService;

/**
 * Componente responsável por integrar o front-end (páginas JSF) c/ camada de serviço (EJB), para resolver o cadastro de <code>Localmonitor</code>.
 * 
 * <p>Trata-se de um <code>Managed Bean</code>, ou seja, as instâncias dessa classe são controladas pelo <code>JSF</code>. 
 * Objetos de <code>LocalmonitorBean</code> são criados e gerenciados pelo <code>CDI</code>, no escopo de <code>Request<code>.</p>
 * 
 * <p>Esse componente atua com um papel parecido com o <code>Controller</code> de outros frameworks <code>MVC</code>, ele resolve o fluxo de navegação e liga os componentes visuais com os dados.</p>
 */
@Named
@RequestScoped
public class LocalmonitorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Referência para o componente EJB, injetado pelo container.
	 */
	@Inject
	private LocalmonitorService service;
	
	/**
	 * Referência para a Localmonitor utiliza na inclusão (nova) ou edição.
	 */
	@Inject
	private Localmonitor localmonitor;
	
	private Long idSelecionado;
	
	private List<Localmonitor> localmonitors;
	
	public LocalmonitorBean() {
	}
	
	public void setIdSelecionado(Long idSelecionado) {
		this.idSelecionado = idSelecionado;
	}
	
	public Long getIdSelecionado() {
		return idSelecionado;
	}
	
	public Localmonitor getLocalmonitor() {
		return localmonitor;
	}
	
	
	public void editar() {
		if (idSelecionado == null) {
			return;
		}
		localmonitor = service.find(idSelecionado);
		//log.debug("Pronto pra editar");
	}
	
	public List<Localmonitor> getLocalmonitors() {
		System.out.println("service: "+service);
		if (localmonitors == null) {
			localmonitors = service.findAll();
		}
		return localmonitors;
	}

	
	public String salvar() {
		try {
			System.out.println("service: "+service);
			service.save(localmonitor);
		} catch(Exception ex) {
			//log.error("Erro ao salvar localmonitor.", ex);
			addMessage(getMessageFromI18N("msg.erro.salvar.localmonitor"), ex.getMessage());
			return "";
		}
		//log.debug("Salvou localmonitor "+localmonitor.getId());
		return "listaLocalmonitors";
	}
	
	public String remover() {
		try {
			service.remove(localmonitor);
		} catch(Exception ex) {
			//log.error("Erro ao remover localmonitor.", ex);
			addMessage(getMessageFromI18N("msg.erro.remover.localmonitor"), ex.getMessage());
			return "";
		}
		//log.debug("Removeu localmonitor "+localmonitor.getId());
		return "listaLocalmonitors";
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
