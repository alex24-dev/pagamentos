package br.com.food.pagamentos.dto;

import br.com.food.pagamentos.model.Status;
import lombok.Getter;;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDto {

    private Long id;
    private BigDecimal valor;
    private String nome;
    private  String numero;
    private String codigo;
    private Status status;
    private Long pedidoId;
    private Long formaDePagamentoId;
}
