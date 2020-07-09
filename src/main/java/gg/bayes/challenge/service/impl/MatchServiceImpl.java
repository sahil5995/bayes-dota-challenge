package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.dto.HeroDamageDTO;
import gg.bayes.challenge.dto.HeroKillsDTO;
import gg.bayes.challenge.dto.HeroSpellsDTO;
import gg.bayes.challenge.repository.HeroDamageRepository;
import gg.bayes.challenge.repository.ItemsPurchasedRepository;
import gg.bayes.challenge.repository.HeroKillsRepository;
import gg.bayes.challenge.repository.HeroSpellsRepository;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import gg.bayes.challenge.dto.HeroMapper;
import gg.bayes.challenge.strategy.DotaStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final DotaStrategyManager strategyManager;
    private final HeroKillsRepository killsRepository;
    private final ItemsPurchasedRepository itemsPurchasedRepository;
    private final HeroMapper heroMapper;
    private final HeroSpellsRepository spellsRepository;
    private final HeroDamageRepository damageRepository;


    private Long matchId = 0L;

    @Override
    public Long ingestMatch(String payload) {
        matchId++;
        Stream.of(payload.split("[\\r\\n]+")).forEach((k) -> strategyManager.apply(k, matchId));
        return matchId;
    }


    @Override
    public List<HeroKills> getKills(Long matchId) {
        return killsRepository.getKills(matchId).stream()
                .map(HeroKillsDTO::mapToHeroKills)
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroItems> getItems(Long matchId, String heroName) {
        return itemsPurchasedRepository.findAllByHeroAndMatchId(heroName, matchId).stream()
                .map(heroMapper::mapToHeroItemsView)
                .collect(Collectors.toList());
    }


    @Override
    public List<HeroSpells> getSpells(Long matchId, String heroName) {
        return spellsRepository.getSpells(matchId, heroName).stream()
                .map(HeroSpellsDTO::mapToHeroSpellsView)
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroDamage> getDamage(Long matchId, String heroName) {
        return damageRepository.getDamage(matchId, heroName).stream()
                .map(HeroDamageDTO::mapToHeroDamageView)
                .collect(Collectors.toList());
    }


}
