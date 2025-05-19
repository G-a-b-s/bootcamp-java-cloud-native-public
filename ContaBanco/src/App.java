import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        //'TO DO':
        //Conhecer e importar a classe Scanner
        //Exibir mensagens para o usuario
        //Obter pelo o Scanner os valores digitados no termial
        //Exibir mensagem de conta criada
        Scanner scanner = new Scanner(System.in);
        int numero =0;
        String agencia="";
        String nomeCliente ="";
        double saldo =0.0;

        System.out.println("Por favor, digite o número da Agência:");
        agencia = scanner.nextLine();

        System.out.println("Por favor, digite o número da Conta:");
        numero = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha deixada pelo nextInt()

        System.out.println("Por favor, digite o nome do Cliente:");
        nomeCliente = scanner.nextLine();

        System.out.println("Por favor, digite o saldo inicial:");
        saldo = scanner.nextDouble();

        // Exibir a mensagem final formatada
        String mensagem = "Olá " + nomeCliente
            + ", obrigado por criar uma conta em nosso banco,"
            +" sua agência é " + agencia
            + ", conta " + numero
            + " e seu saldo "+ saldo + " já está disponível para saque.";
        System.out.println(mensagem);

        // Fechar o scanner
        scanner.close();

    }
}
