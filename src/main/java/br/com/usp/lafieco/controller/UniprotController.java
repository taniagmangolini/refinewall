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

import br.com.usp.lafieco.bean.org.uniprot.uniprot.Uniprot;
import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.service.interfaces.IUniprotService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/uniprot")
public class UniprotController {

	@Autowired
	private IUniprotService uniprotService;

	@Autowired
	private MessageSource messageSource;

	@CrossOrigin
	@GetMapping
	@ResponseBody
	public Uniprot runBlast(@RequestParam("idProtein") String idProtein) {

		Uniprot geneProduct = uniprotService.getUniprot(idProtein);

		System.out.println("Blast job: " + geneProduct);

		if (geneProduct == null ) {
			throw new CustomException(messageSource.getMessage("messages.errorGeneProductNotFound", new Object[] {idProtein}, Locale.US));
		}

		return geneProduct;
	}
}