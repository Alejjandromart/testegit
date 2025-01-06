/*Calculadora Simples

Descrição: Crie um programa que realiza operações matemáticas básicas como soma, subtração, multiplicação e divisão.
Conceitos Praticados: Estruturas de controle, entrada/saída, e uso de métodos para modularidade.*/

package calcuradora;
import java.util.Scanner;

public class calcular {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o primeiro valor: ");
        double primeiro = scanner.nextDouble();

        System.out.println("Informe o segundo valor: ");
        double segundo = scanner.nextDouble();

        System.out.println("Qual operação deseja fazer? (A = adição, S = subtração, M = multiplicação ou D = divisão)");
        char operação = scanner.next().charAt(0);
        char operaçãoMaiuscula = Character.toUpperCase(operação);

        if (operaçãoMaiuscula == 'A') {
            double soma = primeiro + segundo;
            System.out.println("valor: " + soma);

        }else if (operaçãoMaiuscula == 'S'){
            double subtração = primeiro - segundo;
            System.out.println("valor: " + subtração);

        }else if (operaçãoMaiuscula == 'M'){
            double multiplicação = primeiro * segundo;
            System.out.println("valor: " + multiplicação);

        }else if (operaçãoMaiuscula == 'D'){
            if (primeiro > 0){
                double divisão = primeiro / segundo;  
                System.out.println("valor: " + divisão);     
            }else{
                System.out.println("O primeiro valor tem que ser maior que ZERO");
            }
        }else{
            System.out.println("Operação inválida");
        }

        scanner.close();
    }
}
