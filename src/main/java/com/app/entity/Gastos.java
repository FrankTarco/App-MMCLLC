package com.app.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_gastos")
@Getter
@Setter
public class Gastos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_gastos;
	private String nombre_tienda;
	private String numero_boleta;
	private double cantidad;
	private String concepto;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date fecha;
	
}
