package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("NexaBank — Secure Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(buildUI());
    }

    private JPanel buildUI() {
        JPanel root = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Theme.BG_DARK, 0, getHeight(), new Color(0x0D1F3C));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        root.setOpaque(false);

        // Top bank branding
        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBorder(BorderFactory.createEmptyBorder(50, 40, 20, 40));

        JLabel logo = new JLabel("◈ NexaBank", SwingConstants.CENTER);
        logo.setFont(new Font("Georgia", Font.BOLD, 32));
        logo.setForeground(Theme.ACCENT_BLUE);
        logo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tagline = new JLabel("Your Trusted Financial Partner", SwingConstants.CENTER);
        tagline.setFont(Theme.FONT_SMALL);
        tagline.setForeground(Theme.TEXT_MUTED);
        tagline.setAlignmentX(CENTER_ALIGNMENT);

        top.add(logo);
        top.add(Box.createVerticalStrut(6));
        top.add(tagline);

        // Center card
        JPanel cardWrapper = new JPanel(new GridBagLayout());
        cardWrapper.setOpaque(false);
        cardWrapper.setBorder(BorderFactory.createEmptyBorder(0, 36, 0, 36));

        JPanel card = Theme.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sign In");
        title.setFont(Theme.FONT_HEADER);
        title.setForeground(Theme.TEXT_PRIMARY);
        title.setAlignmentX(LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Enter your credentials to continue");
        subtitle.setFont(Theme.FONT_SMALL);
        subtitle.setForeground(Theme.TEXT_MUTED);
        subtitle.setAlignmentX(LEFT_ALIGNMENT);

        usernameField = Theme.styledField();
        passwordField = Theme.styledPassword();

        JButton loginBtn = Theme.primaryButton("Sign In  →");
        loginBtn.setAlignmentX(LEFT_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));

        JButton createBtn = Theme.secondaryButton("Create New Account");
        createBtn.setAlignmentX(LEFT_ALIGNMENT);
        createBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));

        loginBtn.addActionListener(e -> handleLogin());
        createBtn.addActionListener(e -> {
            new CreateAccountScreen().setVisible(true);
            dispose();
        });

        card.add(title);
        card.add(Box.createVerticalStrut(4));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(24));
        card.add(Theme.label("Account ID / Username"));
        card.add(Box.createVerticalStrut(6));
        card.add(usernameField);
        card.add(Box.createVerticalStrut(16));
        card.add(Theme.label("Password / PIN"));
        card.add(Box.createVerticalStrut(6));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(24));
        card.add(loginBtn);
        card.add(Box.createVerticalStrut(12));
        card.add(createBtn);

        cardWrapper.add(card);

        // Bottom footer
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        JLabel footerLbl = new JLabel("© 2026 NexaBank. All rights reserved.");
        footerLbl.setFont(Theme.FONT_SMALL);
        footerLbl.setForeground(Theme.TEXT_MUTED);
        footer.add(footerLbl);
        footer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        root.add(top, BorderLayout.NORTH);
        root.add(cardWrapper, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);

        return root;
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Login Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // For demo: any non-empty credentials work
        // TODO: Connect to your BankService for real auth
        new DashboardScreen(username).setVisible(true);
        dispose();
    }
}