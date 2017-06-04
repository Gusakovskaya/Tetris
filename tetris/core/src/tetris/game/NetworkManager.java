package tetris.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.dropbox.core.*;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v1.DbxWriteMode;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenya on 6/4/17.
 */
public class NetworkManager {

    public static ArrayList<Player> playersList = new ArrayList<Player>();

    private static DbxClientV1 client;

    public static final String accessToken = "Hvc4gVIR41oAAAAAAAARFt85at4kajclA81-WvOsKO08AISb0ZlyjUv2NjncX-Ek";

    private static boolean firstSync = true;

    public static void addPlayer(Player player){
        playersList.add(player);
        Collections.sort(playersList);

        try {
            uploadResults();
        }
        catch (Exception e) {}
    }

    private static void createConnection() throws IOException, DbxException {
        DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());

        client = new DbxClientV1(config, accessToken);
    }

    private static String createStringResult(String data) {
        String output = "";
        ArrayList<Player> players = new ArrayList<Player>();
        Pattern pattern = Pattern.compile("Name: (.*), Points: (.*), Lines: (.*)");
        for (String result : data.split("\n")) {
            Matcher matcher = pattern.matcher(result);
            if (matcher.matches()) {
                players.add(new Player(matcher.group(1),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3))));
            }
        }
        players.add(playersList.get(playersList.size() - 1));
        Collections.sort(players);
        for (Player player : players) {
            output = output.concat(player.toString());
        }
        return output;
    }

    private static void createResultFile() {
        String online_result = "";
        try {
            downloadResults();
            FileHandle file = Gdx.files.local("results.txt");
            online_result = file.readString();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        online_result = createStringResult(online_result);

        File file = new File("results.txt");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(online_result.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void uploadResults() throws IOException, DbxException {
        createConnection();
        createResultFile();

        File inputFile = new File("results.txt");
        FileInputStream inputStream = new FileInputStream(inputFile);

        try {
            try {
                client.delete("/results.txt");
            }
            catch (BadResponseCodeException e) {}
            DbxEntry.File uploadedFile = client.uploadFile("/results.txt",
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            inputStream.close();
        }
    }

    public static void downloadResults() throws IOException, DbxException{
        createConnection();
        FileOutputStream outputStream = new FileOutputStream("results.txt");
        try {
            DbxEntry.File downloadedFile = client.getFile("/results.txt", null,
                    outputStream);
            System.out.println("Metadata: " + downloadedFile.toString());
        } finally {
            outputStream.close();
        }
    }
    public static String getRecords() {
        String result = "";
        try {
            downloadResults();
            FileHandle file = Gdx.files.local("results.txt");
            result = file.readString();
        }
        catch (Exception e) {
            for (Player player : playersList) {
                result = result.concat(player.toString());
            }
        }
        return result;
    }

}