package br.com.pessoacontato.pwc.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pessoacontato.pwc.domain.model.Pessoa;
import br.com.pessoacontato.pwc.domain.service.PessoaService;

@RestController
@RequestMapping(path = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {
	
	
	private PessoaService pessoaService;
	
	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(pessoaService.getPessoa(id));
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
		Pessoa p = pessoaService.salvarPessoa(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoa));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		pessoaService.deletarPessoa(id);
	}

}
