package com.example.th.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Entity
@Document(collection = "pessoas")
public class Pessoa{

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "idade")
    private int idade;

    public Pessoa(){

    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
