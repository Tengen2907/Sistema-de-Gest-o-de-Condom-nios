
package model;

import java.io.Serializable;


public class Notificacao  implements Serializable {
    public static final long seriaVescionuID =1l;

    private String id;
    private String destinatarioId;
    private String tipo;
    private String mensagem;
    private String data;
    private boolean lida;

    public Notificacao(String id, String destinatarioId, String tipo, String mensagem, String data, boolean lida) {
        this.id = id;
        this.destinatarioId = destinatarioId;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.data = data;
        this.lida = lida;
    }
    
  
    public Notificacao (){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(String destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

}
