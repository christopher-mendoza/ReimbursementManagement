package dev.mendoza.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dev.mendoza.models.Event;
import dev.mendoza.models.EventType;
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
		String sql = "INSERT INTO events VALUES (default, ?, ?, ?, ?, 0, ?) RETURNING *;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setDate(1, e.getEventDate());
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
	
	public Event getEventById(Integer id) {
		String sql = "SELECT * FROM events JOIN event_types " +
					 "ON e_type = event_type_id " +
					 "WHERE event_id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			// Checks if ResultSet returns nothing (bad input)
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				Event e = new Event();
				e.setId(rs.getInt("event_id"));
				e.setEventDate(rs.getDate("event_date"));
				e.setEventLocation(rs.getString("event_location"));
				e.setEventDesc(rs.getString("event_description"));
				e.setEventCost(rs.getFloat("event_cost"));
				e.setEventProposed(rs.getFloat("event_proposed_cost"));
				// Add EventType Object
				EventType eType = new EventType();
				eType.setId(rs.getInt("event_type_id"));
				eType.setType(rs.getString("event_type"));
				eType.setCoverage(rs.getFloat("event_coverage"));
				e.setEventType(eType);
				return e;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EventType getEventTypeByEventType(String eventType) {
		String sql = "SELECT * FROM event_types " +
					 "WHERE event_type = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, eventType);
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) {
				return null;
			}
			if(rs.next()) {
				EventType eType = new EventType();
				eType.setId(rs.getInt("event_type_id"));
				eType.setType(rs.getString("event_type"));
				eType.setCoverage(rs.getFloat("event_coverage"));
				return eType;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Event> getAllEvents() {
		String sql = "SELECT * FROM events " +
					"JOIN event_types ON e_type = event_type_id " +
					"ORDER BY event_id ASC;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Event> events = new ArrayList<Event>();
			while(rs.next()) {
				Event e = new Event();
				e.setId(rs.getInt("event_id"));
				e.setEventDate(rs.getDate("event_date"));
				e.setEventLocation(rs.getString("event_location"));
				e.setEventDesc(rs.getString("event_description"));
				e.setEventCost(rs.getFloat("event_cost"));
				e.setEventProposed(rs.getFloat("event_proposed_cost"));
				// Add EventType Object
				EventType eType = new EventType();
				eType.setId(rs.getInt("event_type_id"));
				eType.setType(rs.getString("event_type"));
				eType.setCoverage(rs.getFloat("event_coverage"));
				e.setEventType(eType);
				events.add(e);
			}
			return events;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changeProposedAmt(Event e, float newAmt) {
		String sql = "UPDATE events SET event_proposed_cost = ? " +
					 "WHERE event_id = ?;";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setFloat(1, newAmt);
			cs.setInt(2, e.getId());
			cs.execute();
			cs.close();
			return true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
}
