package servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LocacaoServiceTest {
    private LocacaoService locacaoService;
    private Usuario usuario;
    private List<Filme> filmes;

    @Before
    public void setup(){
        //Cenario
        this.locacaoService = new LocacaoService();
        this.usuario = new Usuario("Usuario");
        this.filmes = new ArrayList();
                filmes.add(new Filme("Filme 1", 1, 5.0));
                filmes.add(new Filme("Filme 2", 3, 4.0));
                filmes.add(new Filme("Filme 3", 2, 8.0));
    }

    @Test
    public void alugarFilme() throws Exception {
            //Acao
            Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

            //Resultado
            assertEquals(15.0, locacao.getValor(), 0.01);

            assertThat(locacao.getValor(), is(equalTo(15.0)));

            assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));

            assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

    @Test(expected = Exception.class)
    public void alugarFilmeSemEstoqueExceptionAnotacao() throws Exception {
        //Acao
        filmes.get(0).setEstoque(0);
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        assertEquals(5.0, locacao.getValor(), 0.01);
    }

    @Test
    public void alugarFilmeSemEstoqueException() {
        //Acao
        filmes.get(0).setEstoque(0);

        //Resultado
        Throwable ex = Assert.assertThrows(Exception.class, () -> locacaoService.alugarFilme(usuario, filmes));

        Assert.assertEquals(ex.getMessage(), "Filme sem estoque");
    }

    @Test
    public void alugarFilmeUsuarioVazioException() {
        //Resultado
        Throwable ex = Assert.assertThrows(LocadoraException.class, () -> locacaoService.alugarFilme(null, filmes));

        assertEquals(ex.getMessage(), "Usuario vazio");
    }

    @Test
    public void alugarFilmeListaFilmesVaziaException() {
        //Resultado
        Throwable ex = Assert.assertThrows(LocadoraException.class, () -> locacaoService.alugarFilme(usuario, null));

        assertEquals(ex.getMessage(), "Lista de filmes vazia");
    }

    @Test
    public void alugarFilmeFilmeNuloException() {
        filmes.add(null);

        //Resultado
        Throwable ex = Assert.assertThrows(LocadoraException.class, () -> locacaoService.alugarFilme(usuario, filmes));

        assertEquals(ex.getMessage(), "Contém filme nulo");
    }

    @Test
    public void alugarFilmeFilmeNomeInvalidoException() {
        filmes.get(0).setNome("");

        //Resultado
        Throwable ex = Assert.assertThrows(LocadoraException.class, () -> locacaoService.alugarFilme(usuario, filmes));

        assertEquals(ex.getMessage(), "Contém filme com nome invalido");
    }

    @Test
    public void alugarFilmeFilmeNomeValorException() {
        filmes.get(0).setPrecoLocacao(0.0);

        //Resultado
        Throwable ex = Assert.assertThrows(LocadoraException.class, () -> locacaoService.alugarFilme(usuario, filmes));

        assertEquals(ex.getMessage(), "Contém filme com valor invalido");
    }
}
