package gui;

import javax.swing.*;
import java.awt.*;

public class DepositWithdrawScreen extends JFrame {

    private String username;
    private double balance;
    private JTextField amountField;
    private JLabel balanceLbl;
    private JComboBox<String> typeBox;

    public DepositWithdrawScreen(String username, double balance) {
        this.username = username;
        this.balance = balance;
        setTitle("NexaBank — Deposit / Withdraw");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(520, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(buildUI());
    }

    private JPanel buildUI() {
        JPanel root = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Theme.BG_DARK, 0, getHeight(), new Color(0x0D1F3C));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(28, 32, 10, 32));

        JButton backBtn = Theme.secondaryButton("← Dashboard");
        backBtn.addActionListener(e -> {
            new DashboardScreen(username).setVisible(true);
            dispose();
        });

        JLabel title = new JLabel("Deposit / Withdraw");
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.TEXT_PRIMARY);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(title);

        header.add(backBtn, BorderLayout.WEST);
        header.add(titlePanel, BorderLayout.SOUTH);

        // Balance display
        JPanel balanceBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        balanceBar.setOpaque(false);
        balanceBar.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 32));

        JLabel balancePre = new JLabel("Current Balance: ");
        balancePre.setFont(Theme.FONT_LABEL);
        balancePre.setForeground(Theme.TEXT_MUTED);

        balanceLbl = new JLabel(String.format("₹ %,.2f", balance));
        balanceLbl.setFont(new Font("Georgia", Font.BOLD, 18));
        balanceLbl.setForeground(Theme.ACCENT_GOLD);

        balanceBar.add(balancePre);
        balanceBar.add(balanceLbl);

        // Form
        JPanel cardWrapper = new JPanel(new GridBagLayout());
        cardWrapper.setOpaque(false);
        cardWrapper.setBorder(BorderFactory.createEmptyBorder(10, 32, 10, 32));

        JPanel card = Theme.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        typeBox = new JComboBox<>(new String[]{"Deposit", "Withdraw"});
        typeBox.setFont(Theme.FONT_BODY);
        typeBox.setBackground(Theme.BG_INPUT);
        typeBox.setForeground(Theme.TEXT_PRIMARY);
        typeBox.setBorder(BorderFactory.createLineBorder(Theme.BORDER, 1));
        typeBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        typeBox.setAlignmentX(LEFT_ALIGNMENT);

        amountField = Theme.styledField();
        amountField.setAlignmentX(LEFT_ALIGNMENT);
        amountField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));

        JButton confirmBtn = Theme.primaryButton("Confirm Transaction  →");
        confirmBtn.setAlignmentX(LEFT_ALIGNMENT);
        confirmBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        confirmBtn.addActionListener(e -> handleTransaction());

        // Quick amount buttons
        JPanel quickPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        quickPanel.setOpaque(false);
        quickPanel.setAlignmentX(LEFT_ALIGNMENT);
        quickPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        for (String amt : new String[]{"500", "1000", "5000", "10000"}) {
            JButton qBtn = Theme.secondaryButton("₹" + amt);
            qBtn.setFont(Theme.FONT_SMALL);
            qBtn.addActionListener(e -> amountField.setText(amt));
            quickPanel.add(qBtn);
        }

        card.add(Theme.label("Transaction Type"));
        card.add(Box.createVerticalStrut(6));
        card.add(typeBox);
        card.add(Box.createVerticalStrut(18));
        card.add(Theme.label("Amount (₹)"));
        card.add(Box.createVerticalStrut(6));
        card.add(amountField);
        card.add(Box.createVerticalStrut(12));
        card.add(Theme.label("Quick Select"));
        card.add(Box.createVerticalStrut(8));
        card.add(quickPanel);
        card.add(Box.createVerticalStrut(28));
        card.add(confirmBtn);

        cardWrapper.add(card);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(balanceBar);
        center.add(cardWrapper);

        root.add(header, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        return root;
    }

    private void handleTransaction() {
        String amtText = amountField.getText().trim();
        String type = (String) typeBox.getSelectedItem();

        if (amtText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double amount = Double.parseDouble(amtText);
            if (amount <= 0) throw new NumberFormatException();

            if (type.equals("Withdraw")) {
                if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // TODO: bankService.withdraw(account, amount);
                balance -= amount;
            } else {
                // TODO: bankService.deposit(account, amount);
                balance += amount;
            }

            balanceLbl.setText(String.format("₹ %,.2f", balance));
            amountField.setText("");

            JOptionPane.showMessageDialog(this,
                String.format("✓ %s of ₹%,.2f successful!\nNew Balance: ₹%,.2f", type, amount, balance),
                "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}