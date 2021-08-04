package controle_estoque;

import java.util.ArrayList;
import java.util.List;

public class Produto {

    private List<String> historico = new ArrayList<>();
    private String nome;
    private int qtdeEstoque;
    private Integer precoUnit;
    private int estoqueMinimo;
    private int estoqueMaximo;

    public Integer getPrecoUnit() {

        return precoUnit;
    }

    public void setPrecoUnit(Integer precoUnit) {

        this.precoUnit = precoUnit;
    }

    public List<String> getHistorico() {

        return historico;
    }

    public void setHistorico(List<String> historico) {

        this.historico = historico;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public int getQtdeEstoque() {

        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {

        this.qtdeEstoque = qtdeEstoque;
    }

    public int getEstoqueMinimo() {

        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {

        this.estoqueMinimo = estoqueMinimo;
    }

    public int getEstoqueMaximo() {

        return estoqueMaximo;
    }

    public void setEstoqueMaximo(int estoqueMaximo) {

        this.estoqueMaximo = estoqueMaximo;
    }

    public Produto(String nome, int qtdeEstoque, Integer precoUnit, int estoqueMinimo, int estoqueMaximo) {

        if ( nome == null){
            throw new IllegalArgumentException("Nome invalido");
        }

        if (estoqueMinimo < 0){
            throw new IllegalArgumentException("Estoque minimo invalido");

        }
        if (estoqueMaximo < 0){
            throw new IllegalArgumentException("Estoque maximo invalido");

        }
        this.nome = nome;
        this.qtdeEstoque = qtdeEstoque;
        this.precoUnit = precoUnit;
        this.estoqueMinimo = estoqueMinimo;
        this.estoqueMaximo = estoqueMaximo;

    }

    public void registrarHistorico(String transacao) {
        this.historico.add(transacao);
    }

    public List<String> exibirHistorico() {
        if (historico == null){
            throw new NullPointerException("Historico sem valor");
        }
        return this.historico;
    }

    public void debitarEstoque(int quantidade) {

        if (this.qtdeEstoque - quantidade < 0) {
            throw new IllegalArgumentException("Quantidade invalida");
        }

        this.qtdeEstoque = qtdeEstoque - quantidade;
    }

    public void creditarEstoque(int quantidade) {

        this.qtdeEstoque = qtdeEstoque + quantidade;
    }

    public boolean verificarEstoqueBaixo() {
        if (qtdeEstoque < estoqueMinimo) {
            return true;
        }
        return false;
    }

    public boolean verificarEstoqueInsuficiente(int quantidade) {
        if (this.qtdeEstoque >= quantidade)
            return false;
        return true;
    }

    public boolean verificarEstoqueExcedente(int quantidade) {
        if ( (this.qtdeEstoque + quantidade) > this.estoqueMaximo){
            return true;
        }
        return false;
    }

    public float calculaValorVenda(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade invalida");
        }
        return this.precoUnit * quantidade;

    }

    public void vender(String dataVenda, Cliente cliente, int qtdeVendida) {
        Venda venda = new Venda(dataVenda, cliente, this, qtdeVendida );
        if(venda.vender(this, qtdeVendida)){
            registrarHistorico(venda.getDataTransacao());
            registrarHistorico(String.valueOf(venda.getQtde()));

        }
    }


    public void comprar(String dataCompra, Fornecedor fornecedor, int qtdeCompra, Integer precoUnit) {
        Compra compra = new Compra(dataCompra, this, fornecedor, qtdeCompra, precoUnit);
        if(compra.comprar(this,qtdeCompra)){
            registrarHistorico(compra.getDataTransacao());
            registrarHistorico(String.valueOf(compra.getQtde()));
        }
    }


}