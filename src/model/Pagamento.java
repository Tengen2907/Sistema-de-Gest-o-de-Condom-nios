
package model;

import java.io.Serializable;


public class Pagamento   implements Serializable {
    public static   final long seriaVescionuID =1l;
 
    private String id;
    private String moradorId;
    private String apartametoId;
    private String salario;
    private String referencia;
    private String mesReferencia;
    private int anoReferencia;
    private double valor;
    private String metodoPagamento;
    private String dataPagamento;
    private String estado;

    public Pagamento(String id, String moradorId, String apartametoId, String salario, String referencia, String mesReferencia, int anoReferencia, double valor, String metodoPagamento, String dataPagamento, String estado) {
        this.id = id;
        this.moradorId = moradorId;
        this.apartametoId = apartametoId;
        this.salario = salario;
        this.referencia = referencia;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.dataPagamento = dataPagamento;
        this.estado = estado;
    }
    
    public Pagamento(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoradorId() {
        return moradorId;
    }

    public void setMoradorId(String moradorId) {
        this.moradorId = moradorId;
    }

    public String getApartametoId() {
        return apartametoId;
    }

    public void setApartametoId(String apartametoId) {
        this.apartametoId = apartametoId;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public int getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(int anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
