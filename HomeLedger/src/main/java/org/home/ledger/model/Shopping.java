package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name="shopping_type")
@Table
public class Shopping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopping_type_id")
	private int shoppingTypeId;
	@Column(name = "shopping_type_name")
	@ColumnTransformer(read = "UPPER(shopping_type_name)")
	private  String shoppingTypeName;
	
	public Shopping() {
		
	}

	public Shopping(String shoppingTypeName) {
		this.shoppingTypeName = shoppingTypeName;
	}

	public int getShoppingTypeId() {
		return shoppingTypeId;
	}

	public void setShoppingTypeId(int shoppingTypeId) {
		this.shoppingTypeId = shoppingTypeId;
	}

	public String getShoppingTypeName() {
		return shoppingTypeName;
	}

	public void setShoppingTypeName(String shoppingTypeName) {
		this.shoppingTypeName = shoppingTypeName;
	}

	@Override
	public String toString() {
		return "Shopping [shoppingTypeId=" + shoppingTypeId + ", shoppingTypeName=" + shoppingTypeName + "]";
	}
	
	public static Comparator<Shopping> shoppingComparator = new Comparator<Shopping>() {

		@Override
		public int compare(Shopping s1, Shopping s2) {
			String shoppingTypeName1 = s1.getShoppingTypeName();
			String shoppingTypeName2 = s2.getShoppingTypeName();
			return shoppingTypeName1.compareTo(shoppingTypeName2);
		}
	};
}
