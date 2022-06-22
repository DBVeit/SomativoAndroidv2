package com.example.mycartandroidv2.Model;

public class Model {
    private int idItem;
    private String nomeItem;
    private int qtdItem;
    private float valorItem;
    private int chkItem;
    private String nomeLista;

    public Model(int idItem, String nomeItem, int qtdItem, float valorItem, int chkItem, String nomeLista) {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.qtdItem = qtdItem;
        this.valorItem = valorItem;
        this.chkItem = chkItem;
        this.nomeLista = nomeLista;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public int getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(int qtdItem) {
        this.qtdItem = qtdItem;
    }

    public float getValorItem() {
        return valorItem;
    }

    public void setValorItem(float valorItem) {
        this.valorItem = valorItem;
    }

    public int getChkItem() {
        return chkItem;
    }

    public void setChkItem(int chkItem) {
        this.chkItem = chkItem;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
}
