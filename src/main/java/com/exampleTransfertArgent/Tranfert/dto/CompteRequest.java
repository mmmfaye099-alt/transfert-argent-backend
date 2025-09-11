package com.exampleTransfertArgent.Tranfert.dto;

public class CompteRequest {
    private String numero;
    private Double solde;
    private Long userId;

    public CompteRequest() {}

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getSolde() {
        return solde;
    }
    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
