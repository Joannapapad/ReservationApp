package com.example.reservation;

import java.util.ArrayList;
import java.util.List;

public class SearchStore {
    private List<Store> allStores;

    // Constructor
    public SearchStore(List<Store> stores) {
        this.allStores = stores;
    }

    // Filter by store name
    public List<Store> searchByName(String name) {
        List<Store> result = new ArrayList<>();
        for (Store store : allStores) {
            if (store.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(store);
            }
        }
        return result;
    }

    // Filter by location
    public List<Store> filterByLocation(String location) {
        List<Store> result = new ArrayList<>();
        for (Store store : allStores) {
            if (store.getLocation().toLowerCase().contains(location.toLowerCase())) {
                result.add(store);
            }
        }
        return result;
    }

    // Filter by type
    public List<Store> filterByType(String type) {
        List<Store> result = new ArrayList<>();
        for (Store store : allStores) {
            if (store.getType().toLowerCase().equals(type.toLowerCase())) {
                result.add(store);
            }
        }
        return result;
    }

    // Display stores in a list
    public void displayStores(List<Store> stores) {
        if (stores.isEmpty()) {
            System.out.println("No stores found.");
            return;
        }
        for (Store store : stores) {
            System.out.println(store + "\n");
        }
    }
}
