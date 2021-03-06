package br.com.cupuama.domain.processing.mapper;

import br.com.cupuama.domain.processing.dto.ProcessFlowTypeKey;
import br.com.cupuama.domain.processing.entity.ProcessFlowTypeId;
import br.com.cupuama.domain.products.mapper.ProductMapper;


public class ProcessFlowTypeKeyMapper {
	public static ProcessFlowTypeId makeId(ProcessFlowTypeKey key) {
		return new ProcessFlowTypeId(ProcessTypeMapper.makeProcessType(key.getProcessType()), 
										ProductMapper.makeProduct(key.getProduct()), 
										key.getStocktakeInOut());
	}

	public static ProcessFlowTypeKey makeKey(ProcessFlowTypeId id) {
		return new ProcessFlowTypeKey(ProcessTypeMapper.makeDTO(id.getProcessType()), 
										ProductMapper.makeDTO(id.getProduct()), 
										id.getStocktakeInOut());
	}

}
