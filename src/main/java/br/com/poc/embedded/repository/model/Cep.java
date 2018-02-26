package br.com.poc.embedded.repository.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Cep {

	private String codigo;

	private String logradouro;

	private String bairro;

	private String estado;

}