package cat.itb.projectespring.model.servei;


import cat.itb.projectespring.model.Usuari;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cat.itb.projectespring.Constants.*;

@Service
public class UserService {

    private List<Usuari> repositori = new ArrayList<>();

    public void afegir(Usuari e) {
        e.setPassword(passwordEncoder(e.getPassword()));
        repositori.add(e);
    }

    public List<Usuari> llistatUsuaris() {
        return repositori;
    }

    @PostConstruct
    public void init() {
        repositori.addAll(
                Arrays.asList(
                        new Usuari(USER, passwordEncoder(PASSWORD), PASSWORD),
                        new Usuari("notAdmin", passwordEncoder(PASSWORD), PASSWORD),
                        new Usuari(ADMIN, passwordEncoder(ADMIN), ADMIN, ADMIN)
                ));
    }

    public Usuari consultarPerId(String id) {
        Usuari u = null;
        boolean trobat = false;
        for (int i = 0; i < repositori.size() && !trobat; i++) {
            if (id.equals(repositori.get(i).getUsername())) {
                u = repositori.get(i);
                trobat = true;
            }
        }
        return u;
    }

    public String passwordEncoder(String s) {
        return new BCryptPasswordEncoder().encode(s);
    }



}
