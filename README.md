# BMR Calculator

This project is a Java-based command-line and file-based tool for calculating BMR/BMI/TDEE from given information. It uses the **Mifflin-St Jeor formula** for its calculations.

-----
### Prerequisites

  * **Java Development Kit (JDK)**
  * **Git**

### Installation

1.  **Clone the Repository**: Open your terminal or command prompt and clone the project using the following command.

    ```bash
    git clone https://github.com/Soretep-Yasshak/BMR-Calculator
    ```

2.  **Navigate to the Project Directory**

    ```bash
    cd BMR
    ```
    
### Optional

3.  **Create a `data` folder**: The application reads from and writes to a `data` folder, so you'll need to create one at the root of the project.

    ```bash
    mkdir data
    ```

4.  **Create input file**: Create a file named `bmr-01.txt` inside the `data` folder. You can populate it with test data, as the program is configured to read from this file.

-----

## How to Run

1.  **Compile the Code**: Compile the `BMRCalculator.java` file.

    ```bash
    javac src/Calculator/BMRCalculator.java
    ```

2.  **Run the Application**: Execute the compiled class file.

    ```bash
    java Calculator.BMRCalculator
    ```

The application will then prompt you for input directly in the console.

**Note**: If you are using an IDE like IntelliJ or Eclipse, you can simply open the project and run the `main` method within the `BMRCalculator` class.

-----

## Features

  * **Metric Calculations**: Computes BMR, BMI, and TDEE based on a user's age, biological sex, height, and weight.
  * **Input Validation**: Robust validation methods are in place to ensure all user inputs (age, weight, height, and body type) are in a valid format.
  * **Unit Conversion**: Automatically converts height entered in feet and inches into total inches for accurate calculations.
  * **Health Metrics Analysis**: Provides a summary of the user's BMI and a recommendation for their healthy weight range.
  * **Dual I/O**: The program can read user data from the console or from a text file, with results written to a separate output file.
  * **Basic GUI**: A simple **Swing UI** is displayed upon program completion.
  * **Junit tests**

