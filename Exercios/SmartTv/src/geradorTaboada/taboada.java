/*Gerador de Tabuada

Descrição: Solicite ao usuário um número e exiba a tabuada desse número de 1 a 10.
Conceitos Praticados: Estruturas de repetição, entrada de dados, e lógica básica. */
package geradorTaboada;
import java.util.Scanner;

public class taboada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gerador de Taboada\n");

        System.out.println("Informe um número: ");
        int numero = scanner.nextInt();
        System.out.println("\n");

        for(int i = 0; i<=10; i++) {
            int resultado = numero*i;
            System.out.println(numero +" x "+ i + " = " + resultado);
        }
        scanner.close();
    }
}
