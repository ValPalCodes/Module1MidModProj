package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

public class App {

    // The regex string to split the Strings in the dataset.
    private static final String FIELD_DELIMITER = "\\|";

    private static final int TITLE_FIELD = 0;
    private static final int AUTHOR_FIELD = 1;
    private static final int PUBLISHED_YEAR_FIELD = 2;
    private static final int PRICE_FIELD = 3;

    private final Scanner keyboard = new Scanner(System.in);

    private List<String> titles = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<Integer> publishedYears = new ArrayList<>();
    private List<BigDecimal> prices = new ArrayList<>();

    public static void main(String[] args) {

        App app = new App();
        app.loadData();
        app.run();

    }

    private void loadData() {

        String[] dataset = Dataset.load();

        for (String line : dataset) {
            String[] fields = line.split(FIELD_DELIMITER);
            titles.add(fields[0]);
            authors.add(fields[1]);
            publishedYears.add(Integer.parseInt(fields[2]));
            prices.add(new BigDecimal(fields[3]));
        }

        /*
         Requirement: 1
         Populate the instance variables `titles`, `authors`, `publishedYears`,
         and `prices` by splitting each string in the `dataset` array and adding
         the individual fields to the appropriate list.
         See README for additional details.
         */

    }

    private void run() {

        while (true) {
            // Main menu loop
            printMainMenu();
            int mainMenuSelection = promptForMenuSelection("Please choose an option: ");
            if (mainMenuSelection == 1) {
                // Display data and subsets loop
                while (true) {
                    printDataAndSubsetsMenu();
                    int dataAndSubsetsMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (dataAndSubsetsMenuSelection == 1) {
                        displayDataset(Dataset.load());
                    } else if (dataAndSubsetsMenuSelection == 2) {
                        displayTitlesList(titles);
                    } else if (dataAndSubsetsMenuSelection == 3) {
                        displayAuthorsList(authors);
                    } else if (dataAndSubsetsMenuSelection == 4) {
                        displayPublishedYearsList(publishedYears);
                    } else if (dataAndSubsetsMenuSelection == 5) {
                        displayPricesList(prices);
                    } else if (dataAndSubsetsMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 2) {
                while (true) {
                    printSearchBooksMenu();
                    int searchBooksMenuSelection = promptForMenuSelection("Please choose an option: ");
                    int primaryField = promptForMenuSelection("Enter Primary Field: ");
                    while(primaryField < 0 || primaryField > 3) {

                        System.out.println("Error: Primary Field must be a number between 0 - 3");
                        primaryField = promptForMenuSelection("Enter Primary Field: ");
                    }
                    if (searchBooksMenuSelection == 1) {
                        // Search by title
                        String filterTitle = promptForString("Enter title: ");
                        /*
                         Requirement: 3b
                         Replace `displayTitlesList(titles)` with calls to the
                         `filterByTitle()` and `displaySearchResults()` methods.
                         */

                        displaySearchResults(filterByTitle(filterTitle), primaryField);

                    } else if (searchBooksMenuSelection == 2) {
                        // Search by author
                        String filterAuthor = promptForString("Enter author: ");
                        /*
                         Requirement: 4b
                         Replace `displayAuthorsList(authors)` with calls to the
                         `filterByAuthor()` and `displaySearchResults()` methods.
                         */

                        displaySearchResults(filterByAuthor(filterAuthor), primaryField);

                    } else if (searchBooksMenuSelection == 3) {
                        // Search by published year
                        int filterYear = promptForPublishedYear("Enter date (YYYY): ");
                        /*
                         Requirement: 5b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYear()` and `displaySearchResults()` methods.
                         */

                        displaySearchResults(filterByPublishedYear(filterYear), primaryField);

                    } else if (searchBooksMenuSelection == 4) {
                        // Search by published year range
                        int filterFromYear = promptForPublishedYear("Enter \"from\" date (YYYY): ");
                        int filterToYear = promptForPublishedYear("Enter \"to\" date (YYYY): ");
                        /*
                         Requirement: 6b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYearRange()` and `displaySearchResults()` methods.
                         */

                        displaySearchResults(filterByPublishedYearRange(filterFromYear, filterToYear), primaryField);

                    } else if (searchBooksMenuSelection == 5) {
                        // Find the most recent books
                        /*
                         Requirement: 7b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `findMostRecentBooks()` and `displaySearchResults()` methods.
                         */

                        displaySearchResults(findMostRecentBooks(), primaryField);

                    } else if (searchBooksMenuSelection == 6) {
                        // Search by price
                        double filterPrice = promptForPrice("Enter price: ");
                        /*
                         Requirement: 8b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPrice()` and `displaySearchResults()` methods.
                         */

                        displaySearchResults(filterByPrice(filterPrice), primaryField);

                    } else if (searchBooksMenuSelection == 7) {
                        // Search by price range
                        double filterFromPrice = promptForPrice("Enter \"from\" price: ");
                        double filterToPrice = promptForPrice("Enter \"to\" price: ");
                        /*
                         Requirement: 9b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPriceRange()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(filterByPriceRange(filterFromPrice, filterToPrice), primaryField);

                    } else if (searchBooksMenuSelection == 8) {
                        // Find the least expensive books
                        /*
                         Requirement: 10b
                         Replace `displayPricesList(prices)` with calls to the
                         `findLeastExpensiveBooks()` and `displaySearchResults()` methods.
                         */

                        displaySearchResults(findLeastExpensiveBooks(), primaryField);

                    } else if (searchBooksMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 0) {
                break;
            }
        }

    }

    /*
     Requirement: 2
     Write the displaySearchResults(List<Integer> indexes) method.
     See README for additional details.
     */

    private void displaySearchResults(List<Integer> indexes, int primaryField) {
        sortSearchResults(indexes, primaryField);
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);

            if(primaryField == TITLE_FIELD) {
                System.out.println(titles.get(index) + " :" + " " + authors.get(index) + " :" + " " + publishedYears.get(index) + " :" + " " + "$" + prices.get(index));
            } else if (primaryField == AUTHOR_FIELD) {
                System.out.println(authors.get(index) + ":" + " " + titles.get(index) + ":" + " " + publishedYears.get(index) + ":" + " " + "$" + prices.get(index));
            } else if (primaryField == PUBLISHED_YEAR_FIELD) {
                System.out.println(publishedYears.get(index) + ":" + " " + titles.get(index) + ":" + " " + authors.get(index) + ":" + " " + "$" + prices.get(index));
            } else if (primaryField == PRICE_FIELD) {
                System.out.println("$" + prices.get(index) + ":" + " " + titles.get(index) + ":" + " " + authors.get(index) + ":" + " " + publishedYears.get(index));
            }
        }
    }

    /*
     Requirement: 3a
     Complete the `filterByTitle()` method.
     See README for additional details.
     */

    private List<Integer> filterByTitle(String filterTitle) {
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).toLowerCase().contains(filterTitle.toLowerCase())) {
                indexList.add(i);
            }
        }
        return indexList;
    }

    /*
     Requirement: 4a
     Complete the `filterByAuthor()` method.
     See README for additional details.
     */
    private List<Integer> filterByAuthor(String filterAuthor) {
        List<Integer> authorList = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).toLowerCase().contains(filterAuthor.toLowerCase())) {
                authorList.add(i);
            }
        }
        return authorList;
    }

    /*
     Requirement: 5a
     Complete the `filterByPublishedYear()` method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYear(int filterYear) {
        List<Integer> publishedYearList = new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i).equals(filterYear)) {
                publishedYearList.add(i);
            }
        }
        return publishedYearList;
    }

    /*
     Requirement: 6a
     Complete the `filterByPublishedYearRange()` method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYearRange(int filterFromYear, int filterToYear) {
        List<Integer> publishedYearRangeList = new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) >= (filterFromYear) && publishedYears.get(i) <= (filterToYear)) {
                publishedYearRangeList.add(i);
            }
        }
        return publishedYearRangeList;
    }

    /*
     Requirement: 7a
     Add the `private List<Integer> findMostRecentBooks()` method.
     See README for additional details.
     */
    private List<Integer> findMostRecentBooks() {
        List<Integer> mostRecentBooks = new ArrayList<>();
        int mostRecent = publishedYears.get(0);
        for (int i = 1; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) > mostRecent) {
                mostRecent = publishedYears.get(i);
            }
        }
        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) == mostRecent) {
                mostRecentBooks.add(i);
            }
        }
        return mostRecentBooks;

    }

    /*
     Requirement: 8a
     Complete the `filterByPrice()` method.
     See README for additional details.
     */
    private List<Integer> filterByPrice(double filterPrice) {
        List<Integer> byPrice = new ArrayList<>();
        BigDecimal priceNew = BigDecimal.valueOf(filterPrice);
        for(int i = 0; i < prices.size(); i++) {
            if(prices.get(i).compareTo(priceNew) <= 0) {
                byPrice.add(i);
            }
        }
        return byPrice;
    }

    /*
     Requirement: 9a
     Complete the `filterByPriceRange()` method.
     See README for additional details.
     */
    private List<Integer> filterByPriceRange(double filterFromPrice, double filterToPrice) {
        List<Integer> priceRange = new ArrayList<>();
        BigDecimal low = BigDecimal.valueOf(filterFromPrice);
        BigDecimal high = BigDecimal.valueOf(filterToPrice);
        for(int i = 0; i < prices.size(); i++) {
            if(prices.get(i).compareTo(low) >= 0 && prices.get(i).compareTo(high) <= 0) {
                priceRange.add(i);
            }
        }
        return priceRange;


    }

    /*
     Requirement: 10a
     Add the `private List<Integer> findLeastExpensiveBooks()` method.
     See README for additional details.
     */

    private List<Integer> findLeastExpensiveBooks() {
        List<Integer> leastExpensiveBooks = new ArrayList<>();
        BigDecimal low = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i).compareTo(low) < 0) {
                low = prices.get(i);
            }
        }
        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).compareTo(low) == 0) {
                leastExpensiveBooks.add(i);
            }
        }
        return leastExpensiveBooks;
    }

    private void sortSearchResults(List<Integer> ids, int primaryField) {
        int indexLimit = ids.size();

        if (primaryField == PUBLISHED_YEAR_FIELD) {
            for (int limit = ids.size(); limit > 0; limit--) {
                boolean didSwap = false;
                for(int i = 0; i < limit - 1; i++) {
                    if(publishedYears.get(ids.get(i)) > publishedYears.get(ids.get(i + 1))) {
                        int originalIValue = ids.get(i);
                        ids.set(i, ids.get(i + 1));
                        ids.set(i + 1, originalIValue);
                        didSwap = true;
                    }
                }
                if (!didSwap) {
                    break;
                }
            }
        }

        if (primaryField == TITLE_FIELD) {
            for (int limit = ids.size(); limit > 0; limit--) {
                boolean didSwap = false;
                for(int i = 0; i < limit - 1; i++) {
                    if ((titles.get(ids.get(i))).compareTo(titles.get(ids.get(i + 1))) > 0) {
                        int originalIValue = ids.get(i);
                        ids.set(i, ids.get(i + 1));
                        ids.set(i + 1, originalIValue);
                        didSwap = true;
                    }
                }
                if (!didSwap) {
                    break;
                }
            }
        }

        if (primaryField == AUTHOR_FIELD) {
            for (int limit = ids.size(); limit > 0; limit--) {
                boolean didSwap = false;
                for(int i = 0; i < limit - 1; i++) {
                    if ((authors.get(ids.get(i))).compareTo(authors.get(ids.get(i + 1))) > 0) {
                        int originalIValue = ids.get(i);
                        ids.set(i, ids.get(i + 1));
                        ids.set(i + 1, originalIValue);
                        didSwap = true;
                    }
                }
                if (!didSwap) {
                    break;
                }
            }
        }

        if (primaryField == PRICE_FIELD) {
            for (int limit = ids.size(); limit > 0; limit--) {
                boolean didSwap = false;
                for(int i = 0; i < limit - 1; i++) {
                    if ((prices.get(ids.get(i))).compareTo(prices.get(ids.get(i + 1))) > 0) {
                        int originalIValue = ids.get(i);
                        ids.set(i, ids.get(i + 1));
                        ids.set(i + 1, originalIValue);
                        didSwap = true;
                    }
                }
                if (!didSwap) {
                    break;
                }
            }
        }
    }



