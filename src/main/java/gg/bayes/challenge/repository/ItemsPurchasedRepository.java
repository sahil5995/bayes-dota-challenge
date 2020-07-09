package gg.bayes.challenge.repository;


import gg.bayes.challenge.repository.entity.HeroItemsPurchased;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsPurchasedRepository extends CrudRepository<HeroItemsPurchased, Long> {

    List<HeroItemsPurchased> findAllByHeroAndMatchId(String heroName, Long matchId);
}
