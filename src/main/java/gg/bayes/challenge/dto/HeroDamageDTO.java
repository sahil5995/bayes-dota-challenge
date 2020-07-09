package gg.bayes.challenge.dto;

import gg.bayes.challenge.rest.model.HeroDamage;
import lombok.Data;

@Data
public class HeroDamageDTO {
    private String target;
    private Long damageInstances;
    private Long totalDamage;

    public HeroDamageDTO(String target, Long damageInstances, Long totalDamage) {
        this.target = target;
        this.damageInstances = damageInstances;
        this.totalDamage = totalDamage;
    }

    public static HeroDamage mapToHeroDamageView(HeroDamageDTO heroDamageDTO) {
        HeroDamage heroDamage = new HeroDamage();
        heroDamage.setTarget(heroDamageDTO.getTarget());
        heroDamage.setDamageInstances(heroDamageDTO.getDamageInstances().intValue());
        heroDamage.setTotalDamage(heroDamageDTO.getTotalDamage().intValue());
        return heroDamage;
    }
}
