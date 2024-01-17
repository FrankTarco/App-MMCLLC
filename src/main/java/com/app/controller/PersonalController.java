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
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Personal;
import com.app.services.PersonalService;
import com.app.utils.AppSettings;

@RestController
@RequestMapping("/api/personal")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class PersonalController {

	@Autowired
	private PersonalService service;
	
	@GetMapping
	public ResponseEntity<?>listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping
	public ResponseEntity<?>registrar(@RequestBody Personal obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			Personal verificar = service.buscarPorCheque(obj.getNumero_cheque());
			
			if(verificar != null) {
				salida.put("status", false);
				salida.put("mensaje", "El personal con cheque "+ obj.getNumero_cheque() + " ya fue registrado");
			}
			else {
				Personal registro = service.registrarActualizar(obj);
				if(registro != null) {
					salida.put("status", true);
					salida.put("mensaje", "Se registro correctamente al personal");
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
	public ResponseEntity<?>actualizar(@RequestBody Personal obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		try {
			Personal verificar = service.buscarPorId(obj.getId_personal());
			
			if(verificar == null) {
				salida.put("status", false);
				salida.put("mensaje", "El id proporcionado no existe en la base de datos");
			}
			else {
				Personal registro_actualizado = service.registrarActualizar(obj);
				if(registro_actualizado != null) {
					salida.put("status", true);
					salida.put("mensaje", "Se actualizo correctamente al personal");
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
			
			Personal verificar = service.buscarPorId(id);
			
			if(verificar == null) {
				salida.put("status", false);
				salida.put("mensaje", "El id proporcionado no existe en la base de datos");
			}
			else {				
				service.eliminar(id);
				salida.put("status", true);
				salida.put("mensaje", "Se elimino correctamente al personal");
			}
			
			
		} catch (Exception e) {
			salida.put("status", false);
			salida.put("mensaje", e.getMessage());
		}
		
		
		return ResponseEntity.ok(salida);
		
	}
	
	
}
