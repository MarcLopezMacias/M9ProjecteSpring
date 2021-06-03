package cat.itb.projectespring.controlador;

import cat.itb.projectespring.model.Patinet;
import cat.itb.projectespring.model.Usuari;
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

    String nom;
    @Autowired
    private UserService userService;
    @Autowired
    private PatinetService patinetService;

    @GetMapping("/")
    public String inici(Model model) {
        model.addAttribute(LLISTAT_PATINETS, patinetService.llistatPatinets());
        model.addAttribute(PATINET, new Patinet());
        return "home";
    }

    @GetMapping("/list")
    public String iniciRegistre(Model model) {
        model.addAttribute(LLISTAT_PATINETS, patinetService.llistatPatinets());
        model.addAttribute(PATINET, new Patinet());
        return "home";
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.POST)
    public String eliminarPatinetPerNom(@PathVariable("name") String nom) {
        patinetService.eliminarPerNom(nom);
        return "redirect:/";
    }

    @GetMapping("/userList")
    public String llistatUsuaris(Model model) {
        model.addAttribute(LLISTAT_USUARIS, userService.llistatUsuaris());
        return LLISTAT_USUARIS;
    }

    @GetMapping("/home")
    public String llistatPatinets(Model model) {
        model.addAttribute(LLISTAT_PATINETS, patinetService.llistatPatinets());
        model.addAttribute(PATINET, new Patinet());
        return "home";
    }

    @GetMapping("/registration")
    public String mostrarFormulariRegistre(WebRequest request, Model model) {
        model.addAttribute("usuari", new Usuari());
        return "registre";
    }

    @RequestMapping("/afegir")
    public String afegirPatinet(Model model) {
        model.addAttribute(PATINET, new Patinet());
        return "afegirPatinet";
    }

    @PostMapping("/afegirPatinet")
    public String afegirPatinet(@ModelAttribute(PATINET) Patinet patinet) {
        patinetService.afegir(patinet);
        return "redirect:/";
    }

    @PostMapping("/registration")
    public String afegirUsuari(@ModelAttribute("usuari") Usuari usuari) {
        usuari.setRol(USER);
        userService.afegir(usuari);
        return "redirect:/list";
    }

    @RequestMapping(value = "/update/{name}", method = RequestMethod.POST)
    public String actualitzarPatinet(@PathVariable("name") String nom, Model model) {
        this.nom = nom;
        model.addAttribute(PATINET, patinetService.consultarPerNom(nom));
        return "/actualitzarPatinet";
    }

    @PostMapping("/actualitzarPatinet")
    public String actualitzarPatinetPost(@ModelAttribute(PATINET) Patinet patinet) {
        patinetService.actualitzarPatinet(patinet, nom);
        return "redirect:/";

    }


}




