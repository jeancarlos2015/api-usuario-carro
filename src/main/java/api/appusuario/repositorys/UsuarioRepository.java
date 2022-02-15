package api.appusuario.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.appusuario.models.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
}
