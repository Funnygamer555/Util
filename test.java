package pizzaService;

/**
 * Created by Serujio on 19-Oct-16.
 */
public class test{


    public static void main(String[] args){
        // sachen die gebraucht werden fuer einen reibungslosen ablauf
        String filename = "speise1.db3";
        String table = "Speise";

        SpeiseDAOsqlite dao = new SpeiseDAOsqlite(filename);
        Speise s = new Speise(20,"hey you", "copy",23.45,3);
        System.out.println(s.getNr());
        //dao.insert(s.toList(), "Speise");
        Speise sp = new Speise();
        sp.toClass(dao.select(1,table,"Nr"));
        System.out.print(sp.getBeschreibung());

    }
}
