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

class FilmNowTest {

    private FilmNow filmNow;

    @BeforeEach
    void setUp() {
        filmNow = new FilmNow();
    }

    @Test
    void adicionaEmPosicaoVazia() {
        assertEquals("FILME ADICIONADO", filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+"));
        assertEquals("Avatar, 2009\nDisney+", filmNow.detalharFilme(1));
    }

    @Test
    void adicionaEmPosicaoOcupada() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("FILME ADICIONADO", filmNow.cadastraFilme(1, "20 dias em Mariupol", "2023", "Cinema"));
        assertEquals("20 dias em Mariupol, 2023\nCinema", filmNow.detalharFilme(1));
    }

    @Test
    void adicionaFilmeRepetidoEmOutraPosicao() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("FILME JA ADICIONADO", filmNow.cadastraFilme(3, "Avatar", "2009", "Disney+"));
        assertEquals("", filmNow.detalharFilme(3));
    }

    @Test
    void adicionaMesmoNomeEAnoComOutroLocal() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("FILME JA ADICIONADO", filmNow.cadastraFilme(3, "Avatar", "2009", "Popcornflix"));
        assertEquals("", filmNow.detalharFilme(3));
    }

    @Test
    void adicionaNaPosicaoLimite() {
        assertEquals("FILME ADICIONADO", filmNow.cadastraFilme(100, "Avatar", "2009", "Disney+"));
    }

    @Test
    void adicionaAcimaDoLimite() {
        assertEquals("POSIÇÃO INVÁLIDA", filmNow.cadastraFilme(101, "Avatar", "2009", "Disney+"));
    }

    @Test
    void adicionaAbaixoDoLimite() {
        assertEquals("POSIÇÃO INVÁLIDA", filmNow.cadastraFilme(0, "Avatar", "2009", "Disney+"));
    }

    @Test
    void adicionaComLocalVazio() {
        assertEquals("FILME INVALIDO", filmNow.cadastraFilme(1, "20 dias em Mariupol", "2023", ""));
        assertEquals("", filmNow.detalharFilme(1));
    }

    @Test
    void adicionaComAnoVazio() {
        assertEquals("FILME ADICIONADO", filmNow.cadastraFilme(1, "20 dias em Mariupol", "", "Cinema"));
    }

    @Test
    void adicionaComNomeVazio() {
        assertEquals("FILME INVALIDO", filmNow.cadastraFilme(1, "", "2023", "Cinema"));
        assertEquals("", filmNow.detalharFilme(1));
    }

    @Test
    void detalhaFilmeCompleto() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("Avatar, 2009\nDisney+", filmNow.detalharFilme(1));
    }

    @Test
    void detalhaFilmeSemAno() {
        filmNow.cadastraFilme(1, "20 dias em Mariupol", "", "Cinema");

        assertEquals("20 dias em Mariupol\nCinema", filmNow.detalharFilme(1));
    }

    @Test
    void detalhaPosicaoSemFilme() {
        assertEquals("", filmNow.detalharFilme(100));
    }

    @Test
    void detalhaAbaixoDoLimite() {
        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.detalharFilme(0));
    }

    @Test
    void detalhaAcimaDoLimite() {
        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.detalharFilme(101));
    }

    @Test
    void detalhaFilmeDaHotList() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");
        filmNow.atribuirHot(1, 1);

        assertEquals("🔥 Avatar, 2009\nDisney+", filmNow.detalharFilme(1));
    }

    @Test
    void mostraListaVazia() {
        assertEquals("", filmNow.mostrarFilmes());
    }

    @Test
    void mostraFilmes() {
        filmNow.cadastraFilme(11, "20 dias em Mariupol", "2023", "Cinema");
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("1 - Avatar\n11 - 20 dias em Mariupol", filmNow.mostrarFilmes());
    }

    @Test
    void atribuiHot() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("ADICIONADO À HOTLIST NA POSIÇÃO 1!", filmNow.atribuirHot(1, 1));
        assertEquals("1 - Avatar, 2009", filmNow.exibirHot());
    }

    @Test
    void naoAtribuiFilmeRepetidoNaHot() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");
        filmNow.atribuirHot(1, 1);

        assertEquals("FILME JA ESTA NA HOTLIST!", filmNow.atribuirHot(1, 2));
        assertEquals("1 - Avatar, 2009", filmNow.exibirHot());
    }

    @Test
    void atribuiHotEmPosicaoOcupadaSubstitui() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");
        filmNow.cadastraFilme(2, "20 dias em Mariupol", "2023", "Cinema");
        filmNow.atribuirHot(1, 1);

        filmNow.atribuirHot(2, 1);

        assertEquals("1 - 20 dias em Mariupol, 2023", filmNow.exibirHot());
        assertEquals("Avatar, 2009\nDisney+", filmNow.detalharFilme(1));
    }

    @Test
    void removeHot() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");
        filmNow.atribuirHot(1, 1);

        assertEquals("", filmNow.removerHot(1));
        assertEquals("", filmNow.exibirHot());
        assertEquals("Avatar, 2009\nDisney+", filmNow.detalharFilme(1));
    }

    @Test
    void hotComPosicaoInvalida() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.atribuirHot(1, 11));
        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.atribuirHot(1, 0));
        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.atribuirHot(2, 1));
        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.removerHot(11));
    }

    @Test
    void exibeHotVazia() {
        assertEquals("", filmNow.exibirHot());
    }

    @Test
    void adicionaLocalAoFilme() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("LOCAL ADICIONADO", filmNow.adicionarLocal(1, "Prime Video"));
        assertEquals("Avatar, 2009\nDisney+, Prime Video", filmNow.detalharFilme(1));
    }

    @Test
    void removeLocalDoFilme() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");
        filmNow.adicionarLocal(1, "Prime Video");

        assertEquals("LOCAL REMOVIDO", filmNow.removerLocal(1, "Disney+"));
        assertEquals("Avatar, 2009\nPrime Video", filmNow.detalharFilme(1));
    }

    @Test
    void naoRemoveUnicoLocalDoFilme() {
        filmNow.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("LOCAL NAO REMOVIDO", filmNow.removerLocal(1, "Disney+"));
    }

    @Test
    void localEmPosicaoInvalida() {
        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.adicionarLocal(1, "Cinema"));
        assertEquals("POSIÇÃO INVÁLIDA!", filmNow.removerLocal(101, "Cinema"));
    }
}