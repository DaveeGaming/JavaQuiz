import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.err.println("No input file given");
            System.exit(1);
        }

        String filename = args[0];
        File file = new File(filename);

        if (!file.exists()) {
            System.err.println("File does not exist");
            System.exit(1);
        }

        List<Integer> numbers = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            numbers.add(num);
        }

        LinkedList<Integer> sorted_numbers = new LinkedList<>(numbers);
        for (int i = 0; i < numbers.size(); i++) {
            int num = numbers.get(i);

            if (num == 0) continue;

            int moves = num;
            int current_idx = sorted_numbers.indexOf(num);

            sorted_numbers.remove(current_idx);
            while (moves > 0) {
                current_idx++;
                if (current_idx >= numbers.size() - 1) {
                    current_idx = current_idx - (numbers.size() - 1);
                }
                moves--;
            }

            while (moves < 0) {
                current_idx--;
                if (current_idx <= 0) {
                    current_idx = current_idx + (numbers.size() - 1);
                }
                moves++;
            }

            sorted_numbers.add(current_idx, num);
        }
        System.out.println(String.join(", ", sorted_numbers.stream().map(Object::toString).toList()));
    }
}

