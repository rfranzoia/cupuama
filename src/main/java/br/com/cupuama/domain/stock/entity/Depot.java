package br.com.cupuama.domain.stock.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.com.cupuama.util.Audit;
import br.com.cupuama.util.AuditableEntity;

@Entity
@Table(name = "depot", uniqueConstraints = @UniqueConstraint(name = "uc_depot_name", columnNames = { "name" }))
public class Depot implements AuditableEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "Name cannot be null!")
	private String name;
	
	@Column(nullable = false)
	private boolean keepStock;

	public Depot() {
	}
	
	public Depot(Long id, String name, boolean keepStock) {
		this.id = id;
		this.name = name;
		this.keepStock = keepStock;
		this.audit = new Audit();
		this.audit.setDeleted(false);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isKeepStock() {
		return keepStock;
	}

	public void setKeepStock(boolean keepStock) {
		this.keepStock = keepStock;
	}

	@Embedded
	private Audit audit;

	@Override
	public Audit getAudit() {
		return this.audit;
	}

	@Override
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Depot other = (Depot) obj;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Depot [id=" + id + ", name=" + name + ", keepStock=" + keepStock + "]";
	}
	
	@Override
	public Depot clone() {
		return new Depot(id, name, keepStock);
	}
	
	
}
