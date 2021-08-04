package controle_estoque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VendaTest {

    @Test
    public void retornarClienteInvalido() {
        try {
            Produto produto = new Produto("caneta", 30, 1, 0, 50);
            Venda venda = new Venda("26/07/2021", null, produto, 20);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Cliente invalido", e.getMessage());
        }
    }

}