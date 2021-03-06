package compilation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Erreur extends Thread{
    Process _pCommande;
    String _command;
    public List<String> _stdout;
    public boolean _error;

    public boolean is_error() {
        return _error;
    }

    public void set_error(boolean _error) {
        this._error = _error;
    }

    Erreur(Process pCommande, String command){
        _pCommande = pCommande;
        _command = command;
        _stdout = new ArrayList<String>();

        _error =false ;
    }
    public void run() {
        try {
                printLines(_command + " stderr:", _pCommande.getErrorStream(),this);
                isError(_stdout);
        } catch (Exception ioe) {

        }
    }

    private static void printLines(String name, InputStream ins,Erreur obj) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
            obj._stdout.add(name+ " " + line ) ;
        }
    }

    public boolean isError(List<String> list){
        String errString = list.get(list.size() -1 );
        errString = errString.substring(errString.length()-8,errString.length());
        return  this._error = errString.matches("[0-9]* error(s|)");
    }
}