package de.chclaus.examples;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Christian Claus (c.claus@micromata.de)
 */
public class UserImporter {

  /**
   * Imports a list of users of a stored csv-file using jackson csv-data-binding.
   * @param url the url to the csv.
   * @return a list of users.
   * @throws IOException if it's not possible to read the csv-file.
   */
  public static List<User> importUsersWithJackson(URL url) throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = CsvSchema.emptySchema().withHeader();

    MappingIterator<User> objectMappingIterator = mapper.readerFor(User.class).with(schema).readValues(url);
    return objectMappingIterator.readAll();
  }


  /**
   * Imports a list of users of a stored csv-file using apache-commons-csv.
   * @param url the url to the csv.
   * @return a list of users.
   * @throws IOException if it's not possible to read the csv-file.
   */
  public static List<User> importUsersWithApacheCsv(URL url) throws IOException {
    Reader in = new FileReader(url.getFile());
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

    return StreamSupport.stream(records.spliterator(), false)
        .skip(1) // Skip the header line.
        .map(record -> { // Transform each entry.
          String username = record.get(0);
          String password = record.get(1);
          String email = record.get(2);

          return new User(username, password, email);
        })
        .collect(Collectors.toList()); // Collect the users to a list.
  }
}
