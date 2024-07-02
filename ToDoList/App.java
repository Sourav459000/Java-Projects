package ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame implements ActionListener, KeyListener {
    private static final String DATA_FILE = "todoListData.ser";
    private Map<String, ArrayList<String>> groupsMap = new HashMap<>();
    private JComboBox<String> groupsComboBox;
    private JTextArea taskArea;
    private JTextField taskField;
    private JButton addButton, deleteButton, updateButton, addGroupButton, deleteGroupButton, updateGroupButton;
    private String selectedGroup = "Default";

    public App() {
        setTitle("To-Do List");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize default group
        groupsMap.put("Default", new ArrayList<>());

        // Groups ComboBox
        groupsComboBox = new JComboBox<>();
        groupsComboBox.addActionListener(this);
        add(groupsComboBox, BorderLayout.NORTH);

        // Task Area
        taskArea = new JTextArea();
        taskArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskArea);
        add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Color.LIGHT_GRAY);

        // Task Field
        taskField = new JTextField();
        taskField.addKeyListener(this);
        inputPanel.add(taskField, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Add Group Button
        addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(this);
        buttonPanel.add(addGroupButton);

        // Update Group Button
        updateGroupButton = new JButton("Update Group");
        updateGroupButton.addActionListener(this);
        buttonPanel.add(updateGroupButton);

        // Delete Group Button
        deleteGroupButton = new JButton("Delete Group");
        deleteGroupButton.addActionListener(this);
        buttonPanel.add(deleteGroupButton);

        // Add Task Button
        addButton = new JButton("Add Task");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        // Delete Task Button
        deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        // Update Task Button
        updateButton = new JButton("Update Task");
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);

        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.SOUTH);

        // Load previously saved data
        loadData();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addGroupButton) {
            addGroup();
        } else if (e.getSource() == updateGroupButton) {
            updateGroup();
        } else if (e.getSource() == deleteGroupButton) {
            deleteGroup();
        } else if (e.getSource() == addButton) {
            addTask();
        } else if (e.getSource() == deleteButton) {
            deleteTask();
        } else if (e.getSource() == updateButton) {
            updateTask();
        } else if (e.getSource() == groupsComboBox) {
            selectedGroup = (String) groupsComboBox.getSelectedItem();
            updateTaskArea();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == taskField && e.getKeyCode() == KeyEvent.VK_ENTER) {
            addTask();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void addGroup() {
        String newGroupName = JOptionPane.showInputDialog(this, "Enter new group name:");
        if (newGroupName != null && !newGroupName.isEmpty() && !groupsMap.containsKey(newGroupName)) {
            groupsComboBox.addItem(newGroupName);
            groupsMap.put(newGroupName, new ArrayList<>());
            saveData();
        } else if (groupsMap.containsKey(newGroupName)) {
            JOptionPane.showMessageDialog(this, "Group already exists", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Group name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateGroup() {
        String oldGroupName = (String) groupsComboBox.getSelectedItem();
        if (oldGroupName != null && !oldGroupName.equals("Default")) {
            String newGroupName = JOptionPane.showInputDialog(this, "Enter new name for the group:");
            if (newGroupName != null && !newGroupName.isEmpty() && !groupsMap.containsKey(newGroupName)) {
                ArrayList<String> tasks = groupsMap.remove(oldGroupName);
                groupsMap.put(newGroupName, tasks);
                groupsComboBox.removeItem(oldGroupName);
                groupsComboBox.addItem(newGroupName);
                groupsComboBox.setSelectedItem(newGroupName);
                selectedGroup = newGroupName;
                updateTaskArea();
                saveData();
            } else if (groupsMap.containsKey(newGroupName)) {
                JOptionPane.showMessageDialog(this, "Group name already exists", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Group name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cannot rename the Default group", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteGroup() {
        String groupToDelete = (String) groupsComboBox.getSelectedItem();
        if (groupToDelete != null && !groupToDelete.equals("Default")) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this group?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                groupsMap.remove(groupToDelete);
                groupsComboBox.removeItem(groupToDelete);
                if (groupsComboBox.getItemCount() > 0) {
                    selectedGroup = (String) groupsComboBox.getSelectedItem();
                } else {
                    selectedGroup = "Default";
                }
                updateTaskArea();
                saveData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cannot delete the Default group", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addTask() {
        String task = taskField.getText();
        if (!task.isEmpty()) {
            ArrayList<String> tasks = groupsMap.get(selectedGroup);
            if (tasks != null) {
                tasks.add(task);
                taskField.setText("");
                updateTaskArea();
                saveData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Task cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        ArrayList<String> tasks = groupsMap.get(selectedGroup);
        if (tasks == null || tasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tasks in the current group", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String taskNumberStr = JOptionPane.showInputDialog(this, "Enter task number to delete:");
        if (taskNumberStr != null && !taskNumberStr.isEmpty()) {
            try {
                int taskNumber = Integer.parseInt(taskNumberStr);
                if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                    tasks.remove(taskNumber - 1);
                    updateTaskArea();
                    saveData();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid task number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTask() {
        ArrayList<String> tasks = groupsMap.get(selectedGroup);
        if (tasks == null || tasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tasks in the current group", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String taskNumberStr = JOptionPane.showInputDialog(this, "Enter task number to update:");
        if (taskNumberStr != null && !taskNumberStr.isEmpty()) {
            try {
                int taskNumber = Integer.parseInt(taskNumberStr);
                if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                    String newTask = JOptionPane.showInputDialog(this, "Enter new task description:");
                    if (newTask != null && !newTask.isEmpty()) {
                        tasks.set(taskNumber - 1, newTask);
                        updateTaskArea();
                        saveData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Task description cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid task number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTaskArea() {
        ArrayList<String> tasks = groupsMap.get(selectedGroup);
        taskArea.setText("");
        if (tasks != null) {
            for (int i = 0; i < tasks.size(); i++) {
                taskArea.append((i + 1) + ". " + tasks.get(i) + "\n");
            }
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(groupsMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                groupsMap = (Map<String, ArrayList<String>>) ois.readObject();
                groupsComboBox.removeAllItems();
                for (String groupName : groupsMap.keySet()) {
                    groupsComboBox.addItem(groupName);
                }
                if (!groupsMap.isEmpty()) {
                    selectedGroup = groupsComboBox.getItemAt(0);
                    updateTaskArea();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}