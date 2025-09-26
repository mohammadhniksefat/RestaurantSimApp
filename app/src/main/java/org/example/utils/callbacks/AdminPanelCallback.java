package org.example.utils.callbacks;
import org.example.models.*;


public interface AdminPanelCallback {
    boolean adminPasswordCheck(String password);

    void addNewFood(Food food);
}
