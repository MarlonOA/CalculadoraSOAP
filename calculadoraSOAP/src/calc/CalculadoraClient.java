package calc;

import java.net.BindException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.ServerRuntimeException;
import java.rmi.server.ServerCloneException;

import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

public class CalculadoraClient {

	static CalculadoraServer calc;
	private static String valor1;
	private static String valor2;
	private static String operacao;
	private static float resultado;

	
	public static void main(String[] args) throws MalformedURLException, BindException {

		publicaServico();
		
		URL url = new URL("http://127.0.0.1:9876/calc?wsdl");
		QName qname = new QName("http://calc/","CalculadoraServerImplService");
		Service ws = Service.create(url, qname);
		calc = ws.getPort(CalculadoraServer.class);

		do {
			
			menuOpcoes();
			escolheOperacao(Integer.parseInt(operacao));

		} while (true);
		
	}
	
	public static void menuOpcoes(){
		operacao =  JOptionPane.showInputDialog(" --- Escolha a operaçao ---\n"
				+"1 - soma; \n"
				+"2 - subtracao; \n"
				+"3 - multiplicação; \n"
				+"4 - divisão; \n");
	}
	
	
	public static void publicaServico() throws BindException{
		try {
			Endpoint.publish("http://127.0.0.1:9876/calc", new CalculadoraServerImpl());
			JOptionPane.showMessageDialog(null, "Serviço publicado em: http://127.0.0.1:9876/calc");
		} catch (Exception e) {

				JOptionPane.showMessageDialog(null, "Serviço já em uso.");
				
		}
	}
	

	public static int escolheOperacao(int valor){
		switch (valor) {
		case 1:
			operacao();
			resultado = calc.soma(Integer.parseInt(valor1), Integer.parseInt(valor2));
			JOptionPane.showMessageDialog(null, "Resultado soma "+resultado);

			break;
		case 2:
			operacao();
			resultado = calc.subtracao(Integer.parseInt(valor1), Integer.parseInt(valor2));
			JOptionPane.showMessageDialog(null, "Resultado subtração "+resultado);

			break;
		case 3:
			operacao();
			resultado = calc.multiplicacao(Integer.parseInt(valor1), Integer.parseInt(valor2));
			JOptionPane.showMessageDialog(null, "Resultado multiplicação "+resultado);

			break;
		case 4:
			operacao();
			resultado = calc.divisao(Integer.parseInt(valor1), Integer.parseInt(valor2));
			JOptionPane.showMessageDialog(null, "Resultado divisão "+resultado);

			break;
		default:

			break;
		}
		return valor;
	}

	public static void operacao() {

		valor1 = JOptionPane.showInputDialog("Informe o primeiro valor");
		valor2 = JOptionPane.showInputDialog("Informe o segundo valor");

	}
}
