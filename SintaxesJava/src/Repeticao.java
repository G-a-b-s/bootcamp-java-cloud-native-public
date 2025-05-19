import java.util.concurrent.ThreadLocalRandom;

public class Repeticao {
    public void sintaxeRepeticaoFor(){

        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        String nomes [] = {"Lucas", "João", "Maria", "Ana"};

        for(String nome : nomes){
            System.out.println(nome);
        }

    }
    public void sintaxeRepeticaoWhile(){
        double mesada = 50.0;
        while(mesada > 0){
            double valorDoce = valorAleatorio();
            if(valorDoce > mesada){
                System.out.println("Você não tem dinheiro suficiente para comprar o doce.");
                break;
            }
            mesada -= valorDoce;
            System.out.println("Você comprou um doce por " + valorDoce + " e agora tem " + mesada + " de mesada.");
        }
    }
    public void sintaxeRepeticaoDoWhile(){
        double mesada = 50.0;
        do{
            double valorDoce = valorAleatorio();
            if(valorDoce > mesada){
                System.out.println("Você não tem dinheiro suficiente para comprar o doce.");
                break;
            }
            mesada -= valorDoce;
            System.out.println("Você comprou um doce por " + valorDoce + " e agora tem " + mesada + " de mesada.");
        }
        while(mesada > 0);
    }
    public static double valorAleatorio(){
        double random = Math.random();

        //double randomValue = Math.random() * (max - min) + min;
        double randomValue = Math.random() * (8 - 1) + 1;

        //int randomInt = (int) (Math.random() * (max - min + 1)) + min;
        int randomInt = (int) (Math.random() * (8 - 1 + 1)) + 1;

        //return ThreadLocalRandom.current().nextDouble(2,8)
        return ThreadLocalRandom.current().nextDouble(2,8);
    }
}
