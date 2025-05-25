

import org.testng.annotations.DataProvider;

public class DataSearch {

    @DataProvider(name = "searchData")
    public Object[][] provideSearchData() {
        return new Object[][] {
                // Format: {searchTerm, expectedMinResults, description}

                // Positive test cases
                {"Tshirt", 1, "Partial match for Tshirts"},
                {"Blue Top", 1, "Exact product name"},
                {"blue top", 1, "Case insensitive search"},
                {"Men", 2, "Category term"},
                {"sleeve", 1, "Partial word match"},

                // Negative test cases
                {"XYZ123", 0, "Non-existent product"},
                {"@#$%", 0, "Special characters"},
                {"", 34, "Empty search (should show all)"}, // 34 is total products

                // Edge cases
                {" ", 34, "Whitespace search"},
                {"T", 5, "Single character search"},
                {"Premium Polo T-Shirts For Men - All Colors Available", 0, "Long description"}
        };
    }
}