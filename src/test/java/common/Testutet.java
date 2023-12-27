package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testutet {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");
        List<String> t2 = Arrays.asList(new String[]{"1", "2"});
        t2.add("1");
        t2.add("2");

        int [] test24234 = {1,2};

//        System.out.println("size: " + test.);
        if(t2.get(0).equals(test.get(0))){
            System.out.println("pass");
        }
        if (t2.equals(test))
        System.out.println("pass");
        }
}
