package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Poweroutage> getPoweroutageList(String nerc) {

		String sql = "SELECT p.date_event_began, p.date_event_finished, p.customers_affected FROM poweroutages AS p, nerc AS n WHERE p.nerc_id = n.id AND n.value=?";
		List<Poweroutage> poweroutageList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nerc);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
			Poweroutage p = new Poweroutage(res.getInt("customers_affected"),res.getDate("date_event_began").toLocalDate(), res.getTime("date_event_began").toLocalTime(), res.getDate("date_event_finished").toLocalDate(), res.getTime("date_event_finished").toLocalTime());
			poweroutageList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return poweroutageList;
	}
	

}
