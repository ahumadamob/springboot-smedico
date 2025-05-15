

package com.imb2025.smedico.service;


import java.util.List;
import com.imb2025.smedico.entity.DetalleFactura;

public interface IDetalleFacturaService {

    public List<DetalleFactura> findAll();
	public DetalleFactura findById(Long id);
	public DetalleFactura save(DetalleFactura detalleFactura);
	public void deleteById(Long id);
    
}
                    