import java.io.*;
import java.util.ArrayList;

public class ProcessReader {

    public static ArrayList<Process> readProcesses(String filename)
            throws IOException {

        ArrayList<Process> processes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        )) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                
                lineNumber++;
                
                // Remove extra spaces
                line = line.trim();
                
                // Ignore blank lines
                if (line.isEmpty()) {
                    continue;
                }
                
                // Ignore the optional header
                if (line.toLowerCase().startsWith("pid")) {
                    continue;
                }
                
                // Split the line into separate fields
                String[] parts = line.split("\\s+");
                
                // We need exactly 4 fields
                if (parts.length != 4) {
                    reader.close();
                    
                    throw new IllegalArgumentException(
                            "Invalid number of fields on line "
                                    + lineNumber
                    );
                }
                
                String pid = parts[0];
                
                int arrivalTime;
                int burstTime;
                int priority;
                
                try {
                    
                    arrivalTime = Integer.parseInt(parts[1]);
                    burstTime = Integer.parseInt(parts[2]);
                    priority = Integer.parseInt(parts[3]);
                    
                } catch (NumberFormatException e) {
                    
                    reader.close();
                    
                    throw new IllegalArgumentException(
                            "Invalid number on line "
                                    + lineNumber
                    );
                }
                
                // Arrival time cannot be negative
                if (arrivalTime < 0) {
                    
                    reader.close();
                    
                    throw new IllegalArgumentException(
                            "Arrival time cannot be negative: "
                                    + pid
                    );
                }
                
                // Burst time must be positive
                if (burstTime <= 0) {
                    
                    reader.close();
                    
                    throw new IllegalArgumentException(
                            "Burst time must be positive: "
                                    + pid
                    );
                }
                
                Process process = new Process(
                        pid,
                        arrivalTime,
                        burstTime,
                        priority
                );
                
                processes.add(process);
            }
        }

        return processes;
    }
}
