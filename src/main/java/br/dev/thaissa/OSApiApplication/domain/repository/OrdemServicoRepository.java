/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.dev.thaissa.OSApiApplication.domain.repository;

import br.dev.thaissa.OSApiApplication.domain.mode.Cliente;
import br.dev.thaissa.OSApiApplication.domain.model.OrdemServico;
import br.dev.thaissa.OSApiApplication.domain.model.StatusOrdemServico;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sesi3dib
 */
    public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
        
        List<OrdemServico> findByDataAbertura(LocalDateTime dataAbertura);
        List<OrdemServico> findByDataFinalizacao (LocalDateTime dataFinalizacao);
        List<OrdemServico> findByStatus (StatusOrdemServico status);
        List<OrdemServico> findByClienteId (Long clienteId);
        
        
    
}
