package game.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 14.01.2016.
 */
@Entity
@Table (name="games")
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="game_id")
    private long gameId;

    @ManyToOne (cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="player_id")
    private Player player;

    @Column(name="rnd_secuence")
    private long rndSecuence;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game")
    private List<ComputerNumber> computerNumberList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game")
    private List<PlayerNumber> playerNumberList;

    public Game(){}

    public Game(Player player){
        this.player = player;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getRndSecuence() {
        return rndSecuence;
    }

    public void setRndSecuence(long rndSecuence) {
        this.rndSecuence = rndSecuence;
    }

    public List<ComputerNumber> getComputerNumberList() {
        return computerNumberList;
    }

    public void setComputerNumberList(ArrayList<ComputerNumber> computerNumberList) {
        this.computerNumberList = computerNumberList;
    }
}
