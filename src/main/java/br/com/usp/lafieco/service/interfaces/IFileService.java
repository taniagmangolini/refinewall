package br.com.usp.lafieco.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

	List<String> processMultipleSequenceFile(MultipartFile file) ;
}
