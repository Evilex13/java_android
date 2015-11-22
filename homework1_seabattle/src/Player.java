import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alexander Tsoi  on 27.09.15.
 */
public class Player {

//    int getShoot(String s) {
//        int shoot;
//
//        Scanner scanner = new Scanner(System.in);
//        do {
//            System.out.print(s);
//            if (scanner.hasNextInt()) {
//                shoot = scanner.nextInt(); // обрез бумаги
//                break;
//            } else {
//                System.out.println("введите все же число, плиз");
//                scanner.nextLine(); // выборосили
//            }
//        } while (true);
//        return shoot;
//    }

    int getShootRow() {
        return getShoot("Ряд: ");
    }

    int getShootColumn() {
        return getShoot("Столбец: ");
    }

    int getShoot(String s){
        int shoot = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print(s);
            try {
                String s1 = reader.readLine();
                if (Character.isAlphabetic(s1.charAt(0)))
                    for (int i = 0; i < Field.ALPHABET.length; i++) {
                        if (Field.ALPHABET[i].equalsIgnoreCase(s1)) shoot = i;
                    }
                else shoot = Integer.parseInt(s1) - 1;
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (true);
        return shoot;
    }
}
