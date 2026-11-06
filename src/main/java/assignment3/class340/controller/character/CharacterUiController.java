package assignment3.class340.controller.character;

import assignment3.class340.entity.TvCharacter;
import assignment3.class340.service.TvCharacterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CharacterUiController {

    private final TvCharacterService service;

    public CharacterUiController(TvCharacterService service) {
        this.service = service;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/characters/new")
    public String newCharacter(Model model) {
        prepareForm(model, new TvCharacter(), false, List.of());
        return "character-form";
    }

    @PostMapping("/characters")
    public String createCharacter(
            @Valid @ModelAttribute("character") TvCharacter character,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, character, false,
                    bindingResult.getFieldErrors().stream()
                            .map(error -> error.getDefaultMessage())
                            .toList());
            return "character-form";
        }

        TvCharacter created = service.create(character);
        redirectAttributes.addFlashAttribute("message", created.getName() + " was added.");
        return "redirect:/";
    }

    @GetMapping("/characters/{id}")
    public String details(@PathVariable long id, Model model) {
        TvCharacter character = service.findById(id);
        model.addAttribute("character", character);
        return "details";
    }

    @GetMapping("/characters/{id}/edit")
    public String editCharacter(@PathVariable long id, Model model) {
        prepareForm(model, service.findById(id), true, List.of());
        return "character-form";
    }

    @PostMapping("/characters/{id}")
    public String updateCharacter(
            @PathVariable long id,
            @Valid @ModelAttribute("character") TvCharacter character,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            service.findById(id);
            character.setCharacterId(id);
            prepareForm(model, character, true,
                    bindingResult.getFieldErrors().stream()
                            .map(error -> error.getDefaultMessage())
                            .toList());
            return "character-form";
        }

        TvCharacter updated = service.update(id, character);
        redirectAttributes.addFlashAttribute("message", updated.getName() + " was updated.");
        return "redirect:/characters/" + id;
    }

    @PostMapping("/characters/{id}/delete")
    public String deleteCharacter(
            @PathVariable long id,
            RedirectAttributes redirectAttributes) {
        TvCharacter character = service.findById(id);
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", character.getName() + " was removed.");
        return "redirect:/";
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String handleNotFound(ResponseStatusException exception, Model model,
            HttpServletResponse response) {
        response.setStatus(exception.getStatusCode().value());
        model.addAttribute("errorMessage", "We couldn't find that character.");
        return "error";
    }

    private void prepareForm(Model model, TvCharacter character, boolean editing, List<String> errors) {
        model.addAttribute("character", character);
        model.addAttribute("editing", editing);
        model.addAttribute("formAction", editing
                ? "/characters/" + character.getCharacterId()
                : "/characters");
        model.addAttribute("errors", errors);
    }
}
