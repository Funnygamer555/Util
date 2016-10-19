package pizzaService.Util;


/**
 * Created by Serujio on 19-Oct-16.
 */
public class test{


    public static void main(String[] args){
        // sachen die gebraucht werden fuer einen reibungslosen ablauf
        String filename = "speise1.db3";
        String table = "Speise";

        SpeiseDAOsqlite dao = new SpeiseDAOsqlite(filename);
        Speise s = new Speise(20,"hey you","copy",23.45,3);
        //dao.delete(20,table,"Nr");
        System.out.println(s.getNr());
        dao.insert("Speise",s);
        Speise sp = new Speise();
        sp=dao.select(20,table,"Nr",sp);
        System.out.println("after select");
        System.out.println(sp.getBeschreibung());
        sp.setBeschreibung("tomato");
        dao.update(sp,table);
        sp=dao.select(20,table,"Nr",sp);
        System.out.println(sp.getBeschreibung());
        dao.delete(20,table,"Nr");


    }
}
