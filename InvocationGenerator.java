import java.util.Scanner;

public class InvocationGenerator {
    final static String  THREAD_EXE = "threadtopk";
    final static int THREAD_OUT_NUM = 1;

    final static String  PROC_EXE = "proctopk";
    final static int PROC_OUT_NUM = 2;

    final static String   IN_FILE_NAME = "in";
    final static String IN_FILE_FORMAT = ".txt";
    final static String       OUT_FILE = "out";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        String executable = "";
        
        while (!executable.equalsIgnoreCase("P") && !executable.equalsIgnoreCase("T")) {
            System.out.print("Process (P/p) or Thread (T/t)? Answer: ");
            executable = scan.nextLine();

            switch (executable) {
                case "p":
                case "P":
                    System.out.println("Selected: Process.");
                    break;
                case "t":
                case "T":
                    System.out.println("Selected: Thread.");
                    break;
                default:
                    System.out.println("Unrecognized input. Please try again.");
                    break;
            }
        }

        int k = 0;
        while (k == 0) {
            System.out.print("k = ");
            try {
                k = scan.nextInt();
            }
            catch (Exception e) {
                System.out.println("An error has occurred. Perhaps the input was unrecognized. Please try again.");
                k = 0;
            }

            System.out.println("Selected k = " + k + ".");
        }

        int n = 0;
        while (n == 0) {
            System.out.print("n = ");
            try {
                n = scan.nextInt();
            }
            catch (Exception e) {
                System.out.println("An error has occurred. Perhaps the input was unrecognized. Please try again.");
                n = 0;
            }

            System.out.println("Selected n = " + n + ".");
        }

        System.out.println("==========================================================================================================");
        System.out.println("Command: " + generateInvocation(executable, k, n));
        System.out.println("==========================================================================================================");

        scan.close();
    }

    public static String generateInvocation(String executable, int k, int n) {
        String result = "./" + executable + " " + k + " " + OUT_FILE + THREAD_OUT_NUM + " " + n;
        
        for (int i = 0; i < n; i++) {
            result += " " + IN_FILE_NAME + i + IN_FILE_FORMAT;
        }

        return result;
    }
}