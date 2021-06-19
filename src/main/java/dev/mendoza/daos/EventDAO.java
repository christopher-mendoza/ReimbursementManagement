package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import dev.mendoza.models.Event;
import dev.mendoza.utils.JDBCConnection;

/*
public class Event {
	private Integer id;
	private Timestamp eventDate;
	private String eventLocation;
	private String eventDesc;
	private Float eventCost;
	private String eventType;
	 
*/
public class EventDAO {
	private Connection conn = JDBCConnection.getConnection();
	
	public boolean addEvent(Event e) {
		String sql = "INSERT INTO events VALUES (default, ?, ?, ?, ?, ?) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setTimestamp(1, e.getEventDate());
			cs.setString(2, e.getEventLocation());
			cs.setString(3,  e.getEventDesc());
			cs.setFloat(4,  e.getEventCost());
			cs.setInt(5, e.getEventType().getId());
			cs.execute();
			cs.close();
			return true;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
