package gg.bayes.challenge.service.strategy;


import gg.bayes.challenge.repository.HeroKillsRepository;
import gg.bayes.challenge.repository.entity.HeroKills;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroKillsStrategy implements EventStrategy {

    private Pattern HERO_KILLS_PATTERN =
            Pattern.compile("\\[(?<timestamp>.*)] npc_dota_hero_(?<heroKilled>.*) is killed by npc_dota_hero_(?<hero>.*)");

    private HeroKillsRepository repository;

    public HeroKillsStrategy(HeroKillsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String event, Long matchId) {
        Matcher matcher = HERO_KILLS_PATTERN.matcher(event);
        if (matcher.matches()) {
            HeroKills heroKills = new HeroKills();
            heroKills.setMatchId(matchId);
            heroKills.setHero(matcher.group("hero"));
            heroKills.setHeroKilled(matcher.group("heroKilled"));
            repository.save(heroKills);
        }
    }
}
