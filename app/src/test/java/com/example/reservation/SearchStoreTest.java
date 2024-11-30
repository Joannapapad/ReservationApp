package com.example.reservation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class SearchStoreTest {

    private SearchStore searchStore;
    private List<Store> stores;

    @Before
    public void setUp() {
        // Initialize store data
        stores = new ArrayList<>();
        stores.add(new Store(1001, "Benjamin", "123 Mesogeivn", "Agia Paraskeui", "Burger Restaurant", 50, 10));
        stores.add(new Store(1002, "La Pasteria", "456 Vasilisis Sofias", "Center, Attica", "Italian", 30, 5));
        stores.add(new Store(1003, "Juicy Grill", "778 Xolargos", "Xolargos, Attica", "Burger Shop", 60, 12));
        stores.add(new Store(1004, "Butchers", "101 Chalandri", "Chalandri, Attica", "Burger Shop", 40, 8));

        // Initialize SearchStore
        searchStore = new SearchStore(stores);
    }

    @Test
    public void testSearchByName() {
        List<Store> result = searchStore.searchByName("Benjamin");
        assertEquals(1, result.size());
        assertEquals("Benjamin", result.get(0).getName());

        result = searchStore.searchByName("Juicy");
        assertEquals(1, result.size());
        assertEquals("Juicy Grill", result.get(0).getName());

        result = searchStore.searchByName("NotExist");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFilterByLocation() {
        List<Store> result = searchStore.filterByLocation("Agia Paraskeui");
        assertEquals(1, result.size());
        assertEquals("Benjamin", result.get(0).getName());

        result = searchStore.filterByLocation("Attica");
        assertEquals(3, result.size());
        assertTrue(result.stream().anyMatch(store -> store.getName().equals("La Pasteria")));
        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Juicy Grill")));
        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Butchers")));

        result = searchStore.filterByLocation("NonExistent");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFilterByType() {
        List<Store> result = searchStore.filterByType("Burger Restaurant");
        assertEquals(1, result.size());
        assertEquals("Benjamin", result.get(0).getName());

        result = searchStore.filterByType("Burger Shop");
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Juicy Grill")));
        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Butchers")));

        result = searchStore.filterByType("Italian");
        assertEquals(1, result.size());
        assertEquals("La Pasteria", result.get(0).getName());

        result = searchStore.filterByType("NonExistent");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCaseInsensitiveSearch() {
        List<Store> result = searchStore.searchByName("benjamin");
        assertEquals(1, result.size());
        assertEquals("Benjamin", result.get(0).getName());

        result = searchStore.filterByLocation("agia paraskeui");
        assertEquals(1, result.size());
        assertEquals("Benjamin", result.get(0).getName());

        result = searchStore.filterByType("burger shop");
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Juicy Grill")));
        assertTrue(result.stream().anyMatch(store -> store.getName().equals("Butchers")));
    }

    @Test
    public void testEmptyStoreList() {
        SearchStore emptySearchStore = new SearchStore(new ArrayList<>());

        List<Store> result = emptySearchStore.searchByName("AnyName");
        assertTrue(result.isEmpty());

        result = emptySearchStore.filterByLocation("AnyLocation");
        assertTrue(result.isEmpty());

        result = emptySearchStore.filterByType("AnyType");
        assertTrue(result.isEmpty());
    }
}
