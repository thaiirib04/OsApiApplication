package br.dev.thaissa.OSApiApplication.domain.service;

import br.dev.thaissa.OSApiApplication.domain.exception.DomainException;
import br.dev.thaissa.OSApiApplication.domain.mode.Cliente;
import br.dev.thaissa.OSApiApplication.domain.model.OrdemServico;
import br.dev.thaissa.OSApiApplication.domain.model.StatusOrdemServico;
import br.dev.thaissa.OSApiApplication.domain.repository.ClienteRepository;
import br.dev.thaissa.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public OrdemServico criar(OrdemServico ordemServico){
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
    
    public OrdemServico salvar(OrdemServico ordemServico){
        
        OrdemServico ordemAtual = ordemServicoRepository.findById(ordemServico.getId())
                .orElseThrow(()-> new RuntimeException("Ordem não encontrada"));
        
        Long clienteId = ordemServico.getCliente().getId();
        
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        ordemAtual.setCliente(cliente);
        ordemAtual.setDescricao(ordemServico.getDescricao());
        ordemAtual.setPreco(ordemServico.getPreco());
        
        return ordemServicoRepository.save(ordemAtual);
        
    }
    
    public void excluir(Long id){
        ordemServicoRepository.deleteById(id);
    }
    
    public List<OrdemServico> listarPorCliente(Long clienteId){
        return ordemServicoRepository.findByClienteId(clienteId);
    }
    
    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status){
        
        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);
        
        if (optOrdemServico.isPresent()){
            OrdemServico ordemServico = optOrdemServico.get();
            
            if(ordemServico.getStatus()==StatusOrdemServico.ABERTA
                    && status != StatusOrdemServico.ABERTA){
                
                ordemServico.setStatus(status);
                ordemServico.setDataFinalizacao(LocalDateTime.now());
                ordemServicoRepository.save(ordemServico);
                return Optional.of(ordemServico);
            } else {
                return Optional.empty();
            }
        } else {
            throw new DomainException("Não existe OS com o id" + ordemServicoID);
        }
    }
    
}
