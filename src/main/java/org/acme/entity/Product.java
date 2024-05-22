package org.acme.entity;

import java.util.List;

import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {
	
	String productName;
	List<Api> apiList;

}
