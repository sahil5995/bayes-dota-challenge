package gg.bayes.challenge.dto;

import gg.bayes.challenge.rest.model.HeroSpells;
import lombok.Data;

@Data
public class HeroSpellsDTO {

    private String spell;
    private Long casts;

    public HeroSpellsDTO(String spell, Long casts) {
        this.spell = spell;
        this.casts = casts;
    }

    public static HeroSpells mapToHeroSpellsView(HeroSpellsDTO heroSpellsDTO) {
        HeroSpells heroSpells = new HeroSpells();
        heroSpells.setSpell(heroSpellsDTO.getSpell());
        heroSpells.setCasts(heroSpellsDTO.getCasts().intValue());
        return heroSpells;
    }
}