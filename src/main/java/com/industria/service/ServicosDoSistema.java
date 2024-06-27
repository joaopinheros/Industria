package com.industria.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import com.industria.models.Funcionario;

/**
 * Classe responsável pelos serviços relacionados aos funcionários da indústria.
 */
public class ServicosDoSistema {
    private List<Funcionario> funcionarios;

    /**
     * Construtor da classe ServicosDoSistema.
     * Inicializa a lista de funcionários.
     */
    public ServicosDoSistema() {
        this.funcionarios = new ArrayList<>();
    }

    /**
     * Insere funcionários predefinidos na lista.
     */
    public void inserirFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        imprimirFuncionarios();
    }

    /**
     * Remove um funcionário da lista pelo nome.
     *
     * @param nome Nome do funcionário a ser removido.
     */
    public void removerFuncionario(String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
        imprimirFuncionarios();
    }

    /**
     * Imprime todos os funcionários com suas informações formatadas.
     */
    public void imprimirFuncionarios() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormatter = new DecimalFormat("#,##0.00");

        for (Funcionario f : funcionarios) {
            String dataNascimento = f.getDataNascimento().format(dateFormatter);
            String salario = decimalFormatter.format(f.getSalario());
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %s, Função: %s%n",
                    f.getNome(), dataNascimento, salario, f.getFuncao());
        }
    }

    /**
     * Aumenta o salário de todos os funcionários em um determinado percentual.
     *
     * @param percentual Percentual de aumento.
     */
    public void aumentarSalario(double percentual) {
        for (Funcionario f : funcionarios) {
            BigDecimal aumento = f.getSalario().multiply(BigDecimal.valueOf(percentual / 100));
            f.setSalario(f.getSalario().add(aumento));
        }
    }

    /**
     * Agrupa os funcionários por função.
     *
     * @return Mapa onde a chave é a função e o valor é a lista de funcionários dessa função.
     */
    public Map<String, List<Funcionario>> agruparPorFuncao() {
        Map<String, List<Funcionario>> groupedByFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            String funcao = f.getFuncao();
            if (!groupedByFuncao.containsKey(funcao)) {
                groupedByFuncao.put(funcao, new ArrayList<>());
            }
            groupedByFuncao.get(funcao).add(f);
        }
        return groupedByFuncao;
    }

    /**
     * Imprime os funcionários agrupados por função.
     */
    public void imprimirAgrupadosPorFuncao() {
        Map<String, List<Funcionario>> map = agruparPorFuncao();
        map.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(f -> System.out.println("  - " + f.getNome()));
        });
    }

    /**
     * Imprime os funcionários que fazem aniversário nos meses especificados.
     *
     * @param meses Meses para verificar os aniversariantes.
     */
    public void imprimirAniversariantes(int... meses) {
        List<Integer> mesesList = Arrays.stream(meses).boxed().collect(Collectors.toList());
        for (Funcionario f : funcionarios) {
            if (mesesList.contains(f.getDataNascimento().getMonthValue())) {
                System.out.println(f.getNome());
            }
        }
    }

    /**
     * Imprime o funcionário mais velho.
     * Calcula a diferença em anos entre duas datas fornecidas.
     * Utiliza a enumeração ChronoUnit para realizar cálculos de tempo baseados em unidades de data.
     */
    public void imprimirFuncionarioMaisVelho() {
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow(NoSuchElementException::new);
        long idade = ChronoUnit.YEARS.between(maisVelho.getDataNascimento(), LocalDate.now());
        System.out.printf("Nome: %s, Idade: %d%n", maisVelho.getNome(), idade);
    }

    /**
     * Imprime a lista de funcionários em ordem alfabética.
     */
    public void imprimirFuncionariosOrdenados() {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));
    }

    /**
     * Imprime o total dos salários dos funcionários.
     */
    public void imprimirTotalSalarios() {
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total dos salários: %,.2f%n", total);
    }

    /**
     * Imprime quantos salários mínimos cada funcionário ganha.
     *
     * @param salarioMinimo Valor do salário mínimo.
     */
    public void imprimirSalariosMinimos(double salarioMinimo) {
        for (Funcionario f : funcionarios) {
            double salariosMinimos = f.getSalario().doubleValue() / salarioMinimo;
            System.out.printf("%s ganha %.2f salários mínimos%n", f.getNome(), salariosMinimos);
        }
    }
}
