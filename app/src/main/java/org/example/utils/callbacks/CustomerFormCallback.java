package org.example.utils.callbacks;

import org.example.models.User;

public interface CustomerFormCallback {
    void signIn();

    void signUp();

    void openCustomerPanel(User user);
}
