package com.warehouse.controller;


import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.net.http.HttpHeaders;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.warehouse.domain.productos.Producto;
import com.warehouse.domain.utilities.mat;
import com.warehouse.domain.productos.Kardex;
import com.warehouse.dto.KardexDTO;
import com.warehouse.dto.VentasDto;
import com.warehouse.dto.VentasListDTO;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.KardexDTORepository;
import com.warehouse.repository.KardexRepository;
import com.warehouse.repository.VentasRepositoryDto;
import java.time.LocalDate;
import jakarta.validation.Valid;
import java.lang.Math;
@RestController
public class VentasControllerDto {

@Autowired
private VentasRepositoryDto ventasRepository;

@Autowired
private KardexDTORepository kardexRepository;

protected void verifyEquipo(Long ventasId) throws ResourceNotFoundException {
	Optional<VentasDto> ventas = ventasRepository.findById(ventasId);
if(ventas.isPresent() == false) {
throw new ResourceNotFoundException("Ventas con  id No." + ventasId + " NO ENCONTRADO");
}
}

 

// Recuperar todos los equipos	OK
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//@RequestMapping(value="/ventass", method=RequestMethod.GET)

@GetMapping("/ventasDto")
public ResponseEntity<Iterable<VentasDto>> getAllVentass() {
Iterable<VentasDto> allVentas = ventasRepository.findAll();
return new ResponseEntity<>(allVentas, HttpStatus.OK);
}


@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/ventasDtoByfechas")
public ResponseEntity<Iterable<VentasDto>> getAllVentasByFechas(@RequestParam  LocalDate date1,LocalDate date2) {
Iterable<VentasDto> allVentas = ventasRepository.findAllByfechas(date1,date2);
return new ResponseEntity<>(allVentas, HttpStatus.OK); 
}

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@CrossOrigin(origins = "*")
@GetMapping("/ventasDtoByclientefechas")
public ResponseEntity<Iterable<VentasDto>> getAllVentasByClienteFechas(@RequestParam  LocalDate date1,LocalDate date2,Long idproducto) {
Iterable<VentasDto> allVentas = ventasRepository.findAllByClienteFechas(date1,date2,idproducto);
return new ResponseEntity<>(allVentas, HttpStatus.OK); 
}



//Recuperar un equipo en particular	OK
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@GetMapping("/ventasDto/{ventasId}")
public ResponseEntity<?> getVentas(@PathVariable Long ventasId) {
	 
	verifyEquipo(ventasId);
	Optional<VentasDto> e = ventasRepository.findById(ventasId);
	return new ResponseEntity<> (e, HttpStatus.OK); 
}
//Crear un nuevo equipo
@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/nuevaFacturaDto")
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
// ventas con el precio de costo del producto

public ResponseEntity<?> createVentas(@Valid @RequestBody VentasDto venta) {
	double cantidadFacturada = 0;
	long idProducto;
	double costoPresentacion = 0;
	double costoUnidad = 0;
	double precioPresentacion = 0;
	double totalFacturado = 0;
	double saldoCantidad = 0;
	double saldoTotal = 0;
	double saldoCantidad1 = 0;
	double saldoTotal1 = 0;
	long totalRegistros = 0;
	int totalRegistros1 = 0;
	long total = 0;
	double precioUnitario;
	double totalFacturadoCosto = 0;
	double valorParcial = 0;
	Optional<Double> sc = Optional.empty();
	Optional<Double> st = Optional.empty();
	Optional<Long> tr = Optional.empty();
	HttpHeaders responseHeaders = new HttpHeaders();
	venta = ventasRepository.save(venta);
	for(int i=0; i<venta.getProductos_facturados().size();i++)
	{
		System.out.println(venta.getProductos_facturados().get(i).toString());
		cantidadFacturada = venta.getProductos_facturados().get(i).getCantidad_vendida();
		idProducto = (long) venta.getProductos_facturados().get(i).getIdProducto();
		precioPresentacion = venta.getProductos_facturados().get(i).getPrecio_venta();
		costoPresentacion = venta.getProductos_facturados().get(i).getCostoPresentacion();
		costoUnidad = venta.getProductos_facturados().get(i).getCostoUnidad();
		valorParcial = mat.Redondear(costoPresentacion * cantidadFacturada,0);
		// precio venta toca precio compra
		//totalFacturado = venta.getProductos_facturados().get(i).getVr_parcial(); 
		//totalFacturadoCosto = costoPresentacion * cantidadFacturada;
		sc = kardexRepository.saldoCantidad(idProducto, venta.getFec_factura());
		st = kardexRepository.saldoTotal(idProducto, venta.getFec_factura());
		tr = kardexRepository.countRecords(idProducto);
	
		saldoCantidad = !(sc.isPresent()) || sc.isEmpty()  ? 0:sc.get();//sc.isPresent()? sc.get():0;
		saldoTotal = !(st.isPresent()) || st.isEmpty()  || st.isEmpty() ? 0:st.get();//st.isPresent()? sc.get():0;
		totalRegistros = !(tr.isPresent()) || tr.isEmpty()  || tr.isEmpty()?  0:tr.get();//tr.isPresent()? tr.get():0;
		saldoCantidad1 = (double) (saldoCantidad > 0? cantidadFacturada:0);
		saldoTotal1 = (double) (saldoTotal > 0? valorParcial:0);
		totalRegistros1 = (int) (totalRegistros+1);
		System.out.println(saldoCantidad1);
		System.out.println(saldoTotal1);
		System.out.println(totalRegistros1);
		saldoCantidad1 = saldoCantidad - cantidadFacturada;//saldoCantidad1 - cantidadFacturada//totalRegistros == 0? cantidadFacturada:saldoCantidad1 - cantidadFacturada;//saldoCantidad1 - cantidadFacturada;//
		saldoTotal1 = saldoTotal - valorParcial;//totalRegistros == 0? totalFacturado:saldoTotal1 - valorParcial;//saldoTotal1 - totalFacturado;
		//precioUnitario = venta.getProductos_facturados().get(i).getCostoPresentacion();
		//precioUnitario = Math.round(saldoTotal1/saldoCantidad1);
		
		ventasRepository.updateExistenciasProductos0(idProducto,cantidadFacturada);
			KardexDTO asiento = new KardexDTO((int) idProducto, venta.getFec_factura(),
										  "factura de venta "+venta.getId_factura(),
										  -1,valorParcial,cantidadFacturada,
										   0,valorParcial,saldoCantidad1,saldoTotal1,
										   venta.getId_factura(),totalRegistros1, 
										   costoPresentacion );
		
			
			/*KardexDTO(  idProducto, fecha, descripcion,  tipo,  total,
					 cantidad,  debe,  haber,  saldoCantidad,  saldoTotal,  idFactura,
					 indiceManual,idCliente)*/ 
			
			/*KardexDTO(idProducto,fecha,descripcion,tipo,total,cantidad,debe,
		  haber,saldoCantidad,saldoTotal,idFactura,indiceManual)*/
		kardexRepository.save(asiento);			
		//ventasRepository .updateExistenciasProductos2(idProducto);
		}
	URI newVentasUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(venta.getId_factura())
			.toUri();
	responseHeaders.setLocation(newVentasUri);
	// actualizar kardex
	// asiento = new Kardex( venta.ge, LocalDate fecha, String descripcion, double tipo, double total,
	//		double cantidad, double debe, double haber, double cantidadSaldo, double cantidadValor) {
		
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}


//CrossOrigin(origins = "*"); 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/nuevaDevolucionComprasDto") 
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
// ventas con el precio de costo del producto

public ResponseEntity<?> createDevolucionCompras(@RequestParam LocalDate  fechaActual,@RequestParam List<Integer> IdFacturas) {
	double cantidadFacturada = 0;
	long idProducto;
	double costoPresentacion = 0;
	double costoUnidad = 0;
	double precioPresentacion = 0;
	double totalFacturado = 0;
	double saldoCantidad = 0;
	double saldoTotal = 0;
	double saldoCantidad1 = 0;
	double saldoTotal1 = 0;
	long totalRegistros = 0;
	int totalRegistros1 = 0;
	
	double valorParcial = 0;
	Optional<VentasDto> venta = null;
	Optional<Double> sc = Optional.empty();
	Optional<Double> st = Optional.empty();
	Optional<Long> tr = Optional.empty();
	HttpHeaders responseHeaders = new HttpHeaders();
	for (Integer idFactura : IdFacturas)
	{
		venta = ventasRepository.findById( (long) idFactura);
		venta.get().setFec_factura(fechaActual);
	for(int i=0; i<venta.get().getProductos_facturados().size();i++)
	{
		// valores actuales
		cantidadFacturada = venta.get().getProductos_facturados().get(i).getCantidad_vendida();
		idProducto = (long) venta.get().getProductos_facturados().get(i).getIdProducto();
		precioPresentacion = venta.get().getProductos_facturados().get(i).getPrecio_venta();
		//costoPresentacion = venta.get().getProductos_facturados().get(i).getCostoPresentacion();
		costoUnidad = venta.get().getProductos_facturados().get(i).getCostoUnidad();
		//valorParcial = mat.Redondear(costoPresentacion * cantidadFacturada,0);
		valorParcial = venta.get().getProductos_facturados().get(i).getValor_parcial();
		costoPresentacion = valorParcial / cantidadFacturada;
		System.out.println(cantidadFacturada);
		System.out.println(idProducto);
		System.out.println(precioPresentacion);
		System.out.println(costoPresentacion);
		System.out.println(costoUnidad);
		System.out.println(valorParcial);
		// valores inmediatamente anterior
		sc = kardexRepository.saldoCantidad(idProducto, venta.get().getFec_factura());
		st = kardexRepository.saldoTotal(idProducto, venta.get().getFec_factura());
		tr = kardexRepository.countRecords(idProducto);
		saldoCantidad = !(sc.isPresent()) || sc.isEmpty()  ? 0:sc.get();//sc.isPresent()? sc.get():0;
		saldoTotal = !(st.isPresent()) || st.isEmpty()  || st.isEmpty() ? 0:st.get();//st.isPresent()? sc.get():0;
		totalRegistros = !(tr.isPresent()) || tr.isEmpty()  ?  0:tr.get();//tr.isPresent()? tr.get():0;
		saldoCantidad1 = (double) (saldoCantidad > 0? cantidadFacturada:0);
		saldoTotal1 = (double) (saldoTotal > 0? valorParcial:0);
		totalRegistros1 = (int) (totalRegistros+1);
		
		saldoCantidad1 = saldoCantidad - cantidadFacturada;//saldoCantidad1 - cantidadFacturada//totalRegistros == 0? cantidadFacturada:saldoCantidad1 - cantidadFacturada;//saldoCantidad1 - cantidadFacturada;//
		saldoTotal1 = saldoTotal - valorParcial;//totalRegistros == 0? totalFacturado:saldoTotal1 - valorParcial;//saldoTotal1 - totalFacturado;
		ventasRepository.updateExistenciasProductos0(idProducto,cantidadFacturada);
		KardexDTO asiento = new KardexDTO((int) idProducto, venta.get().getFec_factura(),
										  "devolucion compras"+venta.get().getId_factura(),
										  -1,valorParcial,cantidadFacturada,
										   0,valorParcial,saldoCantidad1,saldoTotal1,
										   venta.get().getId_factura(),totalRegistros1, 
										    costoPresentacion);
		kardexRepository.save(asiento);			
		}
	}
	
	
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//CrossOrigin(origins = "*"); 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/nuevaDevolucionVentaDto") 
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
//ventas con el precio de costo del producto

public ResponseEntity<?> createDevolucionVentas(@RequestParam LocalDate  fechaActual,@RequestParam List<Integer> IdFacturas) {
	double cantidadFacturada = 0;
	long idProducto;
	double costoPresentacion = 0;
	double costoUnidad = 0;
	double precioPresentacion = 0;
	double totalFacturado = 0;
	double saldoCantidad = 0;
	double saldoTotal = 0;
	double saldoCantidad1 = 0;
	double saldoTotal1 = 0;
	long totalRegistros = 0;
	int totalRegistros1 = 0;
	
	double valorParcial = 0;
	Optional<VentasDto> venta = null;
	Optional<Double> sc = Optional.empty();
	Optional<Double> st = Optional.empty();
	Optional<Long> tr = Optional.empty();
	HttpHeaders responseHeaders = new HttpHeaders();
	for (Integer idFactura : IdFacturas)
	{
		venta = ventasRepository.findById( (long) idFactura);
		venta.get().setFec_factura(fechaActual);
	for(int i=0; i<venta.get().getProductos_facturados().size();i++)
	{
		// valores actuales
		cantidadFacturada = venta.get().getProductos_facturados().get(i).getCantidad_vendida();
		idProducto = (long) venta.get().getProductos_facturados().get(i).getIdProducto();
		precioPresentacion = venta.get().getProductos_facturados().get(i).getPrecio_venta();
		//costoPresentacion = venta.get().getProductos_facturados().get(i).getCostoPresentacion();
		costoUnidad = venta.get().getProductos_facturados().get(i).getCostoUnidad();
		//valorParcial = mat.Redondear(costoPresentacion * cantidadFacturada,0);
		valorParcial = venta.get().getProductos_facturados().get(i).getValor_parcial();
		costoPresentacion = valorParcial / cantidadFacturada;
		System.out.println(cantidadFacturada);
		System.out.println(idProducto);
		System.out.println(precioPresentacion);
		System.out.println(costoPresentacion);
		System.out.println(costoUnidad);
		System.out.println(valorParcial);
		// valores inmediatamente anterior
		sc = kardexRepository.saldoCantidad(idProducto, venta.get().getFec_factura());
		st = kardexRepository.saldoTotal(idProducto, venta.get().getFec_factura());
		tr = kardexRepository.countRecords(idProducto);
		saldoCantidad = !(sc.isPresent()) || sc.isEmpty()  ? 0:sc.get();//sc.isPresent()? sc.get():0;
		saldoTotal = !(st.isPresent()) || st.isEmpty()  || st.isEmpty() ? 0:st.get();//st.isPresent()? sc.get():0;
		totalRegistros = !(tr.isPresent()) || tr.isEmpty()  ?  0:tr.get();//tr.isPresent()? tr.get():0;
		saldoCantidad1 = (double) (saldoCantidad > 0? cantidadFacturada:0);
		saldoTotal1 = (double) (saldoTotal > 0? valorParcial:0);
		totalRegistros1 = (int) (totalRegistros+1);
		
		saldoCantidad1 = saldoCantidad - cantidadFacturada;//saldoCantidad1 - cantidadFacturada//totalRegistros == 0? cantidadFacturada:saldoCantidad1 - cantidadFacturada;//saldoCantidad1 - cantidadFacturada;//
		saldoTotal1 = saldoTotal - valorParcial;//totalRegistros == 0? totalFacturado:saldoTotal1 - valorParcial;//saldoTotal1 - totalFacturado;
		ventasRepository.updateExistenciasProductos0(idProducto,cantidadFacturada);
		
		KardexDTO asiento = new KardexDTO((int) idProducto, venta.get().getFec_factura(),
				  "devolucion venta "+venta.get().getId_factura(),
				  1,valorParcial,cantidadFacturada,
				  valorParcial,0,saldoCantidad1,saldoTotal1,
				  venta.get().getId_factura(),totalRegistros1,costoPresentacion);
		
		kardexRepository.save(asiento);			
		}
	}
	
	
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/nuevaCompraDto")
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
public ResponseEntity<?> createCompra(@Valid @RequestBody VentasDto venta) {
	double cantidadComprada = 0;
	long idProducto;
	double costoPresentacion = 0;
	double cantidadFacturada = 0;
	double costoUnidad = 0;
	double totalFacturado = 0;
	double saldoCantidad = 0;
	double saldoTotal = 0;
	double saldoCantidad1 = 0;
	double saldoTotal1 = 0;
	long totalRegistros = 0;
	int totalRegistros1 = 0;
	long total = 0;
	double precioUnitario = 0;
	double valorParcial = 0;
	double saldot = 0;
	Optional<Double> sc = Optional.empty();
	Optional<Double> st = Optional.empty();
	Optional<Long> tr = Optional.empty();
	HttpHeaders responseHeaders = new HttpHeaders();
	/*
	 tomar datos actuales
	 consultar datos anteriores
	 actualizar producto 
	 */
	venta = ventasRepository.save(venta);
	for(int i=0; i<venta.getProductos_facturados().size();i++)
	{
		// datos actuales Compra
	
		cantidadFacturada = venta.getProductos_facturados().get(i).getCantidad_vendida();
		idProducto = (long) venta.getProductos_facturados().get(i).getIdProducto();
		costoPresentacion = venta.getProductos_facturados().get(i).getCostoPresentacion();
		totalFacturado = venta.getProductos_facturados().get(i).getVr_parcial();
		costoUnidad = venta.getProductos_facturados().get(i).getCostoUnidad();
		precioUnitario = Math.round(venta.getProductos_facturados().get(i).getCostoPresentacion());
		valorParcial = costoPresentacion * cantidadFacturada;
		sc = kardexRepository.saldoCantidad(idProducto, venta.getFec_factura());
		st = kardexRepository.saldoTotal(idProducto, venta.getFec_factura());
		tr = kardexRepository.countRecords(idProducto);
	
		System.out.println("cantidadFacturada "+cantidadFacturada);
		System.out.println("costoPresentacion "+costoPresentacion);
		// datos anteriores 
		saldoCantidad = !(sc.isPresent()) || sc.isEmpty()  ? 0:sc.get();//sc.isPresent()? sc.get():0;
		saldoTotal = !(st.isPresent()) || st.isEmpty()  || st.isEmpty() ? 0:st.get();//st.isPresent()? sc.get():0;
		totalRegistros = !(tr.isPresent()) || tr.isEmpty()  || tr.isEmpty()?  0:tr.get();//tr.isPresent()? tr.get():0;
		saldoCantidad1 = (double) saldoCantidad + cantidadFacturada;//(saldoCantidad > 0? cantidadFacturada:0);
		saldoTotal1 = (double) saldoTotal + valorParcial;//(saldoTotal > 0? valorParcial:0);
		totalRegistros1 = (int) (totalRegistros);
		System.out.println("saldoTotal "+saldoTotal);
		System.out.println("saldoCantidad "+saldoCantidad); 
		System.out.println("saldoCantidad1 "+saldoCantidad1);
		System.out.println("saldoTotal1 "+saldoTotal1);
		System.out.println("totalRegistros1 "+totalRegistros1);
		//saldoCantidad1 = totalRegistros == 0? cantidadFacturada:saldoCantidad + cantidadFacturada;
		//saldoTotal1 = totalRegistros == 0? valorParcial:saldoTotal + valorParcial;
		precioUnitario = Math.round(saldoTotal1/saldoCantidad1);
		System.out.println(saldoCantidad1);
		System.out.println(saldoTotal1);
		System.out.println(totalRegistros1);
		ventasRepository
		.updateExistenciasProductos3(idProducto,cantidadFacturada,precioUnitario);
		
		KardexDTO asiento = new KardexDTO((int) idProducto,venta.getFec_factura(),
										  "factura de compra "+venta.getId_factura(),
										  1,valorParcial,cantidadFacturada,
										  valorParcial,0,saldoCantidad1,saldoTotal1,
										  venta.getId_factura(),totalRegistros1,precioUnitario);
		
		/*
		 public KardexDTO(  idProducto,  fecha, descripcion,  tipo,  total,
			cantidad, debe, haber, saldoCantidad, saldoTotal,idFactura,
			indiceManual,precioUnitario) {//, double costoUnidad) {

		 */
		 
		/*KardexDTO(idProducto,fecha,descripcion,tipo,total,cantidad,debe,
		  haber,saldoCantidad,saldoTotal,idFactura,indiceManual)*/
		kardexRepository.save(asiento);			
		//ventasRepository .updateExistenciasProductos2(idProducto);
		}
	URI newVentasUri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(venta.getId_factura())
			.toUri();
	/*
	 * for(int i=0; i<venta.getProductos_facturados().size();i++) { idProducto =
	 * (long) venta.getProductos_facturados().get(i).getIdProducto();
	 * cantidadComprada =
	 * venta.getProductos_facturados().get(i).getCantidad_vendida();
	 * costoPresentacion = venta.getProductos_facturados().get(i).getPrecio_venta();
	 * System.out.println(venta.getProductos_facturados().get(i).toString());
	 * ventasRepository
	 * .updateExistenciasProductos3(idProducto,cantidadComprada,costoPresentacion);
	 * //ventasRepository .updateExistenciasProductos2(idProducto);
	 * 
	 * }
	 */
	for(int i=0; i<venta.getProductos_facturados().size();i++)
	{
		
		idProducto = (long) venta.getProductos_facturados().get(i).getIdProducto();
		//cantidadComprada = venta.getProductos_facturados().get(i).getCantidad_vendida();
		//costoPresentacion = venta.getProductos_facturados().get(i).getPrecio_venta();
		//System.out.println(venta.getProductos_facturados().get(i).toString());
		//ventasRepository .updateExistenciasProductos3(idProducto,cantidadComprada,costoPresentacion);
		ventasRepository .updateExistenciasProductos2(idProducto);

	}
	responseHeaders.setLocation(newVentasUri);
	
	return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

@CrossOrigin(origins = "*") 
@PreAuthorize("hasRole('ROLE_ADMIN')")
@PostMapping("/devolucionEnCompraDto")
//@RequestMapping(value="/equipos", method=RequestMethod.POST)
public ResponseEntity<?> devolucionEnCompra( @RequestParam LocalDate  fechaActual, List<Integer> IdFacturas) {
	HttpHeaders responseHeaders = new HttpHeaders();
	Optional<VentasDto> venta = null;

	for (Integer idFactura : IdFacturas) {
        System.out.println(idFactura);
        venta = ventasRepository.findById( (long) idFactura);
        
        createVentas(venta.get());
	
	}
		return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
}

//Actualizar un equipo OK
@RequestMapping(value="/ventasDto/{ventasId}", method=RequestMethod.PUT)
public ResponseEntity<?> updateVentas(@RequestBody VentasDto ventas, @PathVariable Long ventasId) {
	verifyEquipo(ventasId);
	ventasRepository.save(ventas);
	return new ResponseEntity<>(HttpStatus.OK);
}




//Actualizar un equipo OK
@RequestMapping(value="/ventasUpdateDto", method=RequestMethod.PATCH)
public ResponseEntity<?> updateVentas1(@RequestBody VentasListDTO facturas) {
	System.out.println(facturas.getRelacion_facturas().get(0).getId_factura());
	//System.out.println(facturas  facturas().get(1).getId_factura());
	//facturas().get(i).getCantidad_vendida();
	//ventasRepository.save(ventas);
	return new ResponseEntity<>(HttpStatus.OK);
}

//Borrar un equipo OK
@RequestMapping(value="/ventasDto/{ventasId}", method=RequestMethod.DELETE)
public ResponseEntity<?> deleteVentas(@PathVariable Long ventasId) {
	verifyEquipo(ventasId);
	ventasRepository.deleteById(ventasId);
return new ResponseEntity<>(HttpStatus.OK);
}




}