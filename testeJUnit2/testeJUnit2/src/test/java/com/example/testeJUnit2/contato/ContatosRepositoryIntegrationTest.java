package com.example.testeJUnit2.contato;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.testeJUnit2.model.ContatoModel;
import com.example.testeJUnit2.repository.ContatoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest //injeta todas as dependencias necessarias para subir a classe de test
public class ContatosRepositoryIntegrationTest {

	@Autowired
	private ContatoRepository contatoRepository;

	@Before
	public void start() {
		ContatoModel contato = new ContatoModel("Chefe", "0y", "9xxxxxxx9");
		if (contatoRepository.findFirstByNome(contato.getNome()) == null)
			contatoRepository.save(contato);

		contato = new ContatoModel("Novo Chefe", "0y", "8xxxxxxx8");
		if (contatoRepository.findFirstByNome(contato.getNome()) == null)
			contatoRepository.save(contato);

		contato = new ContatoModel("chefe Mais Antigo", "0y", "7xxxxxxx7");
		if (contatoRepository.findFirstByNome(contato.getNome()) == null)
			contatoRepository.save(contato);

		contato = new ContatoModel("Amigo", "0z", "5xxxxxxx5");
		if (contatoRepository.findFirstByNome(contato.getNome()) == null)
			contatoRepository.save(contato);
	}

	@Test
	public void findByNomeRetornaContato() throws Exception { //testa para ver se o método está retornando o dado certo

		ContatoModel contato = contatoRepository.findFirstByNome("Chefe");

		Assert.assertTrue(contato.getNome().equals("Chefe")); //retorna a validação da resposta(chefe)
	}

	@Test
	public void findAllByNomeIgnoreCaseRetornaTresContato() { //validando a quantidade de valores que retornam na lista

		List<ContatoModel> contatos = contatoRepository.findAllByNomeIgnoreCaseContaining("chefe");

		Assert.assertEquals(3, contatos.size()); //validade se o valor adquirido é igual ao valor esperado
	}

	@After
	public void end() { //limpa a base de dados depois da rotina de testes
		contatoRepository.deleteAll();
	}

}
