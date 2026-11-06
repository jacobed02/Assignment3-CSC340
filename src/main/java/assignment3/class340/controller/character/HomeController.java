package assignment3.class340.controller.character;

import assignment3.class340.service.TvCharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final TvCharacterService service;

    public HomeController(TvCharacterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String universe,
            Model model) {
        model.addAttribute("characters", service.findCharacters(name, universe));
        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("universe", universe == null ? "" : universe);
        return "index";
    }
}
