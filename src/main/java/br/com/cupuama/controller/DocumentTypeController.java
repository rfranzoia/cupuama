package br.com.cupuama.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cupuama.controller.mapper.DocumentTypeMapper;
import br.com.cupuama.domain.cashflow.DocumentType;
import br.com.cupuama.dto.DocumentTypeDTO;
import br.com.cupuama.exception.ConstraintsViolationException;
import br.com.cupuama.exception.EntityNotFoundException;
import br.com.cupuama.service.cashflow.DocumentTypeService;

/**
 * All operations with a documentType will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("/v1/documentTypes")
public class DocumentTypeController {

	private final DocumentTypeService documentTypeService;

	@Autowired
	public DocumentTypeController(final DocumentTypeService documentTypeService) {
		this.documentTypeService = documentTypeService;
	}

	@GetMapping("/{documentTypeId}")
	public DocumentTypeDTO getDocumentType(@PathVariable final long documentTypeId) throws EntityNotFoundException {
		return DocumentTypeMapper.makeDocumentTypeDTO(documentTypeService.find(documentTypeId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DocumentTypeDTO createDocumentType(@Valid @RequestBody final DocumentTypeDTO documentTypeDTO) throws ConstraintsViolationException {
		DocumentType documentType = DocumentTypeMapper.makeDocumentType(documentTypeDTO);
		return DocumentTypeMapper.makeDocumentTypeDTO(documentTypeService.create(documentType));
	}

	@DeleteMapping("/{documentTypeId}")
	public void deleteDocumentType(@PathVariable final long documentTypeId) throws EntityNotFoundException {
		documentTypeService.delete(documentTypeId);
	}

	@PutMapping("/{documentTypeId}")
	public void updateLocation(@PathVariable final long documentTypeId, @RequestParam final String name)
			throws EntityNotFoundException {
		documentTypeService.update(documentTypeId, name);
	}

	@GetMapping("/name/{name}")
	public List<DocumentTypeDTO> findDocumentTypesByName(@PathVariable final String name) {
		return DocumentTypeMapper.makeDocumentTypeDTOList(documentTypeService.findByName(name));
	}

	@GetMapping
	public List<DocumentTypeDTO> findAllDocumentTypes() {
		return DocumentTypeMapper.makeDocumentTypeDTOList(documentTypeService.findAll());
	}
}
