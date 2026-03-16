package MidtermLabWork3.JAVA;

import java.io.*;
import java.util.*;

public class dataset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for dataset file path
        System.out.print("Enter the dataset file path: ");
        String filePath = scanner.nextLine();
        
        try {
            // Read CSV file
            List<String[]> records = readCSV(filePath);
            
            // MP01: Display total number of records
            System.out.println("Total number of records: " + records.size());
            
            // MP02: Display the first 10 rows
            System.out.println("\nFirst 10 rows:");
            for (int i = 0; i < Math.min(10, records.size()); i++) {
                System.out.println(Arrays.toString(records.get(i)));
            }
            
            // MP20: Convert to JSON
            String json = convertToJSON(records);
            System.out.println("\nJSON format:");
            System.out.println(json);
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please check the file path.");
        } catch (IOException e) {
            System.out.println("Error: Unable to read the file.");
        } catch (Exception e) {
            System.out.println("Error: Invalid data or parsing issue.");
        }
        
        scanner.close();
    }
    
    // Function to read CSV file
    // This function reads the CSV file and returns a list of string arrays representing each row
    private static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        String line;
        boolean headerFound = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip lines until header is found
            while ((line = br.readLine()) != null) {
                if (line.contains("Candidate")) {
                    headerFound = true;
                    break;
                }
            }
            if (!headerFound) {
                throw new IOException("Header not found in CSV file.");
            }
            
            // Read data rows
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] fields = parseCSVLine(line);
                    records.add(fields);
                }
            }
        }
        return records;
    }
    
    // Simple CSV parser that handles quoted fields
    // This is a basic parser; for complex CSV, use a library like OpenCSV
    private static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString().trim());
                field.setLength(0);
            } else {
                field.append(c);
            }
        }
        fields.add(field.toString().trim());
        
        return fields.toArray(new String[0]);
    }
    
    // Function to convert records to JSON
    // This creates a simple JSON array of objects
    private static String convertToJSON(List<String[]> records) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        
        for (int i = 0; i < records.size(); i++) {
            String[] record = records.get(i);
            json.append("  {\n");
            json.append("    \"Candidate\": \"").append(record.length > 0 ? escapeJSON(record[0]) : "").append("\",\n");
            json.append("    \"Type\": \"").append(record.length > 1 ? escapeJSON(record[1]) : "").append("\",\n");
            json.append("    \"Column1\": \"").append(record.length > 2 ? escapeJSON(record[2]) : "").append("\",\n");
            json.append("    \"Exam\": \"").append(record.length > 3 ? escapeJSON(record[3]) : "").append("\",\n");
            json.append("    \"Language\": \"").append(record.length > 4 ? escapeJSON(record[4]) : "").append("\",\n");
            json.append("    \"Exam Date\": \"").append(record.length > 5 ? escapeJSON(record[5]) : "").append("\",\n");
            json.append("    \"Score\": \"").append(record.length > 6 ? escapeJSON(record[6]) : "").append("\",\n");
            json.append("    \"Result\": \"").append(record.length > 7 ? escapeJSON(record[7]) : "").append("\",\n");
            json.append("    \"Time Used\": \"").append(record.length > 8 ? escapeJSON(record[8]) : "").append("\"\n");
            json.append("  }");
            if (i < records.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        
        json.append("]");
        return json.toString();
    }
    
    // Helper to escape JSON strings
    private static String escapeJSON(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
