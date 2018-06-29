import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {

        String result ="";
       ArrayList<Object> _list = new ArrayList<Object>();
       _list.add(2);
       _list.add("2");
       _list.add( Arrays.asList("A", new Object(), "C"));
       for(Object object : _list){
           result += object.toString();
       }
        System.out.print(result);
    }

}
