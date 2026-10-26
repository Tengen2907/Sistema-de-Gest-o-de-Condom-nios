package model;
import java.io.Serializable;

public class  Apartamento implements Serializable {
    private static final long seriaVersionuID = 1l;

  private String id;
  private String numero;
  private String bloco;
  private int andar;
  private double mesensalidade;
  private String estado;
  
  public Apartamento(){

}

    public Apartamento(String id, String numero, String bloco, int andar, double mesensalidade, String estado) {
        this.id = id;
        this.numero = numero;
        this.bloco = bloco;
        this.andar = andar;
        this.mesensalidade = mesensalidade;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public double getMesensalidade() {
        return mesensalidade;
    }

    public void setMesensalidade(double mesensalidade) {
        this.mesensalidade = mesensalidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
 