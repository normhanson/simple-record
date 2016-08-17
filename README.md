# simple-record
Simple App the reads in delimited data from file or rest call and outputs in a variety of sorted formats via STDOUT (for CLI) or JSON (via REST) 

## For the impatient ...
```
mvn clean test
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleApp" -Dexec.args="/tmp/simple.pipe /tmp/simple.txt /tmp/simpleData.csv"
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleApp"
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleRestApp"
```


## Prerequisities

What things you need...
```
Java 1.8
Maven
```

### Dependencies 
For JSON serialization
```
https://github.com/google/gson
```

For mini rest server
```
http://sparkjava.com/
```

## Getting Started

To compile and test 
```
mvn clean test
```

To execute stand-alone app and load example data provided
```
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleApp"
```

To execute stand-alone app and load your own data files 
```
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleApp" -Dexec.args="/your/path/to/file1"
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleApp" -Dexec.args="/your/path/to/file1 /your/path/to/file2"
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleApp" -Dexec.args="/your/path/to/file1 /your/path/to/file2 ... /your/path/to/fileN"
```

To start the SimpleRestApp
```
mvn exec:java -Dexec.mainClass="com.hansoncoyne.simple.SimpleRestApp"
```

### Testing SimpleRestApp with curl 
Run the mvn command to start the SimpleRestApp, then in a seprate terminal...

Loading provided sample data
```
curl http://localhost:5678/load
```

Reset the /store/ to have no records
```
curl http://localhost:5678/reset
```

Load data into the store via POST
```
curl -X POST -d 'Dent,Arthur,male,Brown,6/6/1952' http://localhost:5678/records
curl -X POST -d 'Dent|Chuck|Male|Brown|6/6/1965' http://localhost:5678/records
curl -X POST -d 'Smith Jennifer F blue 12/01/1999' http://localhost:5678/records
```

Get records as JSON sorted by gender, then last name
```
curl http://localhost:5678/records/gender
```

Get records as JSON sorted by birthdate
```
curl http://localhost:5678/records/birthdate
```

Get records as JSON sorted by last name descending
```
curl http://localhost:5678/records/name
```

## Authors

* **Norm Hanson** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
