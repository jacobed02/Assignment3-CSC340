package assignment3.class340;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import assignment3.class340.entity.TvCharacter;
import assignment3.class340.repository.TvCharacterRepository;
import assignment3.class340.service.TvCharacterService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

class TvCharacterServiceTests {

    private TvCharacterRepository repository;
    private TvCharacterService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TvCharacterRepository.class);
        service = new TvCharacterService(repository);
    }

    @Test
    void searchesByNameAndUniverseWhenBothFiltersAreProvided() {
        TvCharacter expected = new TvCharacter("Spider-Man", "Hero", "Marvel", "Human");
        when(repository.findByNameContainingIgnoreCaseAndUniverseIgnoreCase("spider", "Marvel"))
                .thenReturn(List.of(expected));

        List<TvCharacter> results = service.findCharacters(" spider ", " Marvel ");

        assertEquals(List.of(expected), results);
        verify(repository).findByNameContainingIgnoreCaseAndUniverseIgnoreCase("spider", "Marvel");
    }

    @Test
    void missingCharacterProducesNotFound() {
        when(repository.findById(42L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> service.findById(42L));

        assertEquals(404, exception.getStatusCode().value());
    }

    @Test
    void createDiscardsClientSuppliedId() {
        TvCharacter input = new TvCharacter("Spider-Man", "Hero", "Marvel", "Human");
        input.setCharacterId(99L);
        when(repository.save(input)).thenAnswer(invocation -> invocation.getArgument(0));

        TvCharacter created = service.create(input);

        assertNull(created.getCharacterId());
        verify(repository).save(input);
    }
}
