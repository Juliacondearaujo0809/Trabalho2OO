package controle_estoque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void retornarCpfInvalido() {
        try {
            Cliente cliente = new Cliente("Marco", null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("CPF invalido", e.getMessage());
        }
    }
}