// UI methods

    private void printMainMenu() {
        System.out.println("1: Display data and subsets");
        System.out.println("2: Search books");
        System.out.println("0: Exit");
        System.out.println();
    }

    private void printDataAndSubsetsMenu() {
        System.out.println("1: Display dataset");
        System.out.println("2: Display titles");
        System.out.println("3: Display authors");
        System.out.println("4: Display published years");
        System.out.println("5: Display prices");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void printSearchBooksMenu() {
        System.out.println("1: Search by title");
        System.out.println("2: Search by author");
        System.out.println("3: Search by published year");
        System.out.println("4: Search by published year range");
        System.out.println("5: Find most recent books");
        System.out.println("6: Search by price");
        System.out.println("7: Search by price range");
        System.out.println("8: Find least expensive books");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void displayDataset(String[] dataset) {
        System.out.println("Dataset");
        System.out.println("-------");
        for (String data : dataset) {
            System.out.println(data);
        }
        System.out.println();
        promptForReturn();
    }

    private void displayTitlesList(List<String> titles) {
        System.out.println("Titles");
        System.out.println("-------");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + ": " + titles.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayAuthorsList(List<String> authors) {
        System.out.println("Authors");
        System.out.println("-------");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(i + ": " + authors.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPublishedYearsList(List<Integer> publishedYears) {
        System.out.println("Published Years");
        System.out.println("---------------");
        for (int i = 0; i < publishedYears.size(); i++) {
            System.out.println(i + ": " + publishedYears.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPricesList(List<BigDecimal> prices) {
        System.out.println("Prices");
        System.out.println("------");
        for (int i = 0; i < prices.size(); i++) {
            System.out.println(i + ": " + prices.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private int promptForMenuSelection(String prompt) {
        System.out.print(prompt);
        int menuSelection;
        try {
            menuSelection = Integer.parseInt(keyboard.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    private String promptForString(String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private int promptForPublishedYear(String prompt) {
        int year;
        while (true) {
            System.out.println(prompt);
            try {
                year = Integer.parseInt(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The year provided is not well-formed. It must be YYYY.");
            }
        }
        return year;
    }

    private double promptForPrice(String prompt) {
        double price;
        while (true) {
            System.out.println(prompt);
            try {
                price = Double.parseDouble(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The price provided is not a valid monetary value.");
            }
        }
        return price;
    }

    private void promptForReturn() {
        System.out.println("Press RETURN to continue.");
        keyboard.nextLine();
    }
}
