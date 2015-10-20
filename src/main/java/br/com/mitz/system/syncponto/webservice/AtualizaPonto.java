package br.com.mitz.system.syncponto.webservice;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import com.sendgrid.*;
import br.com.mitz.system.bean.RegistropontoBean;
import br.com.mitz.system.model.Funcionario;
import br.com.mitz.system.model.Registroponto;
import br.com.mitz.system.service.FuncionarioService;
import br.com.mitz.system.service.RegistropontoService;
import java.text.SimpleDateFormat;

@WebService(serviceName = "Syncponto/syncponto")
public class AtualizaPonto implements AtualizaPontoService {

	@Inject
	private RegistropontoService registropontoService;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private RegistropontoBean registropontoBean;

	@Override
	@WebMethod
	public void iniciar() {
		System.out.println("Iniciado");
	}

	@Override
	@WebMethod
	public Long getLastRegistropontoId() {
		// SyncpontoServiceEJB syncpontoEJB;
		Long resultado = null;
		resultado = registropontoBean.getLastId();
		return resultado;
	}

	@Override
	@WebMethod
	public double atualizar(@WebParam(name = "idRegistroponto") Long idRegistroponto,
			@WebParam(name = "idFuncionario") Long idFuncionario, @WebParam(name = "dataentrada") String dataentrada,
			@WebParam(name = "datasaida") String datasaida, @WebParam(name = "ocorrencia") String ocorrencia) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat shortDateFormat = new SimpleDateFormat("dd/MM HH:mm");
			boolean atualizado = false;

			Funcionario funcionario = funcionarioService.find(idFuncionario);

			Registroponto registroponto = registropontoService.find(idRegistroponto);
			if (registroponto == null || (registroponto.getDatasaida() == null && !datasaida.isEmpty())) {
				atualizado = true;
				registroponto = new Registroponto();
			}
			registroponto.setId(idRegistroponto);
			registroponto.setOcorrencia(ocorrencia);
			if (dataentrada.trim() != "" && !dataentrada.isEmpty() && !dataentrada.equals(null))
				registroponto.setDataentrada(simpleDateFormat.parse(dataentrada));
			if (datasaida.trim() != "" && !datasaida.isEmpty() && !datasaida.equals(null))
				registroponto.setDatasaida(simpleDateFormat.parse(datasaida));

			registroponto.setFuncionario(funcionario);
			registroponto = registropontoService.save(registroponto);

			if (atualizado) {
				System.out.println("SendGrid");
				SendGrid sendgrid = new SendGrid("jmitsuisendgrid", "send1978");

				SendGrid.Email email = new SendGrid.Email();
				String info = registroponto.getFuncionario().getNome() + " >> E: " +  (registroponto.getDataentrada() == null ? "?" : shortDateFormat.format(registroponto.getDataentrada()))
						+ "; S: " +  (registroponto.getDatasaida() == null ? "?" : shortDateFormat.format(registroponto.getDatasaida()) + ";");

				email.addTo("jmitsui@gmail.com");
				email.setFrom("ponto@mitz.com.br");
				email.setFromName("Ponto");
				email.setSubject(info);
				email.setHtml(info);
				SendGrid.Response response = sendgrid.send(email);
				System.out.println(response.getStatus() + ": " + response.getMessage());
			}

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}