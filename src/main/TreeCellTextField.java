package main;

import javafx.scene.control.*;
import javafx.scene.input.*;


public class TreeCellTextField extends TreeCell<String> {
    private TextField textField;

    /**
     * On editing, create new text field and set it using setGraphic method.
     */
    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    /**
     * On cancel edit, set the original content back
     */
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
            return;
        }

        if (!isEditing()) {
            setText(getString());
            setGraphic(getTreeItem().getGraphic());
            return;
        }

        if (textField != null) {
            textField.setText(getString());
        }
        setText(null);
        setGraphic(textField);

    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}

