package eb.vesna.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Elena_Bogomolova on 12.12.2016.
 */
public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Phyton", "PHP"};

        List<String> languages = Arrays.asList("Java", "C#", "Phyton", "PHP");
       // languages.add("Java");
       // languages.add("C#");

        for (String l : langs) {
            System.out.println("Я хочу изучить " + l);
        }

        System.out.println(" ");

        for (int i = 0; i < languages.size(); i++) {
            System.out.println("Я хочу выучить " + languages.get(i));
        }

        /*String[] langs = new String[4];
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Phyton";
        langs[3] = "PHP";*/

    }
}
