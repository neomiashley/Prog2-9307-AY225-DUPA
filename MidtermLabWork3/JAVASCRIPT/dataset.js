const fs = require('fs');
const readline = require('readline');

// Create readline interface for user input
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Function to prompt user for dataset file path
// This function asks the user to enter the path to the CSV file
rl.question('Enter the dataset file path: ', (filePath) => {
    try {
        // Read the CSV file synchronously
        // This reads the entire file into a string for processing
        const csvData = fs.readFileSync(filePath, 'utf8');

        // Parse CSV data into records
        // This function splits the CSV into lines and parses each line
        const records = parseCSV(csvData);

        // MP01: Display total number of records
        // This shows the count of data rows (excluding header)
        console.log(`Total number of records: ${records.length}`);

        // MP02: Display the first 10 rows
        // This loops through the first 10 records and prints them
        console.log('\nFirst 10 rows:');
        for (let i = 0; i < Math.min(10, records.length); i++) {
            console.log(records[i]);
        }

        // MP20: Convert to JSON format
        // This converts the array of records to a JSON string
        const jsonData = convertToJSON(records);
        console.log('\nJSON format:');
        console.log(jsonData);

    } catch (error) {
        // Handle possible errors during file reading or processing
        if (error.code === 'ENOENT') {
            console.log('Error: File not found. Please check the file path.');
        } else {
            console.log('Error: Unable to read or process the file.');
        }
    }

    // Close the readline interface
    rl.close();
});

// Function to parse CSV data
// This function takes the raw CSV string and converts it to an array of objects
function parseCSV(csvString) {
    const lines = csvString.split('\n');
    const records = [];
    let headerFound = false;

    // Skip lines until header is found
    for (let line of lines) {
        if (line.includes('Candidate')) {
            headerFound = true;
            break;
        }
    }

    if (!headerFound) {
        throw new Error('Header not found in CSV file.');
    }

    // Process data rows
    for (let i = lines.indexOf(lines.find(line => line.includes('Candidate'))) + 1; i < lines.length; i++) {
        const line = lines[i].trim();
        if (line) {
            const record = parseCSVLine(line);
            records.push(record);
        }
    }

    return records;
}

// Function to parse a single CSV line
// This handles quoted fields and commas within quotes
function parseCSVLine(line) {
    const fields = [];
    let field = '';
    let inQuotes = false;

    for (let i = 0; i < line.length; i++) {
        const char = line[i];
        if (char === '"') {
            inQuotes = !inQuotes;
        } else if (char === ',' && !inQuotes) {
            fields.push(field.trim());
            field = '';
        } else {
            field += char;
        }
    }
    fields.push(field.trim());

    return fields;
}

// Function to convert records to JSON
// This creates a JSON array of objects with predefined keys
function convertToJSON(records) {
    const jsonArray = records.map(record => ({
        "Candidate": record[0] || "",
        "Type": record[1] || "",
        "Column1": record[2] || "",
        "Exam": record[3] || "",
        "Language": record[4] || "",
        "Exam Date": record[5] || "",
        "Score": record[6] || "",
        "Result": record[7] || "",
        "Time Used": record[8] || ""
    }));

    return JSON.stringify(jsonArray, null, 2);
}
