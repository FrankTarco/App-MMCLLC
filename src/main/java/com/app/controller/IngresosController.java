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

import com.app.entity.Ingresos;
import com.app.services.IngresosService;
import com.app.utils.AppSettings;

@RestController
@RequestMapping("/api/ingresos")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class IngresosController {

	@Autowired
	private IngresosService service;
	
	@GetMapping
	public ResponseEntity<?>listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping
	public ResponseEntity<?>registrar(@RequestBody Ingresos obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			Ingresos verificar = service.buscarPorCheque(obj.getNumero_cheque());
			
			if(verificar != null) {
				salida.put("status", false);
				salida.put("mensaje", "El ingreso de cheque "+ obj.getNumero_cheque() + " ya fue registrado");
			}
			else {
				Ingresos registro = service.registrarActualizar(obj);
				if(registro != null) {
					salida.put("status", true);
					salida.put("mensaje", "Se registro correctamente al ingreso");
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
	public ResponseEntity<?>actualizar(@RequestBody Ingresos obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			Ingresos verificar = service.buscarPorId(obj.getId_ingresos());
			
			if(verificar == null) {
				salida.put("status", false);
				salida.put("mensaje", "El id proporcionado no existe en la base de datos");
			}
			else {
				Ingresos registro_actualizado = service.registrarActualizar(obj);
				if(registro_actualizado != null) {
					salida.put("status", true);
					salida.put("mensaje", "Se actualizo correctamente al ingreso");
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
			
			Ingresos verificar = service.buscarPorId(id);
			
			if(verificar == null) {
				salida.put("status", false);
				salida.put("mensaje", "El id proporcionado no existe en la base de datos");
			}
			else {				
				service.eliminar(id);
				salida.put("status", true);
				salida.put("mensaje", "Se elimino correctamente al ingreso");
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
