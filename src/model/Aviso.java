
package model;

import java.io.Serializable;

public class Aviso implements Serializable{
    private static final long seriaVescionuID =1l;
    private  String id;
    private String titulo;
    private String mesnsagem;
    private String  dataPublicacao;
    private String autorId;
    private boolean ativo;
    
    public Aviso(){
        
    }

    public Aviso(String id, String titulo, String mesnsagem, String dataPublicacao, String autorId, boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.mesnsagem = mesnsagem;
        this.dataPublicacao = dataPublicacao;
        this.autorId = autorId;
        this.ativo = ativo;
        
        
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMesnsagem() {
        return mesnsagem;
    }

    public void setMesnsagem(String mesnsagem) {
        this.mesnsagem = mesnsagem;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
   
}
