/**
 * Representação de um estudante, especificamente de computação, matriculado da * UFCG. Todo aluno precisa ter uma matrícula e é identificado unicamente
 * por esta matrícula: 20260014800
 *
 * @author Guilherme Soares Nogueira
 */

package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmeTest {

    private Filme avatar;

    @BeforeEach
    void setUp() {
        avatar = new Filme("Avatar", "2009", "Disney+");
    }

    @Test
    void toStringComAno() {
        assertEquals("Avatar, 2009", avatar.toString());
    }

    @Test
    void toStringSemAno() {
        Filme filme = new Filme("20 dias em Mariupol", "", "Cinema");

        assertEquals("20 dias em Mariupol", filme.toString());
    }

    @Test
    void equalsMesmoNomeEAno() {
        assertTrue(avatar.equals(new Filme("Avatar", "2009", "Popcornflix")));
    }

    @Test
    void equalsNomeDiferente() {
        assertFalse(avatar.equals(new Filme("Shrek 2", "2009", "Disney+")));
    }

    @Test
    void equalsAnoDiferente() {
        assertFalse(avatar.equals(new Filme("Avatar", "2022", "Disney+")));
    }

    @Test
    void equalsOutroTipo() {
        assertFalse(avatar.equals("Donzela"));
    }

    @Test
    void equalsNulo() {
        assertFalse(avatar.equals(null));
    }

    @Test
    void nomeNulo() {
        NullPointerException erro = assertThrows(NullPointerException.class,
                () -> new Filme(null, "2009", "Disney+"));

        assertEquals("FILME NULO", erro.getMessage());
    }

    @Test
    void anoNulo() {
        assertThrows(NullPointerException.class, () -> new Filme("Avatar", null, "Disney+"));
    }

    @Test
    void localNulo() {
        assertThrows(NullPointerException.class, () -> new Filme("Avatar", "2009", null));
    }

    @Test
    void nomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Filme("", "2009", "Disney+"));
    }

    @Test
    void nomeEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> new Filme("   ", "2009", "Disney+"));
    }

    @Test
    void localVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Filme("Avatar", "2009", ""));
    }

    @Test
    void anoVazioEhValido() {
        assertDoesNotThrow(() -> new Filme("Avatar", "", "Disney+"));
    }

    @Test
    void adicionaLocal() {
        assertTrue(avatar.adicionarLocal("Prime Video"));

        assertEquals("Disney+, Prime Video", avatar.getLocais());
    }

    @Test
    void naoAdicionaLocalRepetido() {
        assertFalse(avatar.adicionarLocal("Disney+"));

        assertEquals("Disney+", avatar.getLocais());
    }

    @Test
    void naoAdicionaLocalVazio() {
        assertFalse(avatar.adicionarLocal(""));
        assertFalse(avatar.adicionarLocal("   "));
    }

    @Test
    void limiteDeCincoLocais() {
        assertTrue(avatar.adicionarLocal("Cinema"));
        assertTrue(avatar.adicionarLocal("Netflix"));
        assertTrue(avatar.adicionarLocal("Prime Video"));
        assertTrue(avatar.adicionarLocal("Popcornflix"));

        assertFalse(avatar.adicionarLocal("Max"));
        assertEquals("Disney+, Cinema, Netflix, Prime Video, Popcornflix", avatar.getLocais());
    }

    @Test
    void removeLocal() {
        avatar.adicionarLocal("Prime Video");

        assertTrue(avatar.removerLocal("Disney+"));

        assertEquals("Prime Video", avatar.getLocais());
    }

    @Test
    void naoRemoveUnicoLocal() {
        assertFalse(avatar.removerLocal("Disney+"));

        assertEquals("Disney+", avatar.getLocais());
    }

    @Test
    void naoRemoveLocalInexistente() {
        avatar.adicionarLocal("Prime Video");

        assertFalse(avatar.removerLocal("Cinema"));

        assertEquals("Disney+, Prime Video", avatar.getLocais());
    }
}
