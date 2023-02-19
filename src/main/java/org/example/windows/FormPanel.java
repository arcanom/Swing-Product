package org.example.windows;

import com.example.productapp.dto.request.RequestProductDTO;
import com.example.productapp.dto.response.ResponseProductDTO;
import lombok.Data;
import org.example.service.ProductService;

import javax.swing.*;

@Data
public class FormPanel {

    private JPanel formPanel;
    private JLabel ID;
    private JLabel name;
    private JLabel desciption;
    private JLabel message;
    private JTextField idField;
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField unitPriceField;
    private JTextArea descriptionField;
    private JButton addButton;
    private JButton updateButton;
    private JButton searchButton;
    private JButton monPanierButton;
    private JButton nextButton;
    private JButton lastButton;
    private ProductService _productService;

    public FormPanel() {
        _productService = new ProductService();

        searchButton.addActionListener((e) -> {
            try {
                ResponseProductDTO dto = _productService.findById(Integer.parseInt(idField.getText()));
                nameField.setText(dto.getName());
                quantityField.setText(String.valueOf(dto.getStock()));
                unitPriceField.setText(String.valueOf(dto.getPrice()));
            } catch (Exception ex) {
                message.setText(ex.getMessage());
                resetMessage();
            }
        });

        updateButton.addActionListener((e) -> {
            try {
                ResponseProductDTO dto = _productService.update(Integer.parseInt(idField.getText()), Integer.parseInt(quantityField.getText()));
                message.setText("Product updated");
                resetMessage();
            } catch (Exception ex) {
                message.setText(ex.getMessage());
                resetMessage();
            }
        });

        addButton.addActionListener((e) -> {
            try {
                RequestProductDTO dto = new RequestProductDTO();
                dto.setName(nameField.getText());
                dto.setPrice(Double.parseDouble(unitPriceField.getText()));
                dto.setStock(Integer.parseInt(quantityField.getText()));
                ResponseProductDTO responseDto = _productService.add(dto);
                resetMessage();
            } catch (Exception ex) {
                message.setText(ex.getMessage());
                resetMessage();
            }
        });
    }

    private void resetMessage() {
        Timer timer = new Timer(3000, (ev) -> message.setText(""));
        timer.start();
    }

}
