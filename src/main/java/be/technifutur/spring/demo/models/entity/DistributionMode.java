package be.technifutur.spring.demo.models.entity;

// Cette énumération définit les modes de distribution des prix dans les compétitions
public enum DistributionMode {

    // Le gagnant remporte l'intégralité du prix
    WINNER_TAKES_ALL,

    // Les participants sur le podium se partagent le prix
    PODIUM_SPLIT,

    // Les huit meilleurs participants se partagent le prix
    TOP_8;
}
