package gg.bayes.challenge.repository;

import gg.bayes.challenge.dto.HeroSpellsDTO;
import gg.bayes.challenge.repository.entity.HeroSpells;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HeroSpellsRepository extends CrudRepository<HeroSpells, Long> {

    @Query("SELECT new gg.bayes.challenge.dto.HeroSpellsDTO(hs.spell, COUNT(hs)) " +
            "FROM HeroSpells as hs GROUP BY hs.matchId, hs.hero, hs.spell HAVING hs.hero = :heroName AND hs.matchId = :matchId")
    List<HeroSpellsDTO> getSpells(@Param("matchId") Long matchId, @Param("heroName") String heroName);
}
