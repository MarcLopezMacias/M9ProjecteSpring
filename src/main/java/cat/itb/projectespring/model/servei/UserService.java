package cat.itb.projectespring.model.servei;


import cat.itb.projectespring.model.entitat.Usuari;
import cat.itb.projectespring.model.repositori.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static cat.itb.projectespring.Constants.*;
import static cat.itb.projectespring.controlador.Controlador.userRepo;

@Service
public class UserService {

    public void afegir(Usuari e) {
        e.setPassword(passwordEncoder(e.getPassword()));
        userRepo.save(e);
    }

    public Iterable<Usuari> llistatUsuaris() {
        return userRepo.findAll();
    }

    @PostConstruct
    public void init() {
        Usuari notAdmin = new Usuari("notAdmin", passwordEncoder(PASSWORD), PASSWORD);
        Usuari admin = new Usuari(ADMIN, passwordEncoder(ADMIN), ADMIN, ADMIN);


        userRepo.save(notAdmin);
        userRepo.save(admin);

    }

    public Usuari findById(String username){
        return userRepo.findById(username).orElse(null);
    }


    public String passwordEncoder(String s) {
        return new BCryptPasswordEncoder().encode(s);
    }



}
