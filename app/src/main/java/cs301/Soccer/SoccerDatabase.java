package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** Kai Vickers ***
 * @version *** March 2020 ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    private HashMap<String, SoccerPlayer> database = new HashMap<>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {

        String key = createKey(firstName, lastName);
        SoccerPlayer player;

        if (database.containsKey(key)) {
            return false;
        } else {
            player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            database.put(key, player);
            return true;
        }
    }

    /**
     * Returns a key for the player's data based on their name
     * to be used in the database.
     *
     * @param firstName the first name of the player
     * @param lastName the last name of the player
     * @return a key combining the first and last names
     */
    private String createKey(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        return key;
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {

        String key = createKey(firstName, lastName);
        if (database.containsKey(key)) {
            database.remove(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {

        String key = createKey(firstName, lastName);
        return database.get(key);

        /*
        if(database.containsKey(key)) {
            return database.get(key);
        } else {
            return null;
        }
        */
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {

        String key = createKey(firstName, lastName);

        if (database.containsKey(key)){
            database.get(key).bumpGoals();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String key = createKey(firstName, lastName);

        if (database.containsKey(key)){
            database.get(key).bumpAssists();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String key = createKey(firstName, lastName);

        if (database.containsKey(key)){
            database.get(key).bumpShots();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String key = createKey(firstName, lastName);

        if (database.containsKey(key)){
            database.get(key).bumpSaves();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String key = createKey(firstName, lastName);

        if (database.containsKey(key)){
            database.get(key).bumpFouls();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = createKey(firstName, lastName);

        if (database.containsKey(key)){
            database.get(key).bumpYellowCards();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = createKey(firstName, lastName);

        if (database.containsKey(key)){
            database.get(key).bumpRedCards();
            return true;
        } else {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {

        int numPlayers = 0;

        if (teamName == null) {
            return database.size();
        } else {
            for (SoccerPlayer player : database.values()){
                if (player.getTeamName().equals(teamName))
                    numPlayers++;
            }

            return numPlayers;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        int playerIdx = 0;

        if (teamName == null) {
            for (SoccerPlayer player : database.values()) {
                if (playerIdx == idx) {
                    return player;
                } else {
                    playerIdx++;
                }
            }
            return null;
        } else {
            for (SoccerPlayer player : database.values()) {
                if (player.getTeamName().equals(teamName)){
                    if (playerIdx == idx){
                        return player;
                    } else {
                        playerIdx++;
                    }
                }
            }
            return null;
        }
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (SoccerPlayer player : database.values()) {
                writer.println(logString("First Name: " + player.getFirstName()));
                writer.println(logString("Last Name: " + player.getLastName()));
                writer.println(logString("Uniform Number: " + player.getUniform()));
                writer.println(logString("Goals Scored: " + player.getGoals()));
                writer.println(logString("Assists: " + player.getAssists()));
                writer.println(logString("Shots: " + player.getShots()));
                writer.println(logString("Fouls: " + player.getFouls()));
                writer.println(logString("Saves: " + player.getSaves()));
                writer.println(logString("Yellow Cards: " + player.getYellowCards()));
                writer.println(logString("Red Cards: " + player.getRedCards()));
                writer.println(logString("Team Name: " + player.getTeamName()));
                writer.println();
            }
            writer.close();
            return true;

        } catch (Exception FileNotFoundException) {
            return false;
        }
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}
