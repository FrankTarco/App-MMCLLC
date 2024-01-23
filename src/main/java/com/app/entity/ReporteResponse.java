package com.app.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteResponse {

	private String titulo;
	private List<String>cabezera;
	private List<Object>data;
	private double total;
	private String type;
	
}
