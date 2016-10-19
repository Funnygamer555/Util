package pizzaService.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpeiseDAOsqlite extends daoUtil{
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet resultset=null;
	private PreparedStatement ps_1=null;
	private Connection conn_1=null;
	private ResultSet resultset_1=null;
	
	public SpeiseDAOsqlite(String filename) {
		super(filename);

	}
	/*public void insert(Speise speise){
		int id = defaultId();
		try{
			
			String sql="INSERT INTO Speise values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, speise.getBeschreibung()); 
			ps.setString(3, speise.getBezeichnung());
			ps.setInt(4, speise.getArt());
			ps.setDouble(5, speise.getPreis());
			ps.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	*/
	public void delete(int id){
		try{
			String sql="delete from Speise where Nr = ?";
			ps=(conn).prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void update(Speise speise){
		int nr = speise.getNr();
		
		try{
		this.delete(nr);
		String sql="INSERT INTO Speise values(?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, nr);
		ps.setString(2, speise.getBeschreibung()); 
		ps.setString(3, speise.getBezeichnung());
		ps.setDouble(4, speise.getPreis());
		ps.setInt(5, speise.getArt());
		ps.executeUpdate();

		}catch (Exception e) {
		e.printStackTrace();
		}

	}
	public Speise first(){
		Speise s = new Speise();
		int nr = s.getNr();
		try{
		String sql= "select * from Speise";
		ps = conn.prepareStatement(sql);
		resultset = ps.executeQuery();
		resultset.next();

		s.setNr(nr);
		String bezeichung = resultset.getString("Bezeichnung");
		s.setBezeichnung(bezeichung);
		s.setBeschreibung(resultset.getString("Beschreibung"));
		s.setPreis(resultset.getDouble("Preis"));
		s.setArt(resultset.getInt("Art"));

		}catch (Exception e) {
		e.printStackTrace();
		}
		return s;

	}
	public Speise last(){
		Speise s = new Speise();
		try{
		String sql= "select * from Speise order by desc";
		ps = conn.prepareStatement(sql);
		resultset = ps.executeQuery();
		resultset.next();

		s.setNr(resultset.getInt("Nr"));
		String bezeichung = resultset.getString("Bezeichnung");
		s.setBezeichnung(bezeichung);
		s.setBeschreibung(resultset.getString("Beschreibung"));
		s.setPreis(resultset.getDouble("Preis"));
		s.setArt(resultset.getInt("Art"));

		}catch (Exception e) {
		e.printStackTrace();
		}
		return s;

	}
	public Speise next(int id){
		Speise s = new Speise();
		if(id>=this.defaultId()-1){
			id=0;
		}
		try{
		String sql= "select * from Speise where ? < Nr";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		resultset = ps.executeQuery();
		resultset.next();
		s.setNr(resultset.getInt("Nr"));
		String bezeichung = resultset.getString("Bezeichnung");
		s.setBezeichnung(bezeichung);
		s.setBeschreibung(resultset.getString("Beschreibung"));
		s.setPreis(resultset.getDouble("Preis"));
		s.setArt(resultset.getInt("Art"));

		}catch (Exception e) {
		e.printStackTrace();
		}
		return s;
	}
	public Speise previous(int id){
		Speise s = new Speise();
		if(id<=1){
			id=this.defaultId();
		}
		try{
		String sql= "select * from Speise where Nr < ? order by Nr desc";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		resultset = ps.executeQuery();
		resultset.next();
		s.setNr(resultset.getInt("Nr"));
		String bezeichung = resultset.getString("Bezeichnung");
		s.setBezeichnung(bezeichung);
		s.setBeschreibung(resultset.getString("Beschreibung"));
		s.setPreis(resultset.getDouble("Preis"));
		s.setArt(resultset.getInt("Art"));

		}catch (Exception e) {
		e.printStackTrace();
		}
		return s;
	}
	/*
	public Speise select(int nr){
		Speise s = new Speise();
		try{
		String sql= "SELECT * FROM Speise WHERE Nr = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, nr);
		resultset = ps.executeQuery();
		resultset.next();

		s.setNr(nr);
		String bezeichung = resultset.getString("Bezeichnung");
		s.setBezeichnung(bezeichung);
		s.setBeschreibung(resultset.getString("Beschreibung"));
		s.setPreis(resultset.getDouble("Preis"));
		s.setArt(resultset.getInt("Art"));

		}catch (Exception e) {
		e.printStackTrace();
		}
		return s;
	}*/
	public void closeConnection(){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public int defaultId(){
		int id = 0;
		try{
			String sql="select count(*) from Speise";
			ps = conn.prepareStatement(sql);
			resultset = ps.executeQuery();
			resultset.next();
			String ID = resultset.getString("count(*)");
			id= Integer.parseInt(ID.trim());
			id++;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return id;	
	}

	
	
	
	
}
