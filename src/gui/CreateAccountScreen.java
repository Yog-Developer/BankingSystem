package gui;

import javax.swing.*;
import java.awt.*;

public class CreateAccountScreen extends JFrame {

    private JTextField nameField, emailField, phoneField, initialDepositField;
    private JComboBox<String> accountTypeBox;

    public CreateAccountScreen() {
        setTitle("NexaBank — Create Account");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(520, 680);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(buildUI());
    }

    private JPanel buildUI() {
        JPanel root = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Theme.BG_DARK, getWidth(), getHeight(), new Color(0x0D1F3C));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(30, 36, 10, 36));

        JButton backBtn = Theme.secondaryButton("← Back");
        backBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            dispose();
        });

        JLabel title = new JLabel("Open New Account");
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.TEXT_PRIMARY);

        JLabel sub = new JLabel("Fill in your details to get started");
        sub.setFont(Theme.FONT_SMALL);
        sub.setForeground(Theme.TEXT_MUTED);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(4));
        titlePanel.add(sub);

        header.add(backBtn, BorderLayout.WEST);
        header.add(titlePanel, BorderLayout.SOUTH);

        // Form card
        JPanel cardWrapper = new JPanel(new GridBagLayout());
        cardWrapper.setOpaque(false);
        cardWrapper.setBorder(BorderFactory.createEmptyBorder(10, 36, 10, 36));

        JPanel card = Theme.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        nameField = Theme.styledField();
        emailField = Theme.styledField();
        phoneField = Theme.styledField();
        initialDepositField = Theme.styledField();

        accountTypeBox = new JComboBox<>(new String[]{"Savings Account", "Current Account"});
        accountTypeBox.setFont(Theme.FONT_BODY);
        accountTypeBox.setBackground(Theme.BG_INPUT);
        accountTypeBox.setForeground(Theme.TEXT_PRIMARY);
        accountTypeBox.setBorder(BorderFactory.createLineBorder(Theme.BORDER, 1));
        accountTypeBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));

        JButton createBtn = Theme.primaryButton("Create Account  →");
        createBtn.setAlignmentX(LEFT_ALIGNMENT);
        createBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        createBtn.addActionListener(e -> handleCreate());

        addRow(card, "Full Name", nameField);
        card.add(Box.createVerticalStrut(14));
        addRow(card, "Email Address", emailField);
        card.add(Box.createVerticalStrut(14));
        addRow(card, "Phone Number", phoneField);
        card.add(Box.createVerticalStrut(14));
        addRow(card, "Account Type", accountTypeBox);
        card.add(Box.createVerticalStrut(14));
        addRow(card, "Initial Deposit (₹)", initialDepositField);
        card.add(Box.createVerticalStrut(28));
        card.add(createBtn);

        cardWrapper.add(card);

        root.add(header, BorderLayout.NORTH);
        root.add(cardWrapper, BorderLayout.CENTER);
        return root;
    }

    private void addRow(JPanel card, String labelText, JComponent field) {
        JLabel lbl = Theme.label(labelText);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lbl);
        card.add(Box.createVerticalStrut(6));
        field.setAlignmentX(LEFT_ALIGNMENT);
        if (field instanceof JTextField) {
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        }
        card.add(field);
    }

    private void handleCreate() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String deposit = initialDepositField.getText().trim();
        String type = (String) accountTypeBox.getSelectedItem();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || deposit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(deposit);
            // TODO: Connect to Customer and Account classes here
            // Customer customer = new Customer(name, email, phone);
            // Account account = type.contains("Savings") ? new SavingsAccount(...) : new CurrentAccount(...);

            JOptionPane.showMessageDialog(this,
                "✓ Account created successfully!\nWelcome, " + name + "!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            new LoginScreen().setVisible(true);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid deposit amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
