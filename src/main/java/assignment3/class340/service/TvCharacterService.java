package assignment3.class340.service;

import assignment3.class340.entity.TvCharacter;
import assignment3.class340.repository.TvCharacterRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TvCharacterService {

    private final TvCharacterRepository repository;

    public TvCharacterService(TvCharacterRepository repository) {
        this.repository = repository;
    }

    public List<TvCharacter> findCharacters(String name, String universe) {
        String normalizedName = normalize(name);
        String normalizedUniverse = normalize(universe);

        if (normalizedName != null && normalizedUniverse != null) {
            return repository.findByNameContainingIgnoreCaseAndUniverseIgnoreCase(
                    normalizedName, normalizedUniverse);
        }
        if (normalizedName != null) {
            return repository.findByNameContainingIgnoreCase(normalizedName);
        }
        if (normalizedUniverse != null) {
            return repository.findByUniverseIgnoreCase(normalizedUniverse);
        }
        return repository.findAll();
    }

    public TvCharacter findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found"));
    }

    public TvCharacter create(TvCharacter character) {
        character.setCharacterId(null);
        return repository.save(character);
    }

    public TvCharacter update(Long id, TvCharacter updatedCharacter) {
        TvCharacter character = findById(id);
        character.setName(updatedCharacter.getName());
        character.setDescription(updatedCharacter.getDescription());
        character.setUniverse(updatedCharacter.getUniverse());
        character.setSpecies(updatedCharacter.getSpecies());
        return repository.save(character);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
