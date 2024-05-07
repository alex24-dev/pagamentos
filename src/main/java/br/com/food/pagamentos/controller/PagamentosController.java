package br.com.food.pagamentos.controller;

import br.com.food.pagamentos.dto.PagamentoDto;
import br.com.food.pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/pagamentos")
public class PagamentosController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public Page<PagamentoDto> listar(@PageableDefault(size = 10 ) Pageable paginacao ){

        return service.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> detalhar(@PathVariable @NotNull long id){

        PagamentoDto dto = service.obterPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar(@RequestBody @Valid PagamentoDto dto, UriComponentsBuilder uriComponentsBuilder){

        PagamentoDto pagamentoDto = service.criarPagamento(dto);
        URI endereco = uriComponentsBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoDto.getId()).toUri();
        return  ResponseEntity.created(endereco).body(pagamentoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDto dto){

        PagamentoDto atualizado = service.atualizarPagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> remover(@PathVariable @Valid Long id){

        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

}
