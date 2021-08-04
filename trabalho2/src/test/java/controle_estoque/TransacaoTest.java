package controle_estoque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TransacaoTest {

    @Test
    public void retornarDataTransacaoInvalida() {
        try {

            Produto produto = new Produto("caneta", 30, 1, 0, 50);
            Transacao transacao = new Transacao(null, produto, 20);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Data transacao invalida", e.getMessage());
        }
    }

}