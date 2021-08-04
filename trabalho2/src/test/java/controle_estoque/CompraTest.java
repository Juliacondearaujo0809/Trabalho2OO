package controle_estoque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CompraTest {

    @Test
    public void retornarPrecoUnitarioInvalido() {
        try {
            Fornecedor fornecedor = new Fornecedor("Antonio", "456");
            Produto produto = new Produto("caneta", 30, 1, 0, 50);
            Compra compra = new Compra("26/07/2021", produto, fornecedor, 78, -20);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Preço unitario invalido", e.getMessage());
        }
    }

    @Test
    public void retornarFornecedorInvalido() {
        try {
            Produto produto = new Produto("caneta", 30, 1, 0, 50);
            Compra compra = new Compra("26/07/2021", produto, null, 2, 100);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Fornecedor invalido", e.getMessage());
        }
    }

}