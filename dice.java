import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class dice {

    //Default dice value is D4
    private static final DiceType DEFAULT_DICE_VALUE = DiceType.D4;
    //Font for the result
    private static final Font RESULT_FONT = new Font("Arial", Font.BOLD, 24);

    private JFrame frame;
    private JLabel resultadoLabel;
    private JLabel numeroLabel;
    private JButton chooseDicesButton;
    private JButton rollButton;
    private JLabel diceText;
    private DiceType diceType;;

    //Dice tyoes
    public enum DiceType {
        D4(4),
        D6(6),
        D8(8),
        D10(10),
        D12(12),
        D20(20),
        D100(100);

        private final int sides;

        DiceType(int sides){
            this.sides = sides;
        }

        public int getSides() {
            return sides;
        }
    }

    //Roll the dice
    private int rollDice(DiceType diceType) {
        return (int) (Math.random() * diceType.getSides()) + 1;
    }

    //Create the GUI
    private void createGUI() {
        frame = new JFrame("Dados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel for the result
        JPanel resultadoPanel = new JPanel();
        resultadoPanel.setLayout(new GridBagLayout());
        resultadoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(resultadoPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        resultadoLabel = new JLabel("Resultado:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        resultadoPanel.add(resultadoLabel, gbc);

        numeroLabel = new JLabel("0");
        numeroLabel.setFont(RESULT_FONT);
        gbc.gridy = 1;
        resultadoPanel.add(numeroLabel, gbc);

        //Panel for the buttons
        JPanel buttonsPanel = new JPanel();
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        chooseDicesButton = new JButton("Elegir Dado");
        chooseDicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDicesMenu();
            }
        });
        buttonsPanel.add(chooseDicesButton);

        diceText = new JLabel("Dado: D" + diceType.getSides());
        buttonsPanel.add(diceText);

        rollButton = new JButton("Tirar");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = rollDice(diceType);
                numeroLabel.setText(String.valueOf(numero));
                if (numero == 1) {
                    numeroLabel.setForeground(Color.RED);;
                }
                else if (numero == diceType.getSides()) {
                    numeroLabel.setForeground(Color.GREEN);
                }
                else {
                    numeroLabel.setForeground(Color.BLACK);
                }
            }
        });
        buttonsPanel.add(rollButton);

        frame.pack();
        frame.setVisible(true);
    }

    //Show the dices menu
    private void showDicesMenu() {
        JPopupMenu chooseDicesMenu = new JPopupMenu();
        for (DiceType type : DiceType.values()) {
            JMenuItem item = new JMenuItem(type.name());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    diceType = DiceType.valueOf(e.getActionCommand());
                    diceText.setText("Dado: D" + diceType.getSides());
                }
            });
            chooseDicesMenu.add(item);
        }
        chooseDicesMenu.show(chooseDicesButton,0, 0);
    }

    public dice() {
        diceType = DEFAULT_DICE_VALUE;
        createGUI();
    }
    public static void main(String[] args) {
        new dice();
    }


}
