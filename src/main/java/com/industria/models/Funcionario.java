package com.industria.models;

import java.math.BigDecimal;
import java.time.LocalDate;

/* Questão 2:  Classe Funcionário que estenda a classe Pessoa, com os atributos:
   salário (BigDecimal) e função (String).
*/

/* Classe que representa o objeto funcionario que herda pessoa */
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }
}
