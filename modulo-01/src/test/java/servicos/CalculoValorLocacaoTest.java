package servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.*;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
    public LocacaoService locacaoService;

    @Parameter
    public List<Filme> filmes;

    @Parameter(value = 1)
    public Double valorLocacao;

    @Before
    public void init(){
        this.locacaoService = new LocacaoService();
        this.filmes = new ArrayList();
    }

    private static Filme filme1 = new Filme("Filme 1", 1, 5.0);
    private static Filme filme2 = new Filme("Filme 2", 3, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 2, 8.0);
    private static Filme filme4 = new Filme("Filme 4", 1, 8.0);

    @Parameters
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][]{
                { Arrays.asList(filme1, filme2, filme3), 15.0 },
                { Arrays.asList(filme1, filme2, filme3, filme4), 19.0},
        });
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos(){
        System.out.println(valorLocacao);
    }
}
