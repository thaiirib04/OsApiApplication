package br.dev.thaissa.OSApiApplication.api.controller;

import br.dev.thaissa.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.dev.thaissa.OSApiApplication.domain.model.OrdemServico;
import br.dev.thaissa.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.dev.thaissa.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico){
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping("/listar")
    public List<OrdemServico> listas() {
        return ordemServicoRepository.findAll();
    }
    
    @GetMapping("/listar/{id}")
    public ResponseEntity<OrdemServico> buscar (@PathVariable Long id) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);
        if (ordemServico.isPresent()){
            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/por-cliente/{clienteId}")
    public ResponseEntity<List<OrdemServico>> listar(@PathVariable Long clienteId) {
        
        List <OrdemServico> ordensDoUsuario = ordemServicoService.listarPorCliente(clienteId);
        
        if (ordensDoUsuario.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ordensDoUsuario);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizar(@PathVariable Long id,
            @RequestBody OrdemServico ordemServico) {
        //verificar registro
        if (!ordemServicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        ordemServico.setId(id);
        ordemServico = ordemServicoService.salvar(ordemServico);
        return ResponseEntity.ok(ordemServico);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!ordemServicoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        
        ordemServicoService.excluir(id);
        return ResponseEntity.noContent().build();
       
    }
    
    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
            @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO){
        
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
                ordemServicoID,
                statusDTO.status());
        
        if (optOS.isPresent()){
            return ResponseEntity.ok(optOS.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
