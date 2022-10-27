import java.util.Scanner;

public class PositiveOrNegative {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if ((x < 0 && y < 0) || (x > 0 && y > 0)) {
            System.out.println("true");
        } else if (x == 0 || y ==0)
            System.out.println("false");
        else {
            System.out.println("false");
        }
    }
}
