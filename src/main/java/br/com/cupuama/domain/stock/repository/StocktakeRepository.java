package br.com.cupuama.domain.stock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cupuama.domain.stock.entity.Stocktake;
import br.com.cupuama.exception.InvalidDateRange;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface StocktakeRepository extends CrudRepository<Stocktake, Long> {

	@Query(nativeQuery = true, 
			value = "select st.* " + 
					"from stocktake st " +
					"inner join product p on p.id = st.product_id and p.deleted = false " +
					"inner join fruit f  on f.id = st.fruit_id and f.deleted = false " +
                    "where st.stocktake_date between :start and :end " +
					"and st.deleted = false " +
                    "order by st.stocktake_date, st.stocktake_inout")
	public List<Stocktake> findByDateRangeOrderByStocktakeDate(@Param("start") Date start, @Param("end") Date end) throws InvalidDateRange;
	
}