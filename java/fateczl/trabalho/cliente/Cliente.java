package fateczl.trabalho.cliente;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNas;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public LocalDate getDataNas() {return dataNas;}
    public void setDataNas(LocalDate dataNas) {this.dataNas = dataNas;}

    @Override
    public String toString() {
        return this.nome;
    }
}

