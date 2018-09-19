package br.com.usp.lafieco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.lafieco.service.interfaces.IRefineService;
import br.com.usp.lafieco.vo.RefineResultVO;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/refine")
public class RefineController {

	@Autowired
	private IRefineService refineService;

	@Autowired
	private MessageSource messageSource;

	@CrossOrigin
	@GetMapping("/sequence")
	@ResponseBody
	public RefineResultVO refineBySequence(@RequestParam("sequence") String sequence,
			@RequestParam("email") String email) {
		
		RefineResultVO refineResult = refineService.refineSequence(sequence, email);
		
		return refineResult;
	}
	
}
