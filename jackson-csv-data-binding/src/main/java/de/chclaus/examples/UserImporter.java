package de.chclaus.examples;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author Christian Claus (c.claus@micromata.de)
 */
public class UserImporter {

  public static List<User> importUsers(URL url) throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = CsvSchema.emptySchema().withHeader();

    MappingIterator<User> objectMappingIterator = mapper.readerFor(User.class).with(schema).readValues(url);
    return objectMappingIterator.readAll();
  }
}
