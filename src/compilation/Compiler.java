package compilation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Compiler {

    public boolean isTest1 ;

    public boolean isTest2 ;

    public boolean isTest3;

    private static void runProcess(String command) throws Exception {
            Process pro = Runtime.getRuntime().exec(command, null, new File("src"));
            pro.waitFor(3, TimeUnit.SECONDS);
            //printLines(command + " stdout:", pro.getInputStream());
            //printLines(command + " stderr:", pro.getErrorStream());
            Sortie s = new Sortie(pro,command);   s.start();
            Erreur err = new Erreur(pro,command); err.start();
            System.out.println(command + " exitValue() " + pro.exitValue());
    }
     Compiler(){
        isTest1 = true ;
        isTest2 = true ;
        isTest3 = true ;
    }

    public static void main(String[] args) {
        try {
            runProcess("javac compilation/packagecompile/Main.java compilation/packagecompile/TestClass.java ");
            runProcess("java compilation/packagecompile/Main");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public boolean runTest(String className){
        try {
            runProcess("javac compilation/packagecompile/Main.java compilation/packagecompile/"+className+".java ");
            runProcess("java compilation/packagecompile/Main");
        } catch (Exception e) {
            e.printStackTrace();
            return false ;
        }
        return true;
    }
}

