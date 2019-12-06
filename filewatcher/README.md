filepoller
#This is a small project representing a simple API in order to develop simple file processor.

#It consists of :

- Implementing the Line interface in order to define the object to encapsulate each line in the parsed file.
- Extending the AbstractFileProcessor class and implementing the process method in order to plug specific treatment for each line while parsing.
- The api presents a specific file parser for the specific CSV file type.
- Extending the CSVFileParser, and implement the newLine method in order to create the specific line object type.
- Configure in the file config.properties, the directory path to be watched.
- Configure the specific processor bean by overriding the filePattern attribute and fileParser attribute.
- Configure the specific file parser bean, which would be referenced from the processor.