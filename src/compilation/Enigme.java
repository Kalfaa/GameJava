package compilation;

import java.util.ArrayList;

public class Enigme {

    private String _function_name ;

    private String _enonce;

    private ArrayList _arglistType;

    private ArrayList _test1;

    private ArrayList _test2;

    private ArrayList _test3;

     Enigme(String function_name,String enonce , ArrayList arglist){
        _function_name = function_name;
        _enonce = enonce;
        _arglistType = arglist;
    }

    public String get_function_name() {
        return _function_name;
    }

    public void set_function_name(String _function_name) {
        this._function_name = _function_name;
    }

    public String get_enonce() {
        return _enonce;
    }

    public void set_enonce(String _enonce) {
        this._enonce = _enonce;
    }

    public ArrayList get_arglistType() {
        return _arglistType;
    }

    public void set_arglistType(ArrayList _arglistType) {
        this._arglistType = _arglistType;
    }

    public ArrayList get_test1() {
        return _test1;
    }

    public void set_test1(ArrayList _test1) {
        this._test1 = _test1;
    }

    public ArrayList get_test2() {
        return _test2;
    }

    public void set_test2(ArrayList _test2) {
        this._test2 = _test2;
    }

    public ArrayList get_test3() {
        return _test3;
    }

    public void set_test3(ArrayList _test3) {
        this._test3 = _test3;
    }

    public boolean isValidTest(ArrayList test){
        if(test.size() == _arglistType.size()){
            for(int i=0; i<_arglistType.size();i++){
                if( ! (test.get(i).getClass() == _arglistType.get(i).getClass()) ){
                    return false ;
                }
            }
        }
        return false ;
    }
}
