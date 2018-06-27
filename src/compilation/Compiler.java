package compilation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Compiler {


    private static void runProcess(String command) throws Exception {
            Process pro = Runtime.getRuntime().exec(command, null, new File("src"));
            pro.waitFor(3, TimeUnit.SECONDS);
            //printLines(command + " stdout:", pro.getInputStream());
            //printLines(command + " stderr:", pro.getErrorStream());
            Sortie s = new Sortie(pro,command);   s.start();
            Erreur err = new Erreur(pro,command); err.start();

            System.out.println(command + " exitValue() " + pro.exitValue());


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
}

