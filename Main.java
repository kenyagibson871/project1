import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {

            ArrayList<Process> processes =
                    ProcessReader.readProcesses(
                            "../input/processes1.txt"
                    );

            System.out.println("Processes loaded successfully:");
            System.out.println();

            for (Process p : processes) {

                System.out.println(
                        p.getPid() + " " +
                        p.getArrivalTime() + " " +
                        p.getBurstTime() + " " +
                        p.getPriority()
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}