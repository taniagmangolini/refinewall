package br.com.usp.lafieco.service.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.service.interfaces.IFileService;

@Component
public class FileService implements IFileService {

	@Autowired
	private MessageSource messageSource;
	
	private static String SEQUENCE_SEPARATOR = ",";
	
	public List<String> processMultipleSequenceFile(MultipartFile file) {

		List<String> sequences = new ArrayList<String>();

		try {
			
			InputStream inputFS = file.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputFS));
			String line;
			StringBuilder fileContent = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
	        }
			
			 String[] sequencesArray = fileContent.toString().split(SEQUENCE_SEPARATOR);
			 
			 sequences.addAll(Arrays.asList(sequencesArray));
			 
			reader.close();
		} catch (IOException e) {
			throw new CustomException(messageSource.getMessage("messages.errorInputFile", new Object[] {}, Locale.US));
		}

		return sequences;
	}


}
