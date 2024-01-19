package com.app.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Gastos;
import com.app.services.GastosService;
import com.app.utils.AppSettings;


@RestController
@RequestMapping("/api/gastos")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class GastosController {

	@Autowired
	private GastosService service;
	
	@GetMapping
	public ResponseEntity<?>listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping
	public ResponseEntity<?>registrar(@RequestBody Gastos obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			Gastos verificar = service.gastosPorBoleta(obj.getNumero_boleta());
			
			if(verificar != null) {
				salida.put("status", false);
				salida.put("mensaje", "El gasto con numero de boleta "+ obj.getNumero_boleta() + " ya fue registrado");
			}
			else {
				Gastos registro = service.registrarActualizar(obj);
				if(registro != null) {
					salida.put("status", true);
					salida.put("mensaje", "Se registro el gasto correctamente");
				}
				else {
					salida.put("status", false);
					salida.put("mensaje", "No se pudo realizar el registro, intentelo denuevo");
				}
			}
			
			
		} catch (Exception e) {
			salida.put("status", false);
			salida.put("mensaje", e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
		
	}
	
	
	@PutMapping
	public ResponseEntity<?>actualizar(@RequestBody Gastos obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			Gastos verificar = service.gastosPorId(obj.getId_gastos());
			
			if(verificar == null) {
				salida.put("status", false);
				salida.put("mensaje", "El id proporcionado no existe en la base de datos");
			}
			else {
				Gastos registro_actualizado = service.registrarActualizar(obj);
				if(registro_actualizado != null) {
					salida.put("status", true);
					salida.put("mensaje", "Se actualizo el gasto correctamente");
				}
				else {
					salida.put("status", false);
					salida.put("mensaje", "No se pudo realizar la actualización, intentelo denuevo");
				}
			}
			
			
		} catch (Exception e) {
			salida.put("status", false);
			salida.put("mensaje", e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>eliminar(@PathVariable("id")int id){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			
			Gastos verificar = service.gastosPorId(id);
			
			if(verificar == null) {
				salida.put("status", false);
				salida.put("mensaje", "El id proporcionado no existe en la base de datos");
			}
			else {				
				service.eliminar(id);
				salida.put("status", true);
				salida.put("mensaje", "Se elimino el gasto correctamente");
			}
			
			
		} catch (Exception e) {
			salida.put("status", false);
			salida.put("mensaje", e.getMessage());
		}
		
		
		return ResponseEntity.ok(salida);
		
	}
	
	@GetMapping("/consulta")
	public ResponseEntity<?>consultaDinamicaIngreso(@RequestParam(name = "nombre" , required = false, defaultValue = "") String nombre,
			@RequestParam(name = "anio" , required = false, defaultValue = "") String anio){
		
		return ResponseEntity.ok(service.consultaDinamica("%"+ nombre +"%", anio));
	}
	
	
}
