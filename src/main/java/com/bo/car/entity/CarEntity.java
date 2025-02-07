package com.bo.car.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@DynamicInsert
@Table(name="car")
public class CarEntity {
	@Id
	@Column(length=30)
	private String id;
	
	@Column(nullable=false)
	private String carNo;
	
	@Column(nullable=false)
	private String carType;
	
	@Column
	private String maxNum; 
	
	@Column(precision=12, scale=6)
	private BigDecimal latitude;
	
	@Column(precision=12, scale=6)
	private BigDecimal longitude;
	
//	@Column(nullable=false, length=2)
//	@ColumnDefault("0") 
//	private Long status;
	
	public void modifyCarLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}	
	public void modifyCarLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}	
}
