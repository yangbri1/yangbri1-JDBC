

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    // 'createSong' method w/ no return type (void)
    public void createSong(Song song)  {
        //write jdbc code here
        // INSERT INTO songs (id, title, artist) VALUES (4, 'After LIKE', 'IVE');
        // INSERT INTO songs (song.id, title, artist) VALUES song;

        try {
            // connect to DB 'songs'
            Connection conn = ConnectionUtil.getConnection();
            // 
            // String sql = "INSERT INTO songs (title, artist) VALUES (?, ?)";
            /* NOTICE: 'PreparedStatement' ----------- .prepareStatement() */
            PreparedStatement prepState = conn.prepareStatement("INSERT INTO songs (id, title, artist) VALUES (?, ?, ?)");
            prepState.setInt(1, song.getId());
            prepState.setString(2, song.gettitle());
            prepState.setString(3, song.getArtist());
            prepState.executeUpdate();

            // Statement stmt = conn.createStatement();

            // write out desired SQL query statement
            // String sql = "INSERT INTO songs (id, title, artist) VALUES (song.id, song.title, song.artist)";
            
            // https://www.tutorialspoint.com/java-resultset-insertrow-method-with-example
            // String sql = "SELECT * from songs";
            // stmt.setId = song.id;
            
            // retrieving data from DB
            // ResultSet resultSet = stmt.executeUpdate(sql);
            // ResultSet rs = stmt.executeQuery(sql);
            // navigate cursor to last row in result set & return true if exist
            // rs.last();
            // dynamically update column 'id' according to existing number of records/rows
            // int id = rs.getInt("id") + 1;
            // String titleCopy = rs.getString("title");
            // String artistCopy = rs.getString("artist");
            // move to row to be inserted at
            // rs.moveToInsertRow();
            // update 'id' to next number
            // rs.updateInt("id", id);
            // rs.updateString("title", song.gettitle());
            // rs.updateString("artist", song.getArtist());

            // rs.updateString("title", titleCopy);
            // rs.updateString("artist", artistCopy);

            // rs.insertRow();

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    // 'getAllSongs' method w/ return type of List<Song> 
    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        //write jdbc code here
        
        // creating a DB connection
        try {
            Connection conn = ConnectionUtil.getConnection();
            // create statement obj to send SQL statements to DB
            /* NOTICE: 'Statement' ---------- .createStatement()  */
            Statement stmt = conn.createStatement();
            // write out desired SQL query statement
            String sql = "SELECT * FROM songs";
            // retrieving data from DB
            ResultSet resultSet = stmt.executeQuery(sql);
            // ResultSet .next() navigate method -- moves cursor to next row/record in result set, if exists return true (Note: Rows in SQL start at 1)
            while(resultSet.next()){
                // retrieves values from respective columns via ResultSet's .getInt(), .getString() methods
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String artist = resultSet.getString("artist");

                // append return values to empty ArrayList declared above
                songs.add(new Song(id, title, artist));
            }
            // catch any Exceptions that are thrown
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // return whole 'songs' DB table
        return songs;
    }
}
