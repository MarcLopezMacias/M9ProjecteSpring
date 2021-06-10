package cat.itb.projectespring.model.repositori;

import cat.itb.projectespring.model.entitat.Usuari;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Usuari, String> {

}
