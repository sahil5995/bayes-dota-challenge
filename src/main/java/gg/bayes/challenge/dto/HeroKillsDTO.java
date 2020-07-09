package gg.bayes.challenge.dto;

import gg.bayes.challenge.rest.model.HeroKills;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeroKillsDTO {
    private String hero;
    private Long kills;

    public static HeroKills mapToHeroKills(HeroKillsDTO heroKillsDTO) {
        HeroKills heroKills = new HeroKills();
        heroKills.setHero(heroKillsDTO.getHero());
        heroKills.setKills(heroKillsDTO.getKills().intValue());
        return heroKills;
    }
}
