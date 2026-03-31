# Assignment 6 - Insurance Company Automation

This project generates personalized email and letter messages for customers based on a CSV file and templates.

## How to run

Example (email only):

--email --email-template src/test/resources/email-template.txt --output-dir out --csv-file src/test/resources/insurance-company-members.csv

Example (email + letter):

--email --email-template src/test/resources/email-template.txt --letter --letter-template src/test/resources/letter-template.txt --output-dir out --csv-file src/test/resources/insurance-company-members.csv

## Project Structure

- `src/main/java/assignment6` → main implementation  
- `src/test/java/assignment6` → test classes  
- `src/test/resources` → templates and CSV file  

## Features

- Parse command line arguments  
- Read CSV data  
- Replace placeholders in templates  
- Generate personalized output files  
