# SOLID Principles

**All coding standards must abide by the _SOLID_ Principles**

* **Single Responsibility**- Classes/Objects must have one sole responsibility to avoid unintentional bugs

* **Open-Close**- Classes can be extended but not modified. Extend classes without changing behaviours. (Given Classes Furniture and Chair, chair should not change its furniture behaviors)

* **Liskov Substitution**- Children can be substituted without altering desirable properties of a program. (Inheritance follows LSP to ensure Parent/Children can be used the same way without “noticeable” errors)

* **Integration Segregation**- Interfaces should be kept separated from entities that do not need them. (Classes execute Only the set of actions required).

* **Dependency Inversion**- Classes that do a specific action should not depend on the “tool” used to perform that action. The interface should be used to connect them. (Interfaces provide abstraction so other tools can be used of the same type)
Different regions use different keyboards but they all type characters on the computer.

# Indentation

Use **4 spaces** for each level of indentation.

In case if you use tab because your text editor can automatically convert tabs into 4 spaces, please ensure that such tabs are converted into **4 spaces**, NOT 8 spaces.

# Line Length

Every line should have no more than 120 characters to avoid horizontal scrolling. All packages and imports will be presented at the beginning lines of the program.


# Naming Conventions

* `Packages` should be named in all lowercase letter so they do not conflict with Class naming conventions.

    ```Java
    int x;  // one per line
    int y;
    fix pls
    ```

* One declaration per line is recommended, since it encourages comments.

    ```Java
    int x;  // one per line
    int y;
    ```

* **Local** `variables` and `methods` should be **camelCase** (starting with lowercase)

    ```Java
    int localVariable = x;
    int anotherVariable = y;
    ```

* **Global** `variables` should be **CamelCase**, with each internal word captialized.
     ```Java
     String GlobalVariable = "Hello";
     String AnotherGlobalVariable = "World!";
     ```

* `Classes` should be named as **nouns** with the first letter of each word capitilized.
    ```Java
    Teacher.java
    Student.java

    public class SunCalculator { ... }
    ```

* `Methods` and `Interface` will be verbs that are **camelCase**.  
    ```Java
    public void someFunction() { ... }
    ```

* `Constants` are all **uppercase**, with **underscores** separating each word.
    ```Java
    static final float CONST_PI = 3.14;
    ```

* DO NOT use `goto` statements at all… PLEASE.

# Comments

If you have to use a number of single comments for a specific function. Try to either write it cleaner or use a comment block instead to describe what is happening.

```Java
public void doGreeting(...) {
    // Write minimally what the code does
    // ex. below will print out a message.
    
    System.out.println("Hello, World!");
}
```

# Braces

* **Open brace "{" appears at the end of the same line as the declaration statement**

* Keep keywords and parenthesis together but space the bracket away from them. 

* The following are acceptable coding conventions for methods and keyword statements

```Java
public void function(...) {
    // code that works
}
```

# Parameters

* Keep the parameters in the same line.

    ```Java
    func( var obj1, var obj2, var obj3 ) {
        // code that works
    }
    ```
* If there are too many parameters (rougly more than 3, or parameters have too long name), add new lines to make it more readable.

    ```Java
    func( var obj1,
          var obj2, 
          var obj3,
          var obj4,
          var obj5 ) {
        
        // code that works
    }      
    ```

    ```Java
    func( var veryLongParameterName1,
          var veryLongParameterName2, 
          var veryLongParameterName3) {
        
        // code that works
    }      
    ```

# Order of variables

Ordering of class contents should be in the order of: 
- Local fields (if any) -> Protected Fields -> Private Fields

```Java
int random = 3;
protected int counter = 0;
private String  name;

//Constructors-> Overloaded Constructors
//Getters
//Setters
//Methods
```

# Methods

When there is only a single line code under if-else statement, do not use curly brackets. Otherwise, please do use curly brackets.

    
```Java
if(condition)
     // do work
```

```Java
if(condition) {
     // do work
     // do more work
     // do more work
     // and so on ...
}
```

There must be a space after giving a comma between two function parameters and so on.

```Java
public void methodName(int num, String name) {} // space between num and name.
```

`Overloaded methods` must appear in sequence and no code shall be between them.

```Java
someConstructor() {}
// no other code
someConstructor(String name) {}
```

# Others

*We should organize our code by component since we are working feature by feature at a time. It is easier to do it this way but we should vote on this for sure!*
