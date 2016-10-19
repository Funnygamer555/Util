package pizzaService;

import java.util.*;

public class Speise {
	
	private int nr;
	private String bezeichnung;
	private String beschreibung;
	private int art;
	private double preis;

	public Speise(){
	}
	public Speise(int nr, String bezeichnung, String beschreibung, double preis, int art){
		this.nr=nr;
		this.bezeichnung=bezeichnung;
		this.beschreibung=beschreibung;
		this.preis=preis;
		this.art=art;
	}

	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getArt() {
		return art;
	}
	public void setArt(int art) {
		this.art = art;
	}
	public List<String> toList(){
		List<String> list = new ArrayList<String>();

		list.add(0, this.nr+"");
		list.add(1,this.bezeichnung);
		list.add(2, this.beschreibung);
		list.add(3,this.art+"");
		list.add(4,this.preis+"");

		return list;
	}
	public void toClass(List<String> list) {
		this.nr= Integer.parseInt(list.get(0));
		this.bezeichnung=list.get(1);
		this.beschreibung=list.get(2);
		this.art=Integer.parseInt(list.get(3));
		this.preis=Double.parseDouble(list.get(4));

	}

}
