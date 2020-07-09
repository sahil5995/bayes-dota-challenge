package gg.bayes.challenge.strategy;


import gg.bayes.challenge.repository.HeroDamageRepository;
import gg.bayes.challenge.repository.entity.HeroDamage;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HeroDamageStrategy implements EventStrategy {

    private Pattern DAMAGE_DONE_PATTERN =
            Pattern.compile("\\[(?<timestamp>.*)] npc_dota_hero_(?<hero>.*) hits npc_dota_hero_(?<heroDamaged>.*) with (?<itemUsed>.*) for (?<damageCount>\\d+) damage (?<someValue>.*)");

    private HeroDamageRepository repository;

    public HeroDamageStrategy(HeroDamageRepository heroDamageRepository) {
        this.repository = heroDamageRepository;
    }


    @Override
    public void execute(String event, Long matchId) {
        Matcher matcher = DAMAGE_DONE_PATTERN.matcher(event);
        if (matcher.matches()) {
            HeroDamage heroDamage = new HeroDamage();
            heroDamage.setMatchId(matchId);
            heroDamage.setHero(matcher.group("hero"));
            heroDamage.setHeroDamaged(matcher.group("heroDamaged"));
            heroDamage.setDamageCount(Integer.valueOf(matcher.group("damageCount")));
            repository.save(heroDamage);
        }
    }
}
