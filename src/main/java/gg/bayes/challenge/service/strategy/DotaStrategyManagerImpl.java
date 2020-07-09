package gg.bayes.challenge.service.strategy;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DotaStrategyManagerImpl implements DotaStrategyManager {

    private final HeroDamageStrategy heroDamageStrategy;
    private final HeroKillsStrategy heroKillsStrategy;
    private final ItemsPurchasedStrategy itemsPurchasedStrategy;
    private final HeroSpellsStrategy heroSpellsStrategy;

    private List<EventStrategy> allStrategies = new ArrayList<>();

    public DotaStrategyManagerImpl(HeroDamageStrategy heroDamageStrategy, HeroKillsStrategy heroKillsStrategy,
                                   ItemsPurchasedStrategy itemsPurchasedStrategy,
                                   HeroSpellsStrategy heroSpellsStrategy) {
        this.heroDamageStrategy = heroDamageStrategy;
        this.heroKillsStrategy = heroKillsStrategy;
        this.itemsPurchasedStrategy = itemsPurchasedStrategy;
        this.heroSpellsStrategy = heroSpellsStrategy;

        allStrategies.addAll(Arrays.asList(this.heroDamageStrategy, this.heroKillsStrategy,
                this.itemsPurchasedStrategy,
                this.heroSpellsStrategy));
    }

    @Override
    public void apply(String line, Long matchId) {
        for (EventStrategy eventStrategy : allStrategies) {
            eventStrategy.execute(line, matchId);
        }
    }
}
