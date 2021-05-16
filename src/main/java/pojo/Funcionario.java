
package pojo;

public class Funcionario {
    private int id_funcionario;
    private String nome_completo;
    private String telefone;
    
    public Funcionario(int id_funcionario, String nome_completo, String telefone){
        this.id_funcionario = id_funcionario;
        this.nome_completo = nome_completo;
        this.telefone = telefone;
    }

    public Funcionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String toString(){
        return id_funcionario+" "+ nome_completo+" "+telefone;
    }
}
