package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.FXMLController;
import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private float customersAffectedHigher;
	private float oreTotali;
	private List<Poweroutage> best;
	private int MAX_YEARS;
	private long MAX_HOURS;
	private List<Poweroutage> terremoti;
	FXMLController controller;
	
	public Model() {
		podao = new PowerOutageDAO();
		this.oreTotali = 0;
		this.customersAffectedHigher = 0;
		this.best = new ArrayList<Poweroutage>();
		
	}
	
	public List<Poweroutage> trovaSequenza(Nerc nerc) {
		
		String zona = nerc.toString();
		this.terremoti = podao.getPoweroutageList(zona);
		
		List<Poweroutage> parziale = new ArrayList<Poweroutage>();
		
		ricorsiva(parziale, 0);
		return this.best;
		
	}
	
	public float getCustomersAffectedHigher() {
		return customersAffectedHigher;
	}

	public float getOreTotali() {
		return oreTotali;
	}

	public void setMAX_YEARS(int mAX_YEARS) {
		MAX_YEARS = mAX_YEARS;
	}

	public void setMAX_HOURS(long mAX_HOURS) {
		MAX_HOURS = mAX_HOURS;
	}

	public void ricorsiva(List<Poweroutage> parziale, int livello) {
		
		float ore = calcolaOre(parziale);
		int anni = calcolaAnni(parziale);
		
		if(livello == this.terremoti.size()) {   // Ho analizzato tutti i casi
			return;
		}
		
		if( ore > MAX_HOURS) {		// Soluzione non interessamente,  la blocco;
			return;
		}
		
		if( anni > MAX_YEARS) {		// Lo stesso
			return;
		}
		float persone = calcolaPersone(parziale);
		// Se arrivo qui vuol dire che non ho ancora sforato le due condizioni importanti, e posso vedere se è effettivamente la soluzione migliore che ha più persone 
		// che sono state coinvolte (oppure se ci troviamo al primo caso e devo salvarmi i dati, che sicuramente sono i migliori finora)
		if(persone > this.customersAffectedHigher) {
			this.customersAffectedHigher = persone;
			this.oreTotali = ore;
			this.best = new ArrayList<Poweroutage>(parziale);		
		}
		
		// Qua devo mettere invece la procedura ricorsiva. Per farlo posso aggiungere o non aggiungere un terremoto; ogni volta che però prendo 
		// una strada devo andarla ad esplorare, per trovare la migliore (funzione di ottimo)
		
		parziale.add(terremoti.get(livello));
		ricorsiva(parziale, livello+1);
		parziale.remove(parziale.size()-1);
		
		ricorsiva(parziale,livello+1);
	}
	
	private float calcolaPersone(List<Poweroutage> parziale) {
		float persone = 0;
		
		for(Poweroutage p : parziale) {
			persone += p.getCustomersAffected();
		}
		return persone;
	}

	private int calcolaAnni(List<Poweroutage> parziale) {
		int annoMin = 0;
		int annoMax = 0;
		
		for(Poweroutage p : parziale) {
			if(annoMax == 0 && annoMin == 0) {
				annoMin = p.getDateEventBegan().getYear();
				annoMax = p.getDateEventBegan().getYear();
			}
			if(p.getDateEventBegan().getYear() < annoMin) {
				annoMin = p.getDateEventBegan().getYear();
			}
			
			if(p.getDateEventBegan().getYear() > annoMax) {
				annoMax = p.getDateEventBegan().getYear();
			}
		}
		return annoMax - annoMin;
	}

	private long calcolaOre(List<Poweroutage> parziale) {
		long pippo = 0;
		
		for(Poweroutage p : parziale) {
			pippo += p.getDuration().toHours();
		}
		return pippo;
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
}
