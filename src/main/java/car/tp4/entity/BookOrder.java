package car.tp4.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BookOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany
	private List<OrderEntry> orderEntries;

	public BookOrder() {
		orderEntries = new ArrayList<OrderEntry>();
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public List<OrderEntry> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(final List<OrderEntry> orderEntries) {
		this.orderEntries = orderEntries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((orderEntries == null) ? 0 : orderEntries.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BookOrder other = (BookOrder) obj;
		if (id != other.id)
			return false;
		if (orderEntries == null) {
			if (other.orderEntries != null)
				return false;
		} else if (!orderEntries.equals(other.orderEntries))
			return false;
		return true;
	}

}