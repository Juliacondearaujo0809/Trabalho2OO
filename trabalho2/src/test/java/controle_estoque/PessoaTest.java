package controle_estoque;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void retornarNomeInvalido() {
        try {
            Pessoa pessoa = new Pessoa(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Nome invalido", e.getMessage());
        }
    }
}