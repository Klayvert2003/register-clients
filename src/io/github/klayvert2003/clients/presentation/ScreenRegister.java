package io.github.klayvert2003.clients.presentation;

import io.github.klayvert2003.clients.domain.Client;
import io.github.klayvert2003.clients.domain.enums.TypeGender;
import io.github.klayvert2003.clients.service.ClientRegister;
import io.github.klayvert2003.clients.service.Register;
import io.github.klayvert2003.clients.utils.ConvertIconToBytesArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class ScreenRegister extends JFrame {

    private JLabel labelName;
    private JLabel labelCpf;
    private JLabel labelGender;
    private JLabel labelPhoto;

    private JTextField fieldName;
    private JTextField fieldCpf;
    private JComboBox<TypeGender> fieldGender;

    private JButton saveButton;
    private JButton selectPhoto;

    public ScreenRegister() {
        this.constructScreen();
    }

    private void constructScreen() {
        this.setSize(600, 500);
        this.setTitle("Cadastro de Cliente");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.addFields();
        this.addButtons();
        this.addPhotoComponent();
    }

    private void addFields() {
        labelName = new JLabel("Nome:");
        labelName.setBounds(20, 20, 200, 20);
        this.getContentPane().add(labelName);

        fieldName = new JTextField();
        fieldName.setBounds(20, 40, 200, 20);
        this.getContentPane().add(fieldName);


        labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(20, 60, 200, 20);
        this.getContentPane().add(labelCpf);

        fieldCpf = new JTextField();
        fieldCpf.setBounds(20, 80, 200, 20);
        this.getContentPane().add(fieldCpf);


        labelGender = new JLabel("Gender:");
        labelGender.setBounds(20, 100, 200, 20);
        this.getContentPane().add(labelGender);

        TypeGender[] typeGender = { null, TypeGender.M, TypeGender.F, TypeGender.O };
        fieldGender = new JComboBox<>(typeGender);
        fieldGender.setBounds(20, 120, 200, 20);
        this.getContentPane().add(fieldGender);
    }

    private void addButtons() {
        saveButton = new JButton("Save");
        saveButton.setBounds(20, 160, 100, 20);

        ActionListener actionSaveButton = this.saveButtonActionListener();
        saveButton.addActionListener(actionSaveButton);

        this.getContentPane().add(saveButton);
    }

    private void addPhotoComponent() {
        ImageIcon imageIcon = getDefaultIcon();

        labelPhoto = new JLabel();
        labelPhoto.setIcon(imageIcon);
        labelPhoto.setBounds(240, 0, 200, 200);

        getContentPane().add(labelPhoto);

        selectPhoto = new JButton("Alterar foto");
        selectPhoto.setBounds(260, 200, 160, 20);
        selectPhoto.addActionListener(buttonSelectPhotoActionListener());
        getContentPane().add(selectPhoto);
    }

    private ImageIcon getDefaultIcon() {
        URL localization = getClass().getResource("semfoto.jpeg");
        return redimensionImage(new ImageIcon(localization));
    }

    private ImageIcon redimensionImage(ImageIcon imageIcon) {
        Image redimensionImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(redimensionImage);
    }

    private ActionListener saveButtonActionListener() {
        return al -> {
            Client client = new Client();
            client.setName(fieldName.getText());
            client.setCpf(fieldCpf.getText());
            client.setGender( (TypeGender) fieldGender.getSelectedItem());
            byte[] photoBytes = ConvertIconToBytesArray.convert(labelPhoto.getIcon());
            client.setPhoto(photoBytes);

            Register<Client> clientRegister = new ClientRegister();
            clientRegister.save(client);
            fieldName.setText("");
            fieldCpf.setText("");
            fieldGender.setSelectedIndex(0);
            labelPhoto.setIcon(getDefaultIcon());
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        };
    }

    private ActionListener buttonSelectPhotoActionListener() {
        return al -> {
            JFileChooser fileChooser = new JFileChooser();
            int selected = fileChooser.showOpenDialog(ScreenRegister.this);

            if (selected == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                labelPhoto.setIcon(redimensionImage(new ImageIcon(selectedFile.getAbsolutePath())));
            }
        };
    }
}
