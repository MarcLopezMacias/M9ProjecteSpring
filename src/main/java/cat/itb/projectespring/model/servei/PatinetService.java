package cat.itb.projectespring.model.servei;

import cat.itb.projectespring.model.entitat.Patinet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PatinetService {

    private List<Patinet> repositori = new ArrayList<>();

    public void afegir(Patinet e) {
        repositori.add(e);
    }

    public List<Patinet> llistatPatinets() {
        return repositori;
    }

    @PostConstruct
    public void init() {
        repositori.addAll(
                Arrays.asList(
                        new Patinet("T10", "Janobike"),
                        new Patinet("Mijia M365", "Xiaomi"),
                        new Patinet("Storm", "Dualtron")
                ));
    }


    public Patinet consultarPerNom(String s) {
        Patinet p = null;
        boolean trobat = false;
        for (int i = 0; i < repositori.size() && !trobat; i++) {
            if (s.equals(repositori.get(i).getNomPatinet())) {

                p = repositori.get(i);
                trobat = true;
            }
        }
        return p;
    }

    public void eliminarPerNom(String s) {
        Patinet p = null;
        boolean trobat = false;
        for (int i = 0; i < repositori.size() && !trobat; i++) {
            if (s.equals(repositori.get(i).getNomPatinet())) {
                p = repositori.get(i);
                trobat = true;
            }
        }
        repositori.remove(p);
    }

    public void actualitzarPatinet(Patinet e, String nom) {
        Patinet p = null;
        boolean trobat = false;
        for (int i = 0; i < repositori.size() && !trobat; i++) {
            if (nom.equals(repositori.get(i).getNomPatinet())) {
                repositori.get(i).setNomPatinet(e.getNomPatinet());
                repositori.get(i).setMarcaPatinet(e.getMarcaPatinet());
            }
        }

    }


}
