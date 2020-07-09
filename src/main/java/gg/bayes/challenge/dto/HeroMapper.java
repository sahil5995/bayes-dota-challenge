package gg.bayes.challenge.dto;

import gg.bayes.challenge.repository.entity.HeroItemsPurchased;
import gg.bayes.challenge.rest.model.HeroItems;
import org.springframework.stereotype.Component;

@Component
public class HeroMapper {


    public HeroItems mapToHeroItemsView(HeroItemsPurchased heroItemsPurchased) {
        HeroItems heroItems = new HeroItems();
        heroItems.setItem(heroItemsPurchased.getItem());
        heroItems.setTimestamp(heroItemsPurchased.getTimestamp());
        return heroItems;
    }

}
