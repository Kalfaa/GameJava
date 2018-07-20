package compilation;

import compilation.packagecompile.Hint;
import compilation.packagecompile.Joker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParsing {

    public static boolean findJokerAnnot(String methodname , String classpath) {
        try {
            for (Method method : AnnotationParsing.class.getClassLoader()
                    .loadClass(classpath).getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(Joker.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '" + method + "' : " + anno);
                        }
                        return true ;
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }

        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static int findHintAnnot(String methodname,String classpath) {
        try {
            for (Method method : AnnotationParsing.class.getClassLoader()
                    .loadClass(classpath).getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(Hint.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '" + method + "' : " + anno);
                        }
                        Hint hint = method.getAnnotation(Hint.class);
                        if (hint.test()>0 && hint.test()<3) {
                            return hint.test();
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }

        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

}