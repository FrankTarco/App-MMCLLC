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
@Table(name="tb_personal")
@Getter
@Setter
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_personal;
	private String numero_cheque;
	private String nombre_persona;
	private double cantidad;
	private String concepto;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
	private Date fecha;
	
}
