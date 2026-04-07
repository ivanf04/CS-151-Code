This repository contains the JavaFX application for Assignment 5, follow the instructions below to 
set up your development environment and run the project.

## 1. Install Maven

Choose the installation command specific to your operating system to install Apache Maven:

> **Windows** First, ensure [Scoop](https://scoop.sh/) is installed, then run:

`scoop install maven`

> **macOS** Using Homebrew:

`brew install maven`

> **Linux** (Ubuntu/Debian) Using the APT package manager:
```
sudo apt update
sudo apt install maven
```
## 2. Verify Installation
Ensure Maven was installed correctly by checking its version. Open a new terminal window and run:

`mvn -v`

## 3. IDE Setup (VS Code)
If you are using Visual Studio Code, you will need the appropriate extension to manage the project:

1. Open VS Code.
2. Navigate to the Extensions view.
3. Search for and install the "Maven for Java" extension (published by Microsoft).

## 4. Run the Application
Once your environment is set up, navigate to the project folder and launch the JavaFX application:
1. Change into the project directory:
 
`cd Assignment_4_gangOfFour`

2. Run the application using Maven:

`mvn javafx:run`
