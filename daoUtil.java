package pizzaService.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.sql.Types;

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
    public <T extends classUtil> void insert(String table, T t){
        List<Object> list =t.toList();
        String sql = "INSERT INTO "+table+" values(?";
        for (int i=0; i<list.size()-1;i++){
            sql=sql+",?";
        }
        sql=sql+")";
        System.out.println(sql);
        try {
            this.ps = this.conn.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Integer) {
                    this.ps.setInt(i + 1, (Integer) list.get(i));
                }else if (list.get(i) instanceof Double) {
                    this.ps.setDouble(i + 1, (Double) list.get(i));
                }else if (list.get(i) instanceof String) {
                    this.ps.setString(i + 1, (String) list.get(i));
                }else{
                    System.out.println("put your own type in here with insanceof" + i);
                }
            }
            ps.executeUpdate();
        } catch(Exception var4){
            var4.printStackTrace();
        }


    }
    // immer ein leeres objekt vorher deklarieren um dieses mit der list zu fuellen
    public <T extends classUtil> T select(int id,String table, String whereclause,T t){
        List<Object> list = new ArrayList<Object>();
        try {

            String sql= "SELECT * FROM "+table+" WHERE "+whereclause+" = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            resultset = ps.executeQuery();
            System.out.println(resultset.isBeforeFirst());
            System.out.print(resultset.getString(1));

            ResultSetMetaData rsmd = resultset.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(columnsNumber);

            for (int i =1; i <= columnsNumber; i++) {
                try {
                    if (rsmd.getColumnType(i) == Types.INTEGER) {
                        list.add(i-1, resultset.getInt(i));
                    }else if (rsmd.getColumnType(i) == Types.VARCHAR) {
                        list.add(i-1, resultset.getString(i ));
                    }else if (rsmd.getColumnType(i) == Types.NUMERIC) {
                        list.add(i-1, resultset.getDouble(i ));
                    }else {
                        System.out.println("Add new type corresponding to the sql type");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            resultset.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        t.toClass(list);
        return t;
    }
    public void delete(int id, String table, String columm){
        try{
            String sql="delete from "+table+" where "+columm+" = ?";
            ps=(conn).prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public List<List<Object>> getTableData(String table) {
        String sql = "SELECT * FROM " + table;
        List<List<Object>> data = new ArrayList<List<Object>>();
        try {
            ps = conn.prepareStatement(sql);
            resultset = ps.executeQuery(sql);

            ResultSetMetaData rsmd = resultset.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(columnsNumber);

            while (resultset.next()) {
                List<Object> list = new ArrayList<Object>();

                for (int i = 1; i <= columnsNumber; i++) {
                    if (rsmd.getColumnType(i)== Types.INTEGER){
                        list.add(i-1,resultset.getInt(i));
                    }else if (rsmd.getColumnType(i)== Types.VARCHAR){
                        list.add(i-1,resultset.getString(i));
                    }else if(rsmd.getColumnType(i)== Types.NUMERIC){
                        list.add(i-1,resultset.getDouble(i));
                    } else{
                        System.out.println("Add new type corresponding to the sql type");
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
