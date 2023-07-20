package org.ffernandez.apiservlet.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
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
}