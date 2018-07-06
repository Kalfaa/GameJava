import compilation.Enigme;
import compilation.Epreuve;
import util.JSONUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Test {


    public static void main(String[] args) throws IOException {
        JSONUtil jsonUtil= new JSONUtil();
        Enigme eg = (Enigme)jsonUtil.convertStringJSONToObject(Epreuve.readFile("Map/Enigme/Epreuve1.json",StandardCharsets.UTF_8),Enigme.class);
        Epreuve ep = new Epreuve(eg);
        ep.set_answer(" public class Math { public int sum(int a,int b) {return a+b ;  } }");
        ep.createClassesToExecute();
        System.out.print("3 error".matches("[0-9]* error(s|)"));
        ep.tryIt();
            }

}
