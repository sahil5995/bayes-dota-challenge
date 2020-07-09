package gg.bayes.challenge.service.strategy;

import gg.bayes.challenge.repository.ItemsPurchasedRepository;
import gg.bayes.challenge.repository.entity.HeroItemsPurchased;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ItemsPurchasedStrategy implements EventStrategy {

    private Pattern ITEM_PURCHASED_PATTERN =
            Pattern.compile("\\[(?<timestamp>.*)] npc_dota_hero_(?<hero>.*) buys item item_(?<item>.*)");

    private ItemsPurchasedRepository repository;

    public ItemsPurchasedStrategy(ItemsPurchasedRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String event, Long matchId) {
        Matcher matcher = ITEM_PURCHASED_PATTERN.matcher(event);
        if (matcher.matches()) {
            HeroItemsPurchased heroItemsPurchased = new HeroItemsPurchased();
            heroItemsPurchased.setMatchId(matchId);
            heroItemsPurchased.setHero(matcher.group("hero"));
            heroItemsPurchased.setItem(matcher.group("item"));
            heroItemsPurchased.setTimestamp(getTimestampInMillis(matcher.group("timestamp")));
            repository.save(heroItemsPurchased);
        }
    }

    private Long getTimestampInMillis(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        LocalTime time = LocalTime.parse(timestamp, formatter);
        int hour = time.getHour();
        int minute = time.getMinute();
        int seconds = time.getSecond();
        double nanoSec = time.getNano() / Math.pow(10, 6);
        return (hour * 60 * 60 * 1000 + minute * 60 * 1000 + seconds * 1000 + (long) nanoSec);
    }
}
