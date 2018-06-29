package compilation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Epreuve {
    boolean _isSucceed;
    private Enigme _enigme;
    private String _answer;
    private final static String PATH = "src/compilation/";
   private Epreuve(Enigme enigme){
        _isSucceed = false ;
        _enigme  = enigme;
    }

    public static Epreuve createEpreuve(Enigme enigme){
           return new Epreuve(enigme);
    }

    public void tryIt() {
        Compiler compiler = new Compiler();
        createClassesToExecute();
        compiler.runTest(_enigme.get_className());
    }

    public void createClassesToExecute(){
       File userClass = new File(PATH+_enigme.get_className()+".java");
       File mainClass = new File(PATH+"Main"+".java");
       try{
           BufferedWriter writer = new BufferedWriter(new FileWriter(userClass));
           writer.write(_answer);
           writer.close();
       }catch(IOException e ){

       }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(mainClass));
            writer.write(writeMainClass());
            writer.close();
        }catch(IOException e ){

        }



    }

    public String writeMainClass() throws IOException {
       String class_name ="" ;
       String result = readFile("compilation/packagecompile/TemplateClassMain",StandardCharsets.UTF_8);
       result =result.replace("$TESTCLASS" ,class_name);
       result = result.replace("$TEST1",_enigme.get_nameTest1());
       result = result.replace("$TEST2",_enigme.get_nameTest2());
       result = result.replace("$TEST2",_enigme.get_nameTest2());
       result = result.replace("$BLOC1",_enigme.get_blocTest1());
       result = result.replace("$BLOC2",_enigme.get_blocTest2());
       result = result.replace("$BLOC3",_enigme.get_blocTest3());


        return result ;
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


}
