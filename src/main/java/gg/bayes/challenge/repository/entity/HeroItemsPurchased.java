package gg.bayes.challenge.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HeroItemsPurchased {
    @Id
    @GeneratedValue
    private Long id;

    private Long matchId;

    private String hero;

    private String item;

    private Long timestamp;
}
