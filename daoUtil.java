package pizzaService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by Serujio on 19-Oct-16.
 */
// IMPORTANT: immer auf reihenfolge achten in welcher die daten in der datenbank auftauchen
public abstract class daoUtil {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet resultset = null;

    public daoUtil(String filename){
        try {
            Class.forName("org.sqlite.JDBC");
            //String e = "speise1.db3";
            String url = "jdbc:sqlite::resource:" + filename;
            this.conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void insert(List<String> list, String table){
        String sql = "INSERT INTO "+table+" values(?";
        for (int i=0; i<list.size()-1;i++){
            sql=sql+",?";
        }
        sql=sql+")";
        try {
            this.ps = this.conn.prepareStatement(sql);
            for (int i =0; i < list.size();i++){
                try {
                    this.ps.setInt(i+1,Integer.parseInt(list.get(i)));
                }catch(NumberFormatException ne){
                    try {
                        this.ps.setString(i+1,list.get(i));
                    }catch(NumberFormatException nre){
                        this.ps.setDouble(i+1, Double.parseDouble(list.get(i)));
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }
    // immer ein leeres objekt vorher deklarieren um dieses mit der list zu fuellen
    public List<String> select(int id,String table, String whereclause){
        List<String> list = new ArrayList<String>();
        try {

            String sql= "SELECT * FROM "+table+" WHERE "+whereclause+" = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            resultset = ps.executeQuery();

            ResultSetMetaData rsmd = resultset.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(columnsNumber);

            for (int i = 0; i < columnsNumber; i++) {
                try {
                    list.add(i, resultset.getString(i+1));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            resultset.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<List<String>> getTableData(String table) {
        String sql = "SELECT * FROM " + table;
        List<List<String>> data = new ArrayList<List<String>>();
        try {
            ps = conn.prepareStatement(sql);
            resultset = ps.executeQuery(sql);

            ResultSetMetaData rsmd = resultset.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(columnsNumber);

            while (resultset.next()) {
                List<String> list = new ArrayList<String>();

                for (int i = 0; i < columnsNumber; i++) {
                    try {
                        list.add(i, resultset.getString(i+1));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                data.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public void closeConnection(){
        try{
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
