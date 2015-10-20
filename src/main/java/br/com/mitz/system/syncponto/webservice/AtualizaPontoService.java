package br.com.mitz.system.syncponto.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface AtualizaPontoService {
	@WebMethod
	double atualizar(@WebParam(name = "idRegistroponto") Long idRegistroponto,
			@WebParam(name = "idFuncionario") Long idFuncionario, @WebParam(name = "dataentrada") String dataentrada,
			@WebParam(name = "datasaida") String datasaida, @WebParam(name = "ocorrencia") String ocorrencia);

	void iniciar();

	Long getLastRegistropontoId();
}
