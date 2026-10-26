
package model;
import java.io.Serializable;

public class  Cadastro implements Serializable {
    private static final long seriaVersionuID = 1l;
    private String id;
    private String nomeCompleto;
    private String dataDeNascimento;
    private String bi;
    private String telefone;
    private String  apartamento;
    private String dataCadastro;
    private boolean ativo;
    private String usuarioId;
    
    
    public Cadastro(){
        
    }
    public Cadastro(String id, String nomeCompleto, String dataDeNascimento, String bi, String telefone, String apartamento, String dataCadastro, boolean ativo, String usuarioId) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.dataDeNascimento = dataDeNascimento;
        this.bi = bi;
        this.telefone = telefone;
        this.apartamento = apartamento;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.usuarioId = usuarioId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    
    
    
}
