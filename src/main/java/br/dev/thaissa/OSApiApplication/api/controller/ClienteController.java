
package br.dev.thaissa.OSApiApplication.api.controller;

import br.dev.thaissa.OSApiApplication.domain.mode.Cliente;
import br.dev.thaissa.OSApiApplication.domain.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    /**
     * Lista todos os clientes
     * @return Lista de Clientes
     */
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
    public Cliente adicionar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
    
    
    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteID,
                                             @RequestBody Cliente cliente) {
        //verifica se o cliente existe
        if (!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteID);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
}
