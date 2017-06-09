package com.supero.rausch.contatos.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rausch on 08/06/2017.
 */

public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String nome;
    private String email;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String telefone;
    private List<String> validationErrorMessages;

    public Contato(String nome, String email, String cep, String logradouro, String numero, String bairro, String cidade, String uf, String telefone, String complemento) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.telefone = telefone;
        this.complemento = complemento;
        this.validationErrorMessages = new ArrayList<String>();
    }

    public Contato(long id, String nome, String email, String cep, String logradouro, String numero, String bairro, String cidade, String uf, String telefone, String complemento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.telefone = telefone;
        this.complemento = complemento;
        this.validationErrorMessages = new ArrayList<String>();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isValid() {
        Boolean valid = true;
        if (nome == null || nome.trim().isEmpty()) {
            validationErrorMessages.add("Informe o Nome");
            valid = false;
        }

        if (email == null || email.trim().isEmpty() || !emailValido()) {
            validationErrorMessages.add("Informe um e-mail válido");
            valid = false;
        }

        if (cep == null || cep.trim().isEmpty()) {
            validationErrorMessages.add("Informe o CEP");
            valid = false;
        }

        if (logradouro == null || logradouro.trim().isEmpty()) {
            validationErrorMessages.add("Informe o Logradouro");
            valid = false;
        }

        if (numero == null || numero.trim().isEmpty()) {
            validationErrorMessages.add("Informe o Número");
            valid = false;
        }

        if (bairro == null || bairro.trim().isEmpty()) {
            validationErrorMessages.add("Informe o Bairro");
            valid = false;
        }

        if (cidade == null || cidade.trim().isEmpty()) {
            validationErrorMessages.add("Informe o Cidade");
            valid = false;
        }

        if (uf == null || uf.trim().isEmpty()) {
            validationErrorMessages.add("Informe o UF");
            valid = false;
        }

        if (telefone == null || telefone.trim().isEmpty()) {
            validationErrorMessages.add("Informe o Telefone");
            valid = false;
        }

        return valid;
    }

    private boolean emailValido() {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public String getValidationErrorMessage() {
        StringBuilder builder = new StringBuilder();

        builder.append("Não foi possível salvar: ");

        for (String errorMessage : validationErrorMessages) {
            builder.append(System.getProperty("line.separator"));
            builder.append("- " + errorMessage);
        }

        return builder.toString();
    }
}
