package cat.itb.projectespring.controlador;

import cat.itb.projectespring.model.entitat.Patinet;
import cat.itb.projectespring.model.entitat.Usuari;
import cat.itb.projectespring.model.repositori.UserRepo;
import cat.itb.projectespring.model.servei.PatinetService;
import cat.itb.projectespring.model.servei.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import static cat.itb.projectespring.Constants.*;

@Controller
public class Controlador {

    public static UserRepo userRepo;

    public Controlador(UserRepo repo) {
        this.userRepo = repo;
    }

    String nom;
    @Autowired
    private UserService userService;
    @Autowired
    private PatinetService patinetService;

    @GetMapping("/")
    public String inici(Model model) {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @PostMapping("/registration")
    public String afegirUsuari(@ModelAttribute("usuari") Usuari usuari) {
        usuari.setRol(USER);
        if (usuari.getPassword().equals(usuari.getMatchingPassword())) {
            userService.afegir(usuari);
            return "redirect:/" + LLISTAT_PATINETS;
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/" + LLISTAT_PATINETS)
    public String llistatPatinets(Model model) {
        model.addAttribute(LLISTAT_PATINETS, patinetService.llistatPatinets());
        model.addAttribute(PATINET, new Patinet());
        return LLISTAT_PATINETS;
    }

    @GetMapping("/" + LLISTAT_USUARIS)
    public String llistatUsuaris(Model model) {
        model.addAttribute(LLISTAT_USUARIS, userService.llistatUsuaris());
        return LLISTAT_USUARIS;
    }

    @RequestMapping("/afegir")
    public String afegir(Model model) {
        model.addAttribute(PATINET, new Patinet());
        return "afegirPatinet";
    }

    @PostMapping("/afegirPatinet")
    public String afegirPatinet(@ModelAttribute(PATINET) Patinet patinet) {
        patinetService.afegir(patinet);
        return "redirect:/" + LLISTAT_PATINETS;
    }

    @GetMapping("/registration")
    public String mostrarFormulariRegistre(WebRequest request, Model model) {
        model.addAttribute("usuari", new Usuari());
        return "registre";
    }

    @RequestMapping(value = "/update/{name}", method = RequestMethod.POST)
    public String actualitzarPatinet(@PathVariable("name") String nom, Model model) {
        this.nom = nom;
        model.addAttribute(PATINET, patinetService.consultarPerNom(nom));
        return "/actualitzarPatinet";
    }

    @PostMapping("/actualitzarPatinet")
    public String actualitzarPatinet(@ModelAttribute(PATINET) Patinet patinet) {
        patinetService.actualitzarPatinet(patinet, nom);
        return "redirect:/" + LLISTAT_PATINETS;

    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.POST)
    public String eliminarPatinetPerNom(@PathVariable("name") String nom) {
        patinetService.eliminarPerNom(nom);
        return "redirect:/" + LLISTAT_PATINETS;
    }

}




