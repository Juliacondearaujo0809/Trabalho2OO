package controle_estoque;


public class Pessoa {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa(String nome) {
        if (nome == null){
            throw new IllegalArgumentException("Nome invalido");
        }
        this.nome = nome;
    }
}