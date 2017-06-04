package tetris.game;

import com.badlogic.gdx.utils.Json;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zhenya on 6/4/17.
 */
public class NetworkManager {

    public static ArrayList<Player> playersList = new ArrayList<Player>();


    public static void addPlayer(Player player){
        playersList.add(player);
        Collections.sort(playersList);
        if (playersList.size() > 4) {
            playersList.remove(playersList.size() - 1);
        }
    }

    public static void Save() {
        Json json = new Json();
        System.out.println(json.prettyPrint(playersList));
    }

    public static String getRecords() {
        String output = "";
        for (Player player : playersList) {
            output = output.concat(player.toString());
        }
        return output;
    }

}