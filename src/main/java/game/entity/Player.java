package game.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 14.01.2016.
 */
@Entity
@Table (name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="player_id")
    private long playerId;
    @Column(name="player_name")
    private String playerName;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "player")
    private List<Game> gameList;

    public Player(){}

    public Player(String playerName){
        this.playerName = playerName;
    }

    public long getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }
}
