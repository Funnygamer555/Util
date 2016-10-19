package pizzaService.Util;

import java.util.*;

public class Speise implements classUtil{
	
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
	@Override
	public List<Object> toList(){
		List<Object> list = new ArrayList<Object>();

		list.add(0, this.nr);
		list.add(1,this.bezeichnung);
		list.add(2, this.beschreibung);
		list.add(3,this.art);
		list.add(4,this.preis);

		return list;
	}

	@Override
	public void toClass(List<Object> list) {
		this.nr= (Integer)list.get(0);
		this.bezeichnung=(String) list.get(1);
		this.beschreibung=(String)list.get(2);
		this.art=(Integer) list.get(3);
		this.preis=(Double)list.get(4);

	}

}
