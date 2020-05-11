package br.org.soujava.servicos;

import static br.org.soujava.utils.DataUtils.isMesmaData;
import static br.org.soujava.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.org.soujava.entidades.Filme;
import br.org.soujava.entidades.Locacao;
import br.org.soujava.entidades.Usuario;

public class LocacaoServiceTest {
	
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void testeLocacao() throws Exception {
		
		// cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);
		
		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		// validação
		error.checkThat(locacao.getValor(), is(5.0));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
		
	}
	
	@Test(expected = Exception.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		
		// cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);
		
	}

	@Test
	public void testeLocacao_filmeSemEstoque_robusto() {
		
		// cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		
		// ação
		try {
			Locacao locacao = service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lançado uma exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
		}
		
	}
	
}
