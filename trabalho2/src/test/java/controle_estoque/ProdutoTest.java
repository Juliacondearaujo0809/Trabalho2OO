package controle_estoque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProdutoTest {

    @Test
    public void retornarNomeInvalido() {
        try {
            Produto produto = new Produto(null, 20, 1, 0, 60);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Nome invalido", e.getMessage());
        }
    }

    @Test
    public void retornarEstoqueMinimoInvalido() {
        try {
            Produto produto = new Produto("caneta", 20, 1, -10, 50);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Estoque minimo invalido", e.getMessage());
        }
    }

    @Test
    public void retornarEstoqueMaximoInvalido() {
        try {
            Produto produto = new Produto("caneta", 20, 1, 0, -10);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Estoque maximo invalido", e.getMessage());
        }
    }

    @Test
    public void deveRetornarRegistroNoHistorico() {
        Produto produto = new Produto("caneta", 100, 1, 10, 200);
        Cliente cliente = new Cliente("Marco", "123");
        Fornecedor fornecedor = new Fornecedor("Antonio", "456");
        produto.vender("26/07/2021", cliente, 10);
        produto.comprar("26/07/2021", fornecedor, 5, 1);

        List<String> historico = Arrays.asList("Transacao venda realizada","Transacao compra realizada","26/07/2021","5");
        assertEquals(historico, produto.exibirHistorico());
    }

    @Test
    public void retornarHistoricoSemValor() {
        try {
            Produto produto = new Produto("caneta", 100, 1, 10, 200);
            produto.exibirHistorico();

        } catch (NullPointerException e) {
            assertEquals("Historico sem valor", e.getMessage());
        }
    }

    @Test
    public void retornarDebitoDoEstoque() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);
        produto.debitarEstoque(8);

        assertEquals(22, produto.getQtdeEstoque());
    }

    @Test
    public void retornarCreditoNoEstoque() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);
        produto.creditarEstoque(10);

        assertEquals(40, produto.getQtdeEstoque());
    }

    @Test
    public void retornarDebitoInvalidoEstoque() {
        try {
            Produto produto = new Produto("caneta", 30, 1, 0, 50);
            produto.debitarEstoque(100);
            fail();

        } catch (IllegalArgumentException e) {
            assertEquals("Quantidade invalida", e.getMessage());
        }
    }

    @Test
    public void retornarEstoqueInvalido() {
        Produto produto = new Produto("caneta", 30, 1, 5, 50);

        assertEquals(true, produto.verificarEstoqueInsuficiente(40));
    }

    @Test
    public void retornarEstoqueBaixo() {
        Produto produto = new Produto("caneta", 10, 1, 12, 50);

        assertEquals(true, produto.verificarEstoqueBaixo());
    }

    @Test
    public void retornarEstoqueExcedente() {
        Produto produto = new Produto("caneta", 30, 1, 12, 50);

        assertEquals(true, produto.verificarEstoqueExcedente(30));
    }

    @Test
    public void retornarValorVenda() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);

        assertEquals(5, produto.calculaValorVenda(5));
    }

    @Test
    public void retornarValorVendaErrado() {
        try {
            Produto produto = new Produto("caneta", 30, 1, 0, 50);
            produto.calculaValorVenda(-20);

        } catch (IllegalArgumentException e) {
            assertEquals("Quantidade invalida", e.getMessage());
        }
    }

    @Test
    public void retornarHistoricoDaVenda() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);
        Cliente cliente = new Cliente("Marco", "123");
        produto.vender("26/07/2021", cliente, 2);

        List<String> historico = Arrays.asList("Transacao venda realizada");
        assertEquals(historico, produto.exibirHistorico());
    }

    @Test
    public void retornarHistoricoDaCompra() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);
        Fornecedor fornecedor = new Fornecedor("Antonio", "456");
        produto.comprar("26/07/2021", fornecedor, 5, 1);

        List<String> historico = Arrays.asList("Transacao compra realizada", "26/07/2021", "5");
        assertEquals(historico, produto.exibirHistorico());
    }

    @Test
    public void retornarDebitoVendaEstoque() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);
        Cliente cliente = new Cliente("Marco", "123");
        produto.vender("26/07/2021", cliente, 10);

        assertEquals(20, produto.getQtdeEstoque());
    }

    @Test
    public void retornarEstoqueInvalidoVenda() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);
        Cliente cliente = new Cliente("Marco", "123");
        produto.vender("26/07/2021", cliente, 40);

        List<String> historico = Arrays.asList("Estoque invalido");
        assertEquals(historico, produto.exibirHistorico());
    }

    @Test
    public void retornarEstoqueInvalidoCompra() {
        Produto produto = new Produto("caneta", 30, 1, 0, 50);
        Fornecedor fornecedor = new Fornecedor("Antonio", "456");
        produto.comprar("26/07/2021", fornecedor, 30, 1);

        List<String> historico = Arrays.asList("Excedente");
        assertEquals(historico, produto.exibirHistorico());
    }

    @Test
    public void retornarEstoqueBaixoVenda() {
        Produto produto = new Produto("caneta", 30, 1, 10, 50);
        Cliente cliente = new Cliente("Marco", "123");
        produto.vender("26/07/2021", cliente, 25);

        List<String> historico = Arrays.asList("Transacao venda realizada",
                "Estoque baixo", "26/07/2021", "25");
        assertEquals(historico, produto.exibirHistorico());
    }


}