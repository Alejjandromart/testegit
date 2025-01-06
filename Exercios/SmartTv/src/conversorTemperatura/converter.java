/*Conversor de Temperatura

Descrição: Crie um programa que converte temperaturas entre Celsius, Fahrenheit e Kelvin.
fahrenheit for Celsius = (32 °F − 32) × 5/9 = 0 °C
Celsius for Fahrenheit = (0 °C × 9/5) + 32 = 32 °F
Kelvin for Celsius = 0 K − 273,15 = -273,1 °C

Conceitos Praticados: Operações matemáticas, estrutura condicional, e uso de métodos. */
package conversorTemperatura;
import java.util.Scanner;

public class converter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Coverter Temperaturas \n");

        System.out.println("Informe o valor da temperatura: ");
        double temperatura = scanner.nextDouble();

        System.out.println("Qual sistema de medida? (C = Celsius, F = Fahrenheit ou K = Kelvin)");
        char medida = scanner.next().charAt(0);
        char medidaAlta = Character.toUpperCase(medida);
        if(medida == 'C' || medida == 'F' || medida == 'K');
        else{
            System.out.println("Medida inválida");
        }
       

        if (medidaAlta == 'C'){
            System.out.println("Qual sistema de medida para ser convertida? (F = Fahrenheit ou K = Kelvin)");
            char haconverte = scanner.next().charAt(0);
            char haconverteAlta = Character.toUpperCase(haconverte);
            if (haconverteAlta == 'F'){
                double resultado = (temperatura * 9/5) + 32;
                System.out.println(temperatura + "°C" + " = " + resultado + "°F");
            }else if (haconverteAlta == 'K'){
                double resultado = temperatura + 273.15;
                System.out.println(temperatura + "°C" + " = " + resultado + "k");
            }else if (haconverteAlta == 'C'){
                System.out.println(temperatura + "°C" + " = " + temperatura + "°C");
            }else{
                System.out.println("Medida inválida");
            }
        }

        if (medidaAlta == 'F'){
            System.out.println("Qual sistema de medida para ser convertida? (C = Celsius ou K = Kelvin)");
            char haconverte = scanner.next().charAt(0);
            char haconverteAlta = Character.toUpperCase(haconverte);
            if (haconverteAlta == 'C'){
                System.out.println("Fahrenheit para Celsius");
                double resultado = (temperatura - 32) * 5/9;
                System.out.println(temperatura + "°F" + " = " + resultado + "°C");
            }else if (haconverteAlta == 'K'){
                System.out.println("Fahrenheit para Kelvin");
                double resultado = (temperatura - 32) * 5/9 + 273.15;
                System.out.println(temperatura + "°F" + " = " + resultado + "k");
            }else if (haconverteAlta == 'F'){
                System.out.println(temperatura + "°F" + " = " + temperatura + "°F");
            }
            else{
                System.out.println("Medida inválida");
            }
        }

        if (medidaAlta == 'K'){
            System.out.println("Qual sistema de medida para ser convertida? (C = Celsius ou F = Fahrenheit)");
            char haconverte = scanner.next().charAt(0);
            char haconverteAlta = Character.toUpperCase(haconverte);
            if (haconverteAlta == 'F'){
                double resultado = (temperatura - 273.15) * 9/5 + 32;
                System.out.println(temperatura + "K" + " = " + resultado + "°F");
            }else if (haconverteAlta == 'C'){
                double resultado = temperatura - 273.15;
                System.out.println(temperatura + "K" + " = " + resultado + "°C");
            }else if (haconverteAlta == 'K'){
                System.out.println(temperatura + "K" + " = " + temperatura + "K");
            }else{
                System.out.println("Medida inválida");
            }
        }
        scanner.close();
    }
}
