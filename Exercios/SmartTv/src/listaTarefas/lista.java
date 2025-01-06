/*Gerenciador de Lista de Tarefas

Descrição: Permita ao usuário adicionar, visualizar e remover tarefas de uma lista.
Conceitos Praticados: Listas (ArrayList), manipulação de dados, e estrutura de menus. */
package listaTarefas;

import java.util.ArrayList;
import java.util.Scanner;

public class lista {
    public static void main(String[] args) {
        ArrayList<String> tarefa = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Lista de Tarefas!");

        while (true) { 
            System.out.println("\nEscolha uma ação:");
            System.out.println("A = Adicionar tarefa");
            System.out.println("R = Remover tarefa");
            System.out.println("V = Visualizar lista");
            System.out.println("S = Sair");
            System.out.print("Opção: ");
            char acao = scanner.next().toUpperCase().charAt(0);

            switch (acao) {
                case 'A':
                    System.out.print("Digite a tarefa a ser adicionada: ");
                    scanner.nextLine(); 
                    String novaTarefa = scanner.nextLine();
                    tarefa.add(novaTarefa);
                    System.out.println("Tarefa adicionada: " + novaTarefa);
                    break;

                case 'R':
                    if (tarefa.isEmpty()) {
                        System.out.println("A lista está vazia. Não há nada para remover.");
                    } else {
                        System.out.print("Digite a tarefa a ser removida: ");
                        scanner.nextLine(); 
                        String removerTarefa = scanner.nextLine();
                        if (tarefa.remove(removerTarefa)) {
                            System.out.println("Tarefa removida: " + removerTarefa);
                        } else {
                            System.out.println("Tarefa não encontrada na lista.");
                        }
                    }
                    break;

                case 'V':
                    if (tarefa.isEmpty()) {
                        System.out.println("A lista está vazia.");
                    } else {
                        System.out.println("Suas tarefas:");
                        for (String t : tarefa) {
                            System.out.println("- " + t);
                        }
                    }
                    break;

                case 'S':
                    System.out.println("Encerrando o programa. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }
}

