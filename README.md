# Simple Journal

## Overview
Simple Journal is an intuitive and simple application designed to help you keep track of your daily thoughts and experiences. With features like creating new journal entries, editing, deleting, and searching through your journals, you can easily manage and reflect on your day...or rant all you want.

## Features
- **Create New Journal Entries:** Easily create and save your thoughts and experiences.
- **Edit Journal Entries:** Update your entries as needed.
- **Delete Journal Entries:** Remove entries you no longer need.
- **Search Journal Entries:** Quickly find specific entries using keywords.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- SQLite JDBC Driver
- SLF4J (Simple Logging Facade for Java) API and Simple bindings

### Project Structure
```sh
simple-journal/
├── src/
│ ├── gui/
│ │ ├── HomePage.java
│ │ └── JournalApp.java
│ ├── model/
│ │ └── JournalEntry.java
│ ├── dao/
│ │ └── JournalDAO.java
│ ├── util/
│ │ └── Database.java
└── lib/
├── sqlite-jdbc-<version>.jar
├── slf4j-api-<version>.jar
└── slf4j-simple-<version>.jar
```

## Installation

### Clone the repository:
```sh
git clone [https://github.com/yourusername/simple-journal.git](https://github.com/Tonyhrule/Simple-Journal.git)
cd simple-journal
```

### Compile the project:
```sh
javac -d bin -cp "lib/sqlite-jdbc-<verison>.jar;lib/slf4j-api-<verison>.jar;lib/slf4j-simple-<verison>.jar" src/*.java
```

### Run the application:
```sh
java -cp "bin;lib/sqlite-jdbc-<verison>.jar;lib/slf4j-api-<verison>.jar;lib/slf4j-simple-<verison>.jar" gui.HomePage
```

## Usage
- **Home Page:** The home page welcomes you to Simple Journal and provides a brief description. Click the "Emanate" button to start using the journal.
- **Journal Page:** Create new journal entries, edit existing ones, delete entries, and search through your journal entries.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
