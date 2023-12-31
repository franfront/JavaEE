package org.ffernandez.apiservlet.webapp.headers.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.ffernandez.apiservlet.webapp.headers.configs.CarroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


//@SessionScoped
//@Named("carro")
@CarroCompra
public class Carro implements Serializable {
    private List<ItemCarro> items;

    @Inject
    private transient Logger log; // transient para que no se serialice

    @PostConstruct // se ejecuta despues de que se crea el objeto
    public void inicializar(){
        this.items = new ArrayList<>();
        log.info("Inicializando el carro");
    }


    @PreDestroy // se ejecuta antes de que se destruya el objeto
    public void destruir(){
        log.info("Destruyendo el carro");
    }


    public void agregarItem(ItemCarro itemCarro) {

        if(items.contains(itemCarro)){
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(item -> item.equals(itemCarro))
                    .findAny();

            if(optionalItemCarro.isPresent()){
                ItemCarro item = optionalItemCarro.get();
                item.setCantidad(item.getCantidad() + 1);
            }
        }else{
            items.add(itemCarro);
        }


    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream()
                .mapToInt(ItemCarro::getTotal) // obtiene el total de cada item
                .sum();
    }

    public void removeProductos(List<String> productoIds){
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
            // que es lo mismo a:
            // productoIds.forEach(productoId -> removeProducto(productoId));
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId()))) // filtramos por id
                .findAny();
    }
}
