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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(path = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {
	
	
	private PessoaService pessoaService;
	
	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@ApiOperation("Listar todas as pessoas")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de pessoas"),
		    @ApiResponse(code = 500, message = "Foi gerado um erro ao listar todas as pessoas")
		})
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaService.listar();
	}
	
	@ApiOperation("Listar pessoa por ID")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de pessoa por ID"),
		    @ApiResponse(code = 500, message = "Foi gerado um erro ao listar pessoa")
		})
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(pessoaService.getPessoa(id));
	}
	
	@ApiOperation("Cadastrar pessoas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cria uma pessoa"),
			@ApiResponse(code = 500, message = "Foi gerado um erro ao criar pessoa")
	})
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
		Pessoa p = pessoaService.salvarPessoa(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	}
	
	@ApiOperation("Atualizar pessoa por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza uma pessoa"),
			@ApiResponse(code = 500, message = "Foi gerado um erro ao atualizar pessoa")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoa));
	}
	
	@ApiOperation("Deletar pessoa por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Deleta uma pessoa"),
			@ApiResponse(code = 500, message = "Foi gerado um erro ao deletar pessoa")
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		pessoaService.deletarPessoa(id);
	}

}
