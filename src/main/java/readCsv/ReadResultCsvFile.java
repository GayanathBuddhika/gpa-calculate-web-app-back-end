package readCsv;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.gpastm.gpa.model.Result;

import modelConverter.ResultConverter;

public class ReadResultCsvFile {

	 public static <Student> List<ResultConverter> read(Class<ResultConverter> clazz, InputStream stream) throws IOException {
			
	    	CsvMapper mapper = new CsvMapper();
	        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
	        ObjectReader reader = mapper.readerFor(clazz).with(schema);
	        return reader.<ResultConverter>readValues(stream).readAll();
	    }
}
