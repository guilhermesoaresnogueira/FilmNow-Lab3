package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmNowTest {

    private FilmNow filmNow;

    @BeforeEach
    void setUp() {
        filmNow = new FilmNow();
    }

    @Test
    void cadastraValido() {
        String resposta = filmNow.cadastraFilme(1, "Anatomia de uma Queda", "2023", "Cinema");

        assertEquals("FILME ADICIONADO", resposta);
        assertEquals("Anatomia de uma Queda", filmNow.getFilme(1).getNome());
        assertEquals("2023", filmNow.getFilme(1).getAno());
        assertEquals("Cinema", filmNow.getFilme(1).getLocal());
    }
}a