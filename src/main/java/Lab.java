

import java.sql.Connection;
import java.sql.DriverManager;
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

    public void createSong(Song song)  {
        //write jdbc code here
        // INSERT INTO songs (id, title, artist) VALUES (4, 'After LIKE', 'IVE');
        // INSERT INTO songs (song.id, title, artist) VALUES song;

    }

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        //write jdbc code here
        
        // creating a DB connection
        try {
            Connection conn = ConnectionUtil.getConnection();
            // 
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
