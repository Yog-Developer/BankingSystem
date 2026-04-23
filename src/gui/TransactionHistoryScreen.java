package gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TransactionHistoryScreen extends JFrame {

    private String username;

    public TransactionHistoryScreen(String username) {
        this.username = username;
        setTitle("NexaBank — Transaction History");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(720, 580);
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
        header.setBorder(BorderFactory.createEmptyBorder(28, 32, 16, 32));

        JButton backBtn = Theme.secondaryButton("← Dashboard");
        backBtn.addActionListener(e -> {
            new DashboardScreen(username).setVisible(true);
            dispose();
        });

        JLabel title = new JLabel("Transaction History");
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.TEXT_PRIMARY);

        JLabel sub = new JLabel("All transactions for Account: " + username);
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

        // Table
        String[] columns = {"#", "Date & Time", "Type", "Amount (₹)", "Balance After (₹)", "Status"};

        // TODO: Replace dummy data with real data from BankService
        Object[][] data = {
            {"001", "2026-04-23  10:05", "Credit", "10,000.00", "24,500.00", "✓ Success"},
            {"002", "2026-04-22  14:32", "Debit",  " 2,500.00", "14,500.00", "✓ Success"},
            {"003", "2026-04-21  09:15", "Credit",  "5,000.00", "17,000.00", "✓ Success"},
            {"004", "2026-04-20  17:48", "Debit",  " 1,200.00", "12,000.00", "✓ Success"},
            {"005", "2026-04-19  11:22", "Credit", "13,200.00", "13,200.00", "✓ Success"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        table.setFont(Theme.FONT_BODY);
        table.setBackground(Theme.BG_CARD);
        table.setForeground(Theme.TEXT_PRIMARY);
        table.setRowHeight(40);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 4));
        table.setSelectionBackground(new Color(0x1E3A5F));
        table.setSelectionForeground(Theme.TEXT_PRIMARY);
        table.setFillsViewportHeight(true);

        // Header style
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(Theme.FONT_LABEL);
        tableHeader.setBackground(new Color(0x0D47A1));
        tableHeader.setForeground(Theme.TEXT_PRIMARY);
        tableHeader.setBorder(BorderFactory.createEmptyBorder());

        // Color rows by type
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object val, boolean sel, boolean focus, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, focus, row, col);
                setBackground(sel ? new Color(0x1E3A5F) : (row % 2 == 0 ? Theme.BG_CARD : new Color(0x0F1E38)));
                String type = (String) t.getValueAt(row, 2);
                if (col == 2) {
                    setForeground("Credit".equals(type) ? Theme.SUCCESS : Theme.DANGER);
                } else if (col == 3) {
                    setForeground("Credit".equals(type) ? Theme.SUCCESS : Theme.DANGER);
                } else if (col == 5) {
                    setForeground(Theme.SUCCESS);
                } else {
                    setForeground(Theme.TEXT_PRIMARY);
                }
                setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 14));
                return this;
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setOpaque(false);
        scroll.getViewport().setBackground(Theme.BG_CARD);
        scroll.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 32, 30, 32),
            BorderFactory.createLineBorder(Theme.BORDER, 1)
        ));

        root.add(header, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);
        return root;
    }
}
