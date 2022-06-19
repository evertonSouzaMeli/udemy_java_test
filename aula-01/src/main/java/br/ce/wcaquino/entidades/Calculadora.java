package br.ce.wcaquino.entidades;

public class Calculadora {

    public Integer soma(Integer a, Integer b) {
        return a + b;
    }

    public Integer subtracao(Integer a, Integer b) {
        return a - b;
    }

    public Integer divisao(Integer a, Integer b) {
        if(b == 0) { throw new ArithmeticException("Não é possivel dividir por zero"); }
        return a / b;
    }
}
