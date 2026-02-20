
package br.dev.thaissa.OSApiApplication.api.controller;

import br.dev.thaissa.OSApiApplication.domain.mode.Cliente;
import br.dev.thaissa.OSApiApplication.domain.repository.ClienteRepository;
import br.dev.thaissa.OSApiApplication.domain.service.ClienteService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/clientes")
    public List<Cliente> listas() {
         return clienteRepository.findAll();
//        return clienteRepository.findByNome("KGe");
        // return clienteRepository.findByNomeContaining("Silva");
    }
    
    
    /**
     * Busca por Cliente ID
     * @param clienteID
     * @return Cliente ou NotFound
     */    
    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID){
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        
        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
    
    
    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar( @Valid @PathVariable Long clienteID,
                                             @RequestBody Cliente cliente) {
        //verifica se o cliente existe
        if (!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteID);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
    
    
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID){
        //verifica se cliente existe ou não
        
        if(!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }
        
        clienteRepository.deleteById(clienteID);
        return ResponseEntity.noContent().build();
    }
}
