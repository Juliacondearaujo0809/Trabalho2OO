package controle_estoque;

public class Venda extends Transacao {

    Cliente cliente;

    public Venda(String dataVenda, Cliente cliente, Produto produto, int qtdeVendida) {
        super(dataVenda, produto, qtdeVendida);
        if (cliente == null) {
            throw new NullPointerException("Cliente invalido");
        }
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    public boolean vender(Produto produto, int qtdeVendida) {
        if (produto.verificarEstoqueInsuficiente(qtdeVendida)) {
            produto.registrarHistorico("Estoque invalido");
            return false;
        }
        produto.debitarEstoque(qtdeVendida);
        produto.registrarHistorico("Transacao venda realizada");

        produto.calculaValorVenda(qtdeVendida);
        if (produto.verificarEstoqueBaixo()) {
            produto.registrarHistorico("Estoque baixo");
            return true;
        }

        return false;

    }

}