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
import br.com.usp.lafieco.service.interfaces.IUniprotService;
import br.com.usp.lafieco.vo.UniprotVO;

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
	public UniprotVO getUniprot(@RequestParam("idProtein") String idProtein) {

		UniprotVO uniprot = uniprotService.getUniprot(idProtein);

		if (uniprot == null ) {
			throw new CustomException(messageSource.getMessage("messages.errorUniprot", new Object[] {idProtein}, Locale.US));
		}

		return uniprot;
	}
}
