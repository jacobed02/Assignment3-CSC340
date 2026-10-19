package assignment3.class340.controller;

import assignment3.class340.entity.TvCharacter;
import assignment3.class340.service.TvCharacterService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class TvCharacterController {

    private final TvCharacterService service;

    public TvCharacterController(TvCharacterService service) {
        this.service = service;
    }

    @GetMapping
    public List<TvCharacter> getCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String universe) {
        return service.findCharacters(name, universe);
    }

    @GetMapping("/{id}")
    public TvCharacter getCharacter(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<TvCharacter> createCharacter(@Valid @RequestBody TvCharacter character) {
        TvCharacter created = service.create(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public TvCharacter updateCharacter(
            @PathVariable Long id,
            @Valid @RequestBody TvCharacter character) {
        return service.update(id, character);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
