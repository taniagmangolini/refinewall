package br.com.usp.lafieco.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.service.interfaces.IFileService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/file")
public class FileController {

	@Autowired
	private IFileService fileService;
	
	//@Autowired
	//private IBlastService blastService;

	@Autowired
	private MessageSource messageSource;
	
	
	@CrossOrigin
	@GetMapping("/blast")
	@ResponseBody
	public String readSucestBlastFile(@RequestParam("sucestGene") String sucestGene) {

		String content = fileService.readSucestBlastFile(sucestGene);

		if (content == null || content.equalsIgnoreCase("")) {
			throw new CustomException(messageSource.getMessage("messages.errorReadFile", new Object[] {sucestGene, ""}, Locale.US));
		}

		return content;
	}
	

/*	@CrossOrigin
	@GetMapping("/read")
	@ResponseBody
	public List<String> readFile(@RequestParam("fileName") String fileName, @RequestParam("folder") String folder) {

		List<String> lines = fileService.readFile(fileName, folder);

		if (lines == null || lines.isEmpty() ) {
			throw new CustomException(messageSource.getMessage("messages.errorReadFile", new Object[] {fileName, folder}, Locale.US));
		}

		return lines;
	}
	
	@CrossOrigin
	@GetMapping("/process")
	@ResponseBody 
	public Map<String, BlastResult>  processBlastResultFile(@RequestParam("gene") String gene, @RequestParam("sequence") String sequence,
			@RequestParam("description") String description,
			@RequestParam("folderName") String  folderName) {
		
		Sucest sucest = null;
		
		if(gene.trim().equalsIgnoreCase("") || sequence.trim().equalsIgnoreCase("") || description.trim().equalsIgnoreCase("")) {
			
			throw new CustomException(messageSource.getMessage("messages.errorSucestDataMissing", new Object[] {gene}, Locale.US));
			
		} else {
			
			sucest = new Sucest();
			sucest.setDescription(description);
			sucest.setGene(gene);
			SucestSequence sucestSequence = new SucestSequence();
			sucestSequence.setSequence(sequence);
			sucestSequence.setSucest(sucest);
			List<SucestSequence> sequences = new ArrayList<SucestSequence>();
			sequences.add(sucestSequence);
			sucest.setSequences(sequences);
		}
		
		Map<String, BlastResult>  mapResult = fileService.processBlastResultFile(gene, folderName);

		if (mapResult == null || mapResult.isEmpty() ) {
			
			throw new CustomException(messageSource.getMessage("messages.errorProcessFile", new Object[] {gene, folderName}, Locale.US));
		}
		
		if(mapResult != null && !mapResult.isEmpty()) {
			
			Iterator it = mapResult.entrySet().iterator();

			for (Map.Entry<String, BlastResult> entry : mapResult.entrySet()) {
				
				blastService.saveBlastResultForSucest(entry.getValue(), sucest);
			}
		}
		return mapResult;
	} */
}
