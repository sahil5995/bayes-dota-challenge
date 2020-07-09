package gg.bayes.challenge.service.strategy;

public interface EventStrategy {

    void execute(String eventLine, Long matchId);
}
