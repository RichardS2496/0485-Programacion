package org.example.shieldsecuritygestor.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Factura {

    private String numeroFactura;
    private String fechaEmision;
    private String fechaVencimiento;
    private double importe;
    private String estadoPago;
}
