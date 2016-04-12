package net.d80harri.coach.domain.exercise;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name="Flow")
public class Flow extends Exercise {
	private List<FlowItem> items;
	
	@OneToMany(mappedBy="flow")
	public List<FlowItem> getItems() {
		return items;
	}
	public void setItems(List<FlowItem> items) {
		this.items = items;
	}
}
