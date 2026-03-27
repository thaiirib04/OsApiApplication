package br.dev.thaissa.OSApiApplication.domain.repository;

import br.dev.thaissa.OSApiApplication.domain.model.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository <Comentario, Long> {
    
    List<Comentario> FindByDescricaoContaining (String descricao);
}
