package br.org.soujava.servicos;

import static br.org.soujava.utils.DataUtils.isMesmaData;
import static br.org.soujava.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import br.org.soujava.entidades.Filme;
import br.org.soujava.entidades.Locacao;
import br.org.soujava.entidades.Usuario;

public class LocacaoServiceTest {
	@Test
	public void teste() {
		
		// cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);
		
		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		// validação
		assertThat(locacao.getValor(), is(5.0));
		assertThat(locacao.getValor(), is(not(6.0)));
		assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
		
	}
}
