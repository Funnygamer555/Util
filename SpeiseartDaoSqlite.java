package pizzaService.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpeiseartDaoSqlite {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet resultset=null;
	public SpeiseartDaoSqlite(){
		try{
			Class.forName("org.sqlite.JDBC");
			String datei = "Speiseart.db3";
			String url= "jdbc:sqlite::resource:" + datei;
			conn = DriverManager.getConnection(url);
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	public Speiseart select(int art){
		Speiseart s = new Speiseart();
		try{
		String sql= "SELECT * FROM Speiseart WHERE art = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, art);
		resultset = ps.executeQuery();
		resultset.next();

		s.setArt(art);
		s.setText(resultset.getString("Text"));
		

		}catch (Exception e) {
		e.printStackTrace();
		}
		return s;
	}
	public String[]combowert(){
		String[] werte = new String[this.count()];
		for(int i =0;i<werte.length;i++){
		werte[i]=this.wertdescombo(i+1);	
		System.out.println(i);
		}
		return werte;
	}
	public int count(){
		int id = 0;
		try{
			String sql="select count(*) from Speiseart";
			ps = conn.prepareStatement(sql);
			resultset = ps.executeQuery();
			resultset.next();
			String ID = resultset.getString("count(*)");
			System.out.println(ID);
			id= Integer.parseInt(ID.trim());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return id;	
	}
	public String wertdescombo(int i){
		String wert = null;
		System.out.println("check1");
		try{
		String sql="select*from Speiseart where art =?";
		ps=conn.prepareStatement(sql);
		ps.setInt(1, i);
		resultset=ps.executeQuery();
		resultset.next();
		wert = resultset.getString("Text");
		System.out.println(wert);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wert;
	}
	
}
