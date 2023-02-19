package org.example;

import org.example.windows.CartPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame cartFrame = new JFrame("Cart");
        cartFrame.setSize(new Dimension(600, 700));
        cartFrame.setContentPane(new CartPanel().getCartPanel());
        cartFrame.setVisible(true);
    }
}