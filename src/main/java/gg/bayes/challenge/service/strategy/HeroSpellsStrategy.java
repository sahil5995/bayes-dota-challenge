package gg.bayes.challenge.service.strategy;


import gg.bayes.challenge.repository.HeroSpellsRepository;
import gg.bayes.challenge.repository.entity.HeroSpells;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroSpellsStrategy implements EventStrategy {

    private Pattern HERO_SPELLS_REGIX =
            Pattern.compile("\\[(.*)] npc_dota_hero_(?<hero>.*) casts ability (?<spell>.*) \\(lvl (?<level>.*)\\) on (?<targetHero>.*)");

    private HeroSpellsRepository repository;

    public HeroSpellsStrategy(HeroSpellsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String event, Long matchId) {
        Matcher matcher = HERO_SPELLS_REGIX.matcher(event);
        if (matcher.matches()) {
            HeroSpells heroSpells = new HeroSpells();
            heroSpells.setMatchId(matchId);
            heroSpells.setHero(matcher.group("hero"));
            heroSpells.setSpell(matcher.group("spell"));
            repository.save(heroSpells);
        }
    }
}
