package gg.bayes.challenge.repository;


import gg.bayes.challenge.dto.HeroKillsDTO;
import gg.bayes.challenge.repository.entity.HeroKills;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroKillsRepository extends CrudRepository<HeroKills, Long> {

    @Query("SELECT new gg.bayes.challenge.dto.HeroKillsDTO(hk.hero, COUNT(hk)) " +
            "FROM HeroKills hk GROUP BY hk.hero, hk.matchId HAVING hk.matchId = :matchId")
    List<HeroKillsDTO> getKills(@Param("matchId") Long matchId);
}
