package br.com.poc.embedded.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.poc.embedded.PocEmbeddedApplicationTests;
import br.com.poc.embedded.repository.model.Cep;
import br.com.poc.embedded.repository.model.Documento;
import br.com.poc.embedded.repository.model.Endereco;
import br.com.poc.embedded.repository.model.Pessoa;

public class PessoaServiceImplTest extends PocEmbeddedApplicationTests {

	@Autowired
	private PessoaService pessoaService;

	@Test
	public void testSalvaPessoa() {

		pessoaService.limpaTabela();

		pessoaService.salvarPessoa(criaPessoaAlessandro());

		Pessoa pessoa = pessoaService.buscaPorNome("Alessandro").orElseThrow(RuntimeException::new);

		SoftAssertions sa = new SoftAssertions();

		sa.assertThat(pessoa.getNome()).isEqualTo("Alessandro");
		sa.assertThat(pessoa.getDataNascimento()).isEqualTo(LocalDate.of(1996, Month.OCTOBER, 25));

		sa.assertThat(pessoa.getDocumento().getCpf()).isEqualTo("866.708.764-59");
		sa.assertThat(pessoa.getDocumento().getRg()).isEqualTo("39.323.092-2");
		sa.assertThat(pessoa.getDocumento().getDataEmissao()).isEqualTo(LocalDate.of(2010, Month.MARCH, 11));

		sa.assertThat(pessoa.getEndereco().getComplemento()).isEqualTo("Apto. 734");
		sa.assertThat(pessoa.getEndereco().getNumero()).isEqualTo(635);

		sa.assertThat(pessoa.getEndereco().getCep().getBairro()).isEqualTo("Guaianases");
		sa.assertThat(pessoa.getEndereco().getCep().getLogradouro()).isEqualTo("Rua Cruz do Espirito Santo");
		sa.assertThat(pessoa.getEndereco().getCep().getCodigo()).isEqualTo("08440-470");
		sa.assertThat(pessoa.getEndereco().getCep().getEstado()).isEqualTo("SP");

		sa.assertAll();
	}

	@Test
	public void testFindByCep() {

		pessoaService.limpaTabela();

		pessoaService.salvarPessoa(criaPessoaAlessandro());
		pessoaService.salvarPessoa(criaPessoaMarcos());
		pessoaService.salvarPessoa(criaPessoaLeo());

		List<Pessoa> listaPessoas = pessoaService.buscaPorCepOrdenadorPorIdadeDescrecente("084");

		Pessoa marcos = listaPessoas.stream().findFirst().orElseThrow(RuntimeException::new);

		Pessoa leo = listaPessoas.stream().skip(listaPessoas.size() - 1).findFirst().orElseThrow(RuntimeException::new);

		SoftAssertions sa = new SoftAssertions();

		sa.assertThat(marcos.getNome()).isEqualTo("Marcos");
		sa.assertThat(marcos.getDataNascimento()).isEqualTo(LocalDate.of(1984, Month.DECEMBER, 11));
		sa.assertThat(marcos.getDocumento().getCpf()).isEqualTo("675.481.515-94");
		sa.assertThat(marcos.getDocumento().getRg()).isEqualTo("09.546.084-3");
		sa.assertThat(marcos.getEndereco().getCep().getCodigo()).isEqualTo("08403-200");

		sa.assertThat(leo.getNome()).isEqualTo("Leo");
		sa.assertThat(leo.getDataNascimento()).isEqualTo(LocalDate.of(1998, Month.JANUARY, 22));
		sa.assertThat(leo.getDocumento().getCpf()).isEqualTo("211.180.030-98");
		sa.assertThat(leo.getDocumento().getRg()).isEqualTo("08.516.394-X");
		sa.assertThat(leo.getEndereco().getCep().getCodigo()).isEqualTo("08485-065");

		sa.assertAll();
	}

	private Pessoa criaPessoaAlessandro() {

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Alessandro");
		pessoa.setDataNascimento(LocalDate.of(1996, Month.OCTOBER, 25));

		Documento doc = new Documento();
		doc.setCpf("866.708.764-59");
		doc.setRg("39.323.092-2");
		doc.setDataEmissao(LocalDate.of(2010, Month.MARCH, 11));

		Cep cep = new Cep();
		cep.setBairro("Guaianases");
		cep.setLogradouro("Rua Cruz do Espirito Santo");
		cep.setCodigo("08440-470");
		cep.setEstado("SP");

		Endereco end = new Endereco();
		end.setNumero(635);
		end.setComplemento("Apto. 734");
		end.setCep(cep);

		pessoa.setDocumento(doc);
		pessoa.setEndereco(end);

		return pessoa;
	}

	private Pessoa criaPessoaMarcos() {

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Marcos");
		pessoa.setDataNascimento(LocalDate.of(1984, Month.DECEMBER, 11));

		Documento doc = new Documento();
		doc.setCpf("675.481.515-94");
		doc.setRg("09.546.084-3");
		doc.setDataEmissao(LocalDate.of(2015, Month.MAY, 11));

		Cep cep = new Cep();
		cep.setBairro("Mooca");
		cep.setLogradouro("Rua da Mooca");
		cep.setCodigo("08403-200");
		cep.setEstado("SP");

		Endereco end = new Endereco();
		end.setNumero(2560);
		end.setCep(cep);

		pessoa.setDocumento(doc);
		pessoa.setEndereco(end);

		return pessoa;
	}

	private Pessoa criaPessoaLeo() {

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Leo");
		pessoa.setDataNascimento(LocalDate.of(1998, Month.JANUARY, 22));

		Documento doc = new Documento();
		doc.setCpf("211.180.030-98");
		doc.setRg("08.516.394-X");
		doc.setDataEmissao(LocalDate.of(2013, Month.JUNE, 20));

		Cep cep = new Cep();
		cep.setBairro("Itaquera");
		cep.setLogradouro("Avenida Itaquera");
		cep.setCodigo("08485-065");
		cep.setEstado("SP");

		Endereco end = new Endereco();
		end.setNumero(5555);
		end.setCep(cep);

		pessoa.setDocumento(doc);
		pessoa.setEndereco(end);

		return pessoa;
	}

}