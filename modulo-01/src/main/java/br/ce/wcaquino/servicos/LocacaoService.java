package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.LocadoraException;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {
		validacao(usuario, filmes);

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		verificaDesconto(filmes);

		locacao.setValor(filmes.stream().mapToDouble(Filme::getPrecoLocacao).sum());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public void verificaDesconto(List<Filme> filmes) {
		for(int i = 0; i < filmes.size(); i++) {
			Filme filme = filmes.get(i);

			switch (i) {
				case 2 -> filme.setPrecoLocacao(filme.getPrecoLocacao() * 0.75);
				case 3 -> filme.setPrecoLocacao(filme.getPrecoLocacao() * 0.5);
				case 4 -> filme.setPrecoLocacao(filme.getPrecoLocacao() * 0.25);
				case 5 -> filme.setPrecoLocacao(0.0);
				default -> { }
			}
		}

	}

	private void validacao(Usuario usuario, List<Filme> filmes) throws Exception {
		if(filmes == null || filmes.size() == 0)
			throw new LocadoraException("Lista de filmes vazia");

		if(filmes.stream().anyMatch(filme -> filme == null))
			throw new LocadoraException("Contém filme nulo");

		if(filmes.stream().anyMatch(filme -> filme == null || filme.getNome() == null || filme.getNome().equals("")))
			throw new LocadoraException("Contém filme com nome invalido");

		if(filmes.stream().anyMatch(filme -> filme == null || filme.getPrecoLocacao() == null || filme.getPrecoLocacao() <= 0))
			throw new LocadoraException("Contém filme com valor invalido");

		if(usuario == null)
			throw new LocadoraException("Usuario vazio");

		if(filmes.stream().anyMatch(filme -> filme.getEstoque().equals(0)))
			throw new LocadoraException("Filme sem estoque");
	}
}