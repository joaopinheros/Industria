package com.industria.service;

import java.util.Scanner;

/**
 * Classe responsável por mostrar o menu e gerenciar a interação com o usuário.
 */
public class TelinhaSistema {

    /**
     * Método principal para mostrar o menu e executar as opções selecionadas pelo usuário.
     */
    public void mostrarMenu() {
        ServicosDoSistema servicos = new ServicosDoSistema();
        Scanner scanner = new Scanner(System.in);

        boolean executando = true;
        while (executando) {
            System.out.println("Escolha uma ação:");
            System.out.println("1 - Inserir funcionários");
            System.out.println("2 - Remover funcionário 'João'");
            System.out.println("3 - Imprimir todos os funcionários");
            System.out.println("4 - Aumentar salário dos funcionários em 10%");
            System.out.println("5 - Agrupar funcionários por função");
            System.out.println("6 - Imprimir funcionários agrupados por função");
            System.out.println("7 - Imprimir aniversariantes dos meses 10 e 12");
            System.out.println("8 - Imprimir funcionário com a maior idade");
            System.out.println("9 - Imprimir funcionários por ordem alfabética");
            System.out.println("10 - Imprimir total dos salários");
            System.out.println("11 - Imprimir quantos salários mínimos cada funcionário ganha");
            System.out.println("0 - Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    servicos.inserirFuncionarios();
                    break;
                case 2:
                    servicos.removerFuncionario("João");
                    break;
                case 3:
                    servicos.imprimirFuncionarios();
                    break;
                case 4:
                    servicos.aumentarSalario(10);
                    servicos.imprimirFuncionarios();
                    break;
                case 5:
                    servicos.agruparPorFuncao();
                    break;
                case 6:
                    servicos.imprimirAgrupadosPorFuncao();
                    break;
                case 7:
                    servicos.imprimirAniversariantes(10, 12);
                    break;
                case 8:
                    servicos.imprimirFuncionarioMaisVelho();
                    break;
                case 9:
                    servicos.imprimirFuncionariosOrdenados();
                    break;
                case 10:
                    servicos.imprimirTotalSalarios();
                    break;
                case 11:
                    servicos.imprimirSalariosMinimos(1640);
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
