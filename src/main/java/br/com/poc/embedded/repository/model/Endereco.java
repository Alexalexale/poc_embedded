package br.com.poc.embedded.repository.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

	@Embedded
	private Cep cep;

	private Integer numero;

	private String complemento;

}