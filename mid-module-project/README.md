# Module One mid-module project

The Module One mid-module project is an opportunity to apply the programming skills and knowledge you've learned so far at the halfway point of the module. In particular, you'll demonstrate the following:

* Declaring and assigning values to variables, including constants
* Writing conditional code using boolean expressions
* Using arrays, collections, and loops
* Parsing strings into numeric data types
* Using methods of the `String` class for text processing and manipulation

## Application

The design of the application is to allow users to search through a body of data containing books: title, author, year published, and price. For instance, users can search for a particular title or author, a range of prices, or a year of publication. The application displays results of the search on the console.

The [Requirements](#requirements) section later in this document describes the specific searches and format of the results.

## Starting code

Begin by opening the Module One mid-module project in IntelliJ and reviewing the starting code.

The starting code provides you with a menu user interface that handles user input, displays unfiltered data, and calls to methods in the application.

The Module One mid-module project is a small application with only two classes:

* `Dataset.java` - The data used within the application
* `App.java` - The "main" class of the application

### `Dataset.java`

The data is a hardcoded array of strings in the `Dataset` class. The class has a single `load()` method which returns the hardcoded `String` array. **The code in this class is complete and you shouldn't modify them.**

### `App.java`

The `App` class contains two primary methods:

* `loadData()` - Loads the `String` array by calling `Dataset.load()`. You'll transform the single array of strings to collections of different data types. The [Requirements](#requirements) section describes this in greater detail.
* `run()` - The run-loop which is responsible for the application menus and search results display.

The `main()` method of the `App` class calls both of these methods. In other words, when the application starts.

Immediately following these two methods, you'll see several "stubbed-out" methods beginning with `filter...()`. You'll implement the logic for these methods as described in the [Requirements](#requirements) section.

Following the `filter...()` methods you'll find more methods whose names begin with `print...()`, `display...()`, and `prompt...()`. These comprise the application UI and called on from within the run-loop. You won't need to change any of this code, but you can feel free to browse it. The method names are descriptive of their purpose. **These methods are complete and you shouldn't modify them.**

## Requirements

There are a number of requirements you need to complete. They're grouped under data transformation and searching.

In the code there are comments that begin with `Requirement:` and the requirement number that indicates where your code goes to complete the requirement.

### Data transformation

![Hardcoded dataset](img/string-array.png)

A closer examination of the dataset reveals that each string in the array contains four fields: title, author, publication year, and price. They're separated by a pipe character (`|`).

While it's possible to just search within each `String` as a whole, there are potential problems. For instance, assuming a case-insensitive search, looking for authors named "hal" would find three books:

* Two for authors "*Hal* Clement" and "*Hal* Foster"
* One for the title "Ex*hal*ation"

A better approach is to separate the fields for each `String` into four `ArrayList`s, one for each of the four fields: `titles`, `authors`, `publishedYears`, and `prices`, then search the appropriate list. So, if the user is searching for authors named "hal", you'd search the `authors` list to ensure that there isn't any unwanted matches returned.

![Separated lists](img/split-array-into-lists.png)

To relate the data between lists, you'll use the element index to link them. For example, if "hal" matches in the `authors` list at indexes 3 and 7, you can use the same index to relate the titles, years of publication, and prices in the other lists. Continuing with the "hal" example, index 3 in the `titles` list is "Mission of Gravity" (by Hal Clement) and index 7 is "Prince Valiant, Vol. 1: 1937-1938" (by Hal Foster).

>Note: these indexes are for demonstration purposes. The actual indexes in your application may be different.

**Requirement: 1**

Add code where indicated to populate the instance variables `titles`, `authors`, `publishedYears`, and `prices` by splitting each string row-by-row in the `dataset` array and adding the individual fields to their respective list. *Make sure to keep the values in sync across the lists as shown in the diagram.*

>Recommendation: Use the provided `FIELD_DELIMITER` constant when splitting.

When you run the application, you can use option 1 on the main menu "Display data and subsets" to print the main dataset and list the subset collections that you populate.

### Searching

In programming, there are three parts to any search: the values you search for, finding the matches, and displaying the results.

The provided code already handles the input of search values. You'll add code that finds the matches and displays the results.

Your search must allow for partial matches and be case-insensitive for strings. In this application, a search happens on a single list at a time—`titles`, `authors`, `publishedYears`, or `prices`—depending on the method called. The search returns a list of the indexes that match the search value.

>Note: Any search may yield multiple matches so a list of the found indexes is necessary even if there is only one match.

When you run the application, you can use option 2 on the main menu "Search books" to perform the searches.

**Requirement: 2**

In order to display the search results, add the following method where indicated in code:

```java
private void displaySearchResults(List<Integer> indexes)
```

The method displays the full information on the console:

`<title>: <author>: <publication year>: $<price>`

for each index in the list of indexes.

For example, the output for the authors found previously:

```
Mission of Gravity: Hal Clement: 1954: $18.63
Prince Valiant, Vol. 1: 1937-1938: Hal Foster: 2009: $44.14
```

>Note: Your search results may appear in any order.

**Requirement: 3a**

Complete the `filterByTitle()` method to find matching titles:

```java
filterByTitle(String filterTitle)
```

>Note: The run-loop has code that prompts for the `filterTitle`.

**Requirement: 3b**

Replace `displayTitlesList(titles)` with calls to `filterByTitle()` and `displaySearchResults()` methods to search for `filterTitle` and display the results.

**Requirement: 4a**

Complete the `filterByAuthor()` method to find matching authors:

```java
filterByAuthor(String filterAuthor)
```

>Note: The run-loop has code that prompts for the `filterAuthor`.

**Requirement: 4b**

Replace `displayAuthorsList(authors)` with calls to the `filterByAuthor()` and `displaySearchResults()` methods to search for `filterAuthor` and display the results.

**Requirement: 5a**

Complete the `filterByPublishedYear()` method to find matching published years:

```java
filterByPublishedYear(int filterYear)
```

>Note: The run-loop has code that prompts for the `filterYear`.

**Requirement: 5b**

Replace `displayPublishedYearsList(publishedYears)` with calls to the `filterByPublishedYear()` and `displaySearchResults()` methods to search for `filterYear` and display the results.

**Requirement: 6a**

Complete the `filterByPublishedYearRange()` method to find books that are between two published years:

```java
filterByPublishedYearRange(int filterFromYear, int filterToYear)
```

>Note: The run-loop has code that prompts for the `filterFromYear` and `filterToYear`.

**Requirement: 6b**

Replace `displayPublishedYearsList(publishedYears)` with calls to the `filterByPublishedYearRange()` and `displaySearchResults()` methods to search `filterFromYear` to `filterToYear` and display the results.

**Requirement: 7a**

Add this method to find the index of the book or books with the most recent published year:

```java
private List<Integer> findMostRecentBooks()
```

>Hint: The method first needs to determine the latest year of publication within the published years list before finding the most recent books.

**Requirement: 7b**

Replace `displayPublishedYearsList(publishedYears)` with calls to the `findMostRecentBooks()` and `displaySearchResults()` methods to search and display the results.

**Requirement: 8a**

Complete the `filterByPrice()` method to find books less than or equal to the searched price:

```java
filterByPrice(double filterPrice)
```

>Note: The run-loop has code that prompts for the `filterPrice`.

**Requirement: 8b**

Replace `displayPricesList(prices)` with calls to the `filterByPrice()` and `displaySearchResults()` methods to search for `filterPrice` and display the results.

**Requirement: 9a**

Complete the `filterByPriceRange()` method to find books between two prices:

```java
filterByPriceRange(double filterFromPrice, double filterToPrice)
```

>Note: The run-loop has code that prompts for the `filterFromPrice` and `filterToPrice`.

**Requirement: 9b**

Replace `displayPricesList(prices)` with calls to the `filterByPriceRange()` and `displaySearchResults()` methods to search `filterFromPrice` to `filterToPrice` and display the results.

**Requirement: 10a**

Add the method to find the index of the book or books with the lowest price:

```java
private List<Integer> findLeastExpensiveBooks()
```

>Hint: The method first needs to determine the lowest price within the prices list before finding the least expensive books.

**Requirement: 10b**

Replace `displayPricesList(prices)` with calls to the `findLeastExpensiveBooks()` and `displaySearchResults()` methods to search and display the results.

## Challenges

The following challenges are optional, and *you must only attempt them after all the requirements are complete*.

**Challenge: 1**

Add a second parameter `int primaryField` to `displaySearchResults()`. The primary field is the field getting searched by. Display the primary field as the first element of the output line, then display the rest of the fields.

| `primaryField` | output |
|---|---|
| `0` | title: author: publication year: price |
| `1` | author: title: publication year: price |
| `2` | publication year: title: author: price |
| `3` | price: title: author: publication year |

>Recommendation: Use the provided constants `TITLE_FIELD`, `AUTHOR_FIELD`, `PUBLISHED_YEAR_FIELD`, and `PRICE_FIELD` to help you with this challenge.

**Challenge: 2**

Sort the output of the challenge `displaySearchResults()` method by the primary field.

Add a *helper* method `sortSearchResults` to sort the list of indexes and call this method before the loop inside of displaySearchResults().
```java
private void sortSearchResults(List<Integer> indexes, int primaryField)
```
> Recommendation: There are many algorithms (or ways) to sort data. One of the most straightforward (but not the fastest) is the **bubble sort**, which gets its name from appearing to *bubble* data to the top.
>
> In a bubble sort, you start at the beginning of the list and compare the first two values. If the 1st sorts after the 2nd, you swap them. Then you move on to compare the 2nd and 3rd, again swapping them if the 2nd sorts after the 3rd. Continue to repeat this until you get to the end of the list. Now the value that sorts last is at the end of the list. Since this only moves one value, you must to go back to the beginning of the list and repeat the process again until you pass through the list without swapping a value. To make things a little more efficient, you can check one less item each time through the list, since the last item looked at is always where it belongs. 
>
>The following animated image walks through the bubble sort process using a list of numbers. 
![Bubble Sort](img/bubble-sort.gif)
>
> There is also a full, text-based, static image of the preceding animated image in the `img` folder. Use this to view the walk-through at your own pace. 