package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import it.polito.tdp.poweroutages.model.Poweroutage;

public class TestPowerOutagesDAO {

	public static void main(String[] args){
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getNercList()) ;

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
		
		
		PowerOutageDAO dao = new PowerOutageDAO() ;
		for(Poweroutage p : dao.getPoweroutageList("RFC")) {
			if(p != null) {
				System.out.println(p.toString());
			}
		}
			

	}

}
