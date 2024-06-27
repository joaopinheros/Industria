package com.industria.models;

import java.time.LocalDate;

/* Questão 1: Classe Pessoa com os atributos:
    nome (String) e data nascimento (LocalDate).
*/

/* Classe que representa o objeto pessoa */
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
