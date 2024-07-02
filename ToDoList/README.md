# To-Do List Application

A comprehensive To-Do List application created using Java Swing. This application allows you to manage tasks within groups, update tasks, and save/load tasks from a file. It provides a user-friendly interface for managing tasks and groups effectively.

## Features

- **Task Management**:
  - **Add Task**: Add new tasks to the selected group.
  - **Delete Task**: Remove tasks from the selected group by specifying the task number.
  - **Update Task**: Modify the description of an existing task.
  - **View Tasks**: Display all tasks in the selected group.

- **Group Management**:
  - **Add Group**: Create new groups to categorize tasks.
  - **Delete Group**: Remove existing groups. The default group is preserved if no other groups exist.
  - **Update Group**: Rename existing groups.

- **Persistent Storage**:
  - **Save Data**: Automatically save tasks and groups to a file.
  - **Load Data**: Restore previously saved tasks and groups upon application startup.

- **User Interface**: A clean and intuitive interface with options for managing tasks and groups, and functionality for saving/loading data.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Make sure you have JDK 8 or later installed on your machine.

### Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Sourav459000/Java-Projects/ToDoList.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd ToDoList
   ```

3. **Compile the Java Files**:
   ```bash
   javac -d bin src/ToDoList/App.java
   ```

4. **Run the Application**:
   ```bash
   java -cp bin ToDoList.App
   ```

## Usage

1. **Managing Tasks**:
   - **Add Task**: Enter a task description in the text field and click the "Add Task" button or press `Enter` to add it to the selected group.
   - **Delete Task**: Click the "Delete Task" button to remove a task from the selected group by specifying the task number.
   - **Update Task**: Select a task to update its description. Enter the new description when prompted.

2. **Managing Groups**:
   - **Add Group**: Click the "Add Group" button and enter the new group's name.
   - **Delete Group**: Click the "Delete Group" button and enter the name of the group to delete.
   - **Update Group**: Select a group to rename it. Enter the new name when prompted.

3. **Saving and Loading Data**: The application automatically saves your tasks and groups to a file when you close it, and loads them when you restart.

## Contributing

If you would like to contribute to this project, please fork the repository and submit a pull request with your changes.

## MIT License

Copyright (c) 2024 Sourav Toshniwal
    
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
    
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Feel free to adjust the content to fit your project and preferences!