package controle_estoque;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FornecedorTest {

    @Test
    void retornarCnpjInvalido() {
        try {
            Fornecedor fornecedor = new Fornecedor("Antonio", null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("CNPJ invalido", e.getMessage());
        }
    }


}