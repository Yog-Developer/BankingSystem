package gui;

import javax.swing.*;
import java.awt.*;

public class Theme {
    // Core palette
    public static final Color BG_DARK       = new Color(0x0A1628);
    public static final Color BG_CARD       = new Color(0x112240);
    public static final Color BG_INPUT      = new Color(0x1A2F50);
    public static final Color ACCENT_BLUE   = new Color(0x1E90FF);
    public static final Color ACCENT_LIGHT  = new Color(0x64B5F6);
    public static final Color ACCENT_GOLD   = new Color(0xFFD700);
    public static final Color TEXT_PRIMARY  = new Color(0xF0F6FF);
    public static final Color TEXT_MUTED    = new Color(0x8AAFC8);
    public static final Color BORDER        = new Color(0x1E3A5F);
    public static final Color SUCCESS       = new Color(0x00E676);
    public static final Color DANGER        = new Color(0xFF5252);

    // Fonts
    public static final Font FONT_TITLE  = new Font("Georgia", Font.BOLD, 28);
    public static final Font FONT_HEADER = new Font("Georgia", Font.BOLD, 18);
    public static final Font FONT_BODY   = new Font("Courier New", Font.PLAIN, 14);
    public static final Font FONT_SMALL  = new Font("Courier New", Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font("Georgia", Font.BOLD, 14);
    public static final Font FONT_LABEL  = new Font("Courier New", Font.BOLD, 13);

    public static void applyGlobalFont() {
        UIManager.put("OptionPane.messageFont", FONT_BODY);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
    }

    public static JButton primaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_BUTTON);
        btn.setBackground(ACCENT_BLUE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(12, 28, 12, 28));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(0x1565C0));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(ACCENT_BLUE);
            }
        });
        return btn;
    }

    public static JButton secondaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_BUTTON);
        btn.setBackground(BG_INPUT);
        btn.setForeground(ACCENT_LIGHT);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_BLUE, 1),
            BorderFactory.createEmptyBorder(11, 27, 11, 27)
        ));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(0x1E3A5F));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(BG_INPUT);
            }
        });
        return btn;
    }

    public static JTextField styledField() {
        JTextField field = new JTextField();
        field.setFont(FONT_BODY);
        field.setBackground(BG_INPUT);
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT_BLUE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        return field;
    }

    public static JPasswordField styledPassword() {
        JPasswordField field = new JPasswordField();
        field.setFont(FONT_BODY);
        field.setBackground(BG_INPUT);
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT_BLUE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        return field;
    }

    public static JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(TEXT_MUTED);
        return lbl;
    }

    public static JPanel card() {
        JPanel panel = new JPanel();
        panel.setBackground(BG_CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(24, 28, 24, 28)
        ));
        return panel;
    }
}