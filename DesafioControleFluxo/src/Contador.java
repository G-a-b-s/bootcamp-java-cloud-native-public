import java.util.Scanner;

public class Contador {
	public static void main(String[] args) {
		Scanner terminal = new Scanner(System.in);
		boolean continuar = true;
		do{
			try {
			System.out.println("Digite o primeiro parâmetro");
			int parametroUm = terminal.nextInt();
			System.out.println("Digite o segundo parâmetro");
			int parametroDois = terminal.nextInt();
			//chamando o método contendo a lógica de contagem
			contar(parametroUm, parametroDois);
			continuar = false;
			}catch (ParametrosInvalidosException exception) {
				//imprimir a mensagem: O segundo parâmetro deve ser maior que o primeiro
				System.out.println(exception.getMessage());
			}
		}while(continuar);
		terminal.close();

	}
	static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		//validar se parametroUm é MAIOR que parametroDois e lançar a exceção
		if (parametroUm > parametroDois) {
			throw new ParametrosInvalidosException("O segundo parâmetro deve ser maior que o primeiro");
		}
		//realizar o for para imprimir os números com base na variável contagem
		int contagem = parametroDois - parametroUm;
		System.out.println("Iniciando contagem...");
		for (int i = 0; i <= contagem; i++) {
			System.out.println(parametroUm + i); //imprimir os números entre os parâmetros
		}
		System.out.println("Contagem finalizada.");
	}

}
