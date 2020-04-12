package br.com.cupuama.domain.processing.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cupuama.domain.processing.dto.SupplierDTO;
import br.com.cupuama.domain.processing.mapper.SupplierMapper;
import br.com.cupuama.domain.processing.service.SupplierService;
import br.com.cupuama.exception.ConstraintsViolationException;
import br.com.cupuama.exception.EntityNotFoundException;

/**
 * All operations with a supplier will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("/v1/suppliers")
public class SupplierController {

	private final SupplierService supplierService;

	@Autowired
	public SupplierController(final SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@GetMapping("/{supplierId}")
	public SupplierDTO getSupplier(@PathVariable final long supplierId) throws EntityNotFoundException {
		return SupplierMapper.makeSupplierDTO(supplierService.find(supplierId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SupplierDTO createSupplier(@Valid @RequestBody final SupplierDTO supplierDTO) throws ConstraintsViolationException {
		return supplierService.create(supplierDTO);
	}

	@DeleteMapping("/{supplierId}")
	public void deleteSupplier(@PathVariable final long supplierId) throws EntityNotFoundException {
		supplierService.delete(supplierId);
	}

	@PutMapping("/{supplierId}")
	public void updateLocation(@PathVariable final long supplierId, @RequestBody final SupplierDTO supplierDTO) throws EntityNotFoundException {
		supplierService.update(supplierId, supplierDTO);
	}

	@GetMapping("/name/{name}")
	public List<SupplierDTO> findDocumentTypesByName(@PathVariable final String name) {
		return SupplierMapper.makeSupplierDTOList(supplierService.findByName(name));
	}
	
	@GetMapping
	public List<SupplierDTO> findAllSuppliers() {
		return SupplierMapper.makeSupplierDTOList(supplierService.findAll());
	}
}
