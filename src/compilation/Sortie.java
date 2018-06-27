package compilation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class Sortie extends Thread{
    public Process _pCommande;
    String _command;
    Sortie(Process pCommande,String command){
        _pCommande = pCommande;
        _command = command;
    }

    public void run() {
        try {
                printLines(_command + " stdout:", _pCommande.getInputStream());
        } catch (Exception ioe) {}
    }

    private static void printLines(String name, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
        }
    }
}