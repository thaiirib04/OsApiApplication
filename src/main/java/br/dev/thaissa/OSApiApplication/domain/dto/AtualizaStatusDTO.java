package br.dev.thaissa.OSApiApplication.domain.dto;

import br.dev.thaissa.OSApiApplication.domain.model.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author sesi3dib
 */
public record AtualizaStatusDTO(
        @NotNull(message = "Status é obrigtório")
        StatusOrdemServico status
        
){}
