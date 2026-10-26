
package model;

import java.io.Serializable;

public class Manutencao implements Serializable {
private static final long seriaVescionuID =1l;

  
    

    private String id;
    private String moradorId;
    private String apartametoId;
    private String tipo;
    private String descriacao;
    private String dataPedido;
    private String estado;
    private String responsavelId;
    private String dataConclusao;
    
    
    public Manutencao(){
        
    }

    public Manutencao(String id, String moradorId, String apartametoId, String tipo, String descriacao, String dataPedido, String estado, String responsavelId, String dataConclusao) {
        this.id = id;
        this.moradorId = moradorId;
        this.apartametoId = apartametoId;
        this.tipo = tipo;
        this.descriacao = descriacao;
        this.dataPedido = dataPedido;
        this.estado = estado;
        this.responsavelId = responsavelId;
        this.dataConclusao = dataConclusao;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescriacao() {
        return descriacao;
    }

    public void setDescriacao(String descriacao) {
        this.descriacao = descriacao;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(String responsavelId) {
        this.responsavelId = responsavelId;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
    
    
}
