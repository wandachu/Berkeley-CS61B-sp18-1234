public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int total = 0;
        while (x <= 9) {
            total = total + x;
            x = x + 1;
            System.out.print(total + " ");
        }
	}
} 
