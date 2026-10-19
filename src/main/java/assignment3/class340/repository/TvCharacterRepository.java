package assignment3.class340.repository;

import assignment3.class340.entity.TvCharacter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvCharacterRepository extends JpaRepository<TvCharacter, Long> {

    List<TvCharacter> findByUniverseIgnoreCase(String universe);

    List<TvCharacter> findByNameContainingIgnoreCase(String name);

    List<TvCharacter> findByNameContainingIgnoreCaseAndUniverseIgnoreCase(String name, String universe);
}
