package comrueda.productos.productoSimple.resources;

import comrueda.productos.productoSimple.dtos.ProductoDTO;
import comrueda.productos.productoSimple.ejb.ProductoLogic;
import comrueda.common.exceptions.BusinessLogicException;
import comrueda.productos.productoSimple.persistance.ProductoPersistance;
import comrueda.factories.IProductoFactoryEntity;
import comrueda.productos.productoSimple.entities.ProductoEntity;
import comrueda.factories.ProductoImagenEntityFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("productos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProductoResource {

	@Inject
	ProductoLogic productoLogic;
        
        private IProductoFactoryEntity productoFactoryEntity;
        
	private static final Logger LOGGER = Logger.getLogger(ProductoPersistance.class.getName());
        
        public ProductoResource(){
            this.productoFactoryEntity = new ProductoImagenEntityFactory();
        }
        
	@POST
	public ProductoDTO crearProducto(ProductoDTO producto) throws BusinessLogicException {
		ProductoEntity productoEntity = productoFactoryEntity.getProductoEntity(producto);
		ProductoEntity nuevoProducto = productoLogic.crearProducto(productoEntity);
		return new ProductoDTO(nuevoProducto);
	}

	@GET
	public List<ProductoDTO> getProductos() {
		return listEntity2DTO(productoLogic.listarProductos());
	}

	@GET
	@Path("{id: \\d+}")
	public ProductoDTO buscar(@PathParam("id") Long id) {
		ProductoEntity entity = productoLogic.buscar(id);
		if (entity != null) {
			return new ProductoDTO(entity);
		}
		return null;
	}

	@PUT
	@Path("{id: \\d+}")
	public ProductoDTO actualizar(@PathParam("id") Long id, ProductoDTO producto) throws BusinessLogicException {
            producto.setId(id);
            ProductoEntity entity = productoLogic.buscar(id);
            if (entity == null) {
                throw new BusinessLogicException("El recurso /productos/" + id + " no existe.");
            }
            return new ProductoDTO(productoLogic.actualizar(productoFactoryEntity.getProductoEntity(producto)));
	}

	@DELETE
	@Path("{id: \\d+}")
	public void borrar(@PathParam("id") Long id) throws BusinessLogicException {
		LOGGER.log(Level.INFO, "Inicia proceso de borrar un producto con id {0}", id);
        ProductoEntity entity = productoLogic.buscar(id);
		productoLogic.borrar(entity);
	}

	private List<ProductoDTO> listEntity2DTO(List<ProductoEntity> entityList) {
		List<ProductoDTO> list = new ArrayList<ProductoDTO>();
		for (ProductoEntity entity : entityList) {
			list.add(new ProductoDTO(entity));
		}
		return list;
	}
}
