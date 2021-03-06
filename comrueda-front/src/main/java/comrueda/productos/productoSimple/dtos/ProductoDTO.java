package comrueda.productos.productoSimple.dtos;

import comrueda.productos.productoSimple.entities.ProductoEntity;
import comrueda.productos.dtos.interfaces.IProductoDTO;

public class ProductoDTO implements IProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;

    public ProductoDTO() {
    }

    public ProductoDTO(ProductoEntity producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.cantidad = producto.getCantidad();
        this.precio = producto.getPrecio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getImagen(){
        return "";
    }
    
    public void setImagen(String imagen){
        
    }
}
