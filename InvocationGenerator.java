import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class InvocationGenerator {
    final static String  THREAD_EXE = "threadtopk";
    final static int THREAD_OUT_NUM = 1;

    final static String  PROC_EXE = "proctopk";
    final static int PROC_OUT_NUM = 2;

    final static String   IN_FILE_NAME = "in";
    final static String IN_FILE_FORMAT = ".txt";
    final static String       OUT_FILE = "out";

    final static String PREFIX_COMMAND = "time "; // Must add a space after the command

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        
        String executable = "";
        
        while (
            !executable.equalsIgnoreCase("P")
            && !executable.equalsIgnoreCase("T")
            && !executable.equals(THREAD_EXE)
            && !executable.equals(PROC_EXE)
            ) {
            System.out.print("Process (P/p) or Thread (T/t)? Answer: ");
            executable = scan.nextLine();

            switch (executable) {
                case "p":
                case "P":
                    executable = PROC_EXE;
                    System.out.println("Selected: Process.");
                    break;
                case "t":
                case "T":
                    executable = THREAD_EXE;
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

        System.out.println("Generating...");

        PrintWriter write = new PrintWriter(new File(OUT_FILE + IN_FILE_FORMAT));
        write.println("==========================================================================================================");
        for (int i = 1; i <= n; i++) {
            write.println(PREFIX_COMMAND + generateInvocation(executable, k, i));
        }
        write.println("==========================================================================================================");

        System.out.println("Done!");
        write.close();
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