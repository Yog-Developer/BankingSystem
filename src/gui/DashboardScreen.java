package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardScreen extends JFrame {

    private String username;
    private double balance = 24500.00; // TODO: fetch from BankService

    public DashboardScreen(String username) {
        this.username = username;
        setTitle("NexaBank — Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(buildUI());
    }

    private JPanel buildUI() {
        JPanel root = new JPanel(new BorderLayout(0, 0)) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Theme.BG_DARK, 0, getHeight(), new Color(0x0D1F3C));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // ---- Top Nav ----
        JPanel nav = new JPanel(new BorderLayout());
        nav.setOpaque(false);
        nav.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER),
            BorderFactory.createEmptyBorder(16, 30, 16, 30)
        ));

        JLabel logo = new JLabel("◈ NexaBank");
        logo.setFont(new Font("Georgia", Font.BOLD, 20));
        logo.setForeground(Theme.ACCENT_BLUE);

        JLabel userLbl = new JLabel("Welcome, " + username + "  |");
        userLbl.setFont(Theme.FONT_SMALL);
        userLbl.setForeground(Theme.TEXT_MUTED);

        JButton logoutBtn = Theme.secondaryButton("Logout");
        logoutBtn.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            dispose();
        });

        JPanel navRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        navRight.setOpaque(false);
        navRight.add(userLbl);
        navRight.add(logoutBtn);

        nav.add(logo, BorderLayout.WEST);
        nav.add(navRight, BorderLayout.EAST);

        // ---- Balance Card ----
        JPanel balanceCard = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(0x1565C0), getWidth(), getHeight(), new Color(0x0D47A1));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
            }
        };
        balanceCard.setOpaque(false);
        balanceCard.setLayout(new BoxLayout(balanceCard, BoxLayout.Y_AXIS));
        balanceCard.setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        JLabel balanceLabel = new JLabel("Total Balance");
        balanceLabel.setFont(Theme.FONT_LABEL);
        balanceLabel.setForeground(new Color(0xBBDEFB));
        balanceLabel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel balanceAmt = new JLabel(String.format("₹ %,.2f", balance));
        balanceAmt.setFont(new Font("Georgia", Font.BOLD, 36));
        balanceAmt.setForeground(Color.WHITE);
        balanceAmt.setAlignmentX(LEFT_ALIGNMENT);

        JLabel accType = new JLabel("Savings Account  •  ACC-00" + username.hashCode() % 9000);
        accType.setFont(Theme.FONT_SMALL);
        accType.setForeground(new Color(0x90CAF9));
        accType.setAlignmentX(LEFT_ALIGNMENT);

        balanceCard.add(balanceLabel);
        balanceCard.add(Box.createVerticalStrut(8));
        balanceCard.add(balanceAmt);
        balanceCard.add(Box.createVerticalStrut(6));
        balanceCard.add(accType);

        JPanel balanceWrapper = new JPanel(new BorderLayout());
        balanceWrapper.setOpaque(false);
        balanceWrapper.setBorder(BorderFactory.createEmptyBorder(24, 30, 16, 30));
        balanceWrapper.add(balanceCard);

        // ---- Quick Actions ----
        JPanel actionsPanel = new JPanel(new GridLayout(1, 3, 16, 0));
        actionsPanel.setOpaque(false);
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));

        actionsPanel.add(actionCard("💰", "Deposit / Withdraw", e -> {
            new DepositWithdrawScreen(username, balance).setVisible(true);
            dispose();
        }));
        actionsPanel.add(actionCard("📋", "Transaction History", e -> {
            new TransactionHistoryScreen(username).setVisible(true);
            dispose();
        }));
        actionsPanel.add(actionCard("👤", "My Profile", e ->
            JOptionPane.showMessageDialog(this, "Name: " + username + "\nAccount Type: Savings\nStatus: Active", "Profile", JOptionPane.INFORMATION_MESSAGE)
        ));

        // ---- Stats Row ----
        JPanel statsRow = new JPanel(new GridLayout(1, 3, 16, 0));
        statsRow.setOpaque(false);
        statsRow.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

        statsRow.add(statCard("Total Credits", "₹ 52,000", Theme.SUCCESS));
        statsRow.add(statCard("Total Debits", "₹ 27,500", Theme.DANGER));
        statsRow.add(statCard("This Month", "₹ 4,200", Theme.ACCENT_LIGHT));

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.add(balanceWrapper);
        center.add(actionsPanel);
        center.add(Box.createVerticalStrut(16));
        center.add(statsRow);

        root.add(nav, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        return root;
    }

    private JPanel actionCard(String icon, String label, java.awt.event.ActionListener action) {
        JPanel card = Theme.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconLbl = new JLabel(icon, SwingConstants.CENTER);
        iconLbl.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        iconLbl.setAlignmentX(CENTER_ALIGNMENT);

        JLabel textLbl = new JLabel(label, SwingConstants.CENTER);
        textLbl.setFont(Theme.FONT_LABEL);
        textLbl.setForeground(Theme.TEXT_PRIMARY);
        textLbl.setAlignmentX(CENTER_ALIGNMENT);

        card.add(Box.createVerticalStrut(8));
        card.add(iconLbl);
        card.add(Box.createVerticalStrut(10));
        card.add(textLbl);
        card.add(Box.createVerticalStrut(8));

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) { action.actionPerformed(null); }
            public void mouseEntered(java.awt.event.MouseEvent e) { card.setBackground(new Color(0x1E3A5F)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { card.setBackground(Theme.BG_CARD); }
        });

        return card;
    }

    private JPanel statCard(String label, String value, Color valueColor) {
        JPanel card = Theme.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel lbl = new JLabel(label);
        lbl.setFont(Theme.FONT_SMALL);
        lbl.setForeground(Theme.TEXT_MUTED);
        lbl.setAlignmentX(LEFT_ALIGNMENT);

        JLabel val = new JLabel(value);
        val.setFont(new Font("Georgia", Font.BOLD, 18));
        val.setForeground(valueColor);
        val.setAlignmentX(LEFT_ALIGNMENT);

        card.add(lbl);
        card.add(Box.createVerticalStrut(6));
        card.add(val);

        return card;
    }
}