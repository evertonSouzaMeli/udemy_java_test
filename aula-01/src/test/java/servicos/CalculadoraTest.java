package servicos;

import br.ce.wcaquino.entidades.Calculadora;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    private Calculadora calculadora;

    @Before
    public void init(){
        this.calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
       Assert.assertEquals(Integer.valueOf(2), calculadora.soma(1, 1));
    }

    @Test
    public void deveSubtrairDoisValores(){
        Assert.assertEquals(Integer.valueOf(0), calculadora.subtracao(1, 1));
    }

    @Test
    public void deveDividirDoisValores()  {
        Assert.assertEquals(Integer.valueOf(1), calculadora.divisao(1, 1));
    }
    @Test
    public void deveDividirDoisValoresException() {
        Throwable ex = Assert.assertThrows(ArithmeticException.class, () -> calculadora.divisao(1, 0));
        Assert.assertEquals(ex.getMessage(), "Não é possivel dividir por zero");
    }
}
