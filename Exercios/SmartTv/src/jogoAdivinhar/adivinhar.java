/*Jogo de Adivinhação

Descrição: Faça um jogo onde o programa escolhe um número aleatório, e o usuário tem que adivinhar. 
Dê dicas de "maior" ou "menor" após cada tentativa.
Conceitos Praticados: Geração de números aleatórios, loops, e manipulação de variáveis.  */
package jogoAdivinhar;
import java.util.Random;
import java.util.Scanner;

public class adivinhar {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int valorAleatorio = random.nextInt(11);

        System.out.println("=== JOGO DE ADIVINHAR ===\n");
        System.out.println("Você Tem 3 tentativas");
        System.out.println("O Valor do numero vai de 0 até 10 \n");

        for (int i = 0; i<3; i++){
            System.out.println("Tentativa: " + (i+1));
            int informado = scanner.nextInt();
            if (informado > valorAleatorio){
                System.out.println("O valor é MAIOR");
            }
            if(informado == valorAleatorio){
                System.out.println("Você acertou!!!");
                break;
            }if(i == 2){
                System.out.println("Acabou suas tentativas, O valor era " + valorAleatorio);
            }
        }
        scanner.close();
    }
}
