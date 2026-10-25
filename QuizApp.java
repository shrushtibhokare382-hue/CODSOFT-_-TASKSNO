
import javax.swing.*;
import java.awt.*;

public class QuizApp extends JFrame {

    String[] questions = {
        "Which keyword creates an object in Java?",
        "Which collection stores unique elements?",
        "Which method is the entry point of Java?"
    };

    String[][] options = {
        {"class", "new", "static", "void"},
        {"ArrayList", "List", "HashSet", "Queue"},
        {"start()", "run()", "main()", "init()"}
    };

    int[] answers = {1, 2, 2};

    int currentQuestion = 0;
    int score = 0;
    int timeLeft = 15;

    JLabel questionLabel;
    JLabel timerLabel;
    JRadioButton[] buttons = new JRadioButton[4];
    ButtonGroup group;
    JButton submitButton;
    Timer timer;

    public QuizApp() {
        setTitle("Java Quiz Application");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        questionLabel = new JLabel();
        timerLabel = new JLabel();
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(questionLabel);
        topPanel.add(timerLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            buttons[i] = new JRadioButton();
            group.add(buttons[i]);
            optionsPanel.add(buttons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit Answer");
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> submitAnswer());

        showQuestion();
        setVisible(true);
    }

    void showQuestion() {
        if (currentQuestion >= questions.length) {
            showResult();
            return;
        }

        group.clearSelection();

        questionLabel.setText(
            "Q" + (currentQuestion + 1) + ": "
            + questions[currentQuestion]
        );

        for (int i = 0; i < 4; i++) {
            buttons[i].setText(options[currentQuestion][i]);
            buttons[i].setEnabled(true);
        }

        submitButton.setEnabled(true);
        timeLeft = 15;
        updateTimerLabel();

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(1000, e -> {
            timeLeft--;
            updateTimerLabel();

            if (timeLeft <= 0) {
                timer.stop();
                submitAnswer();
            }
        });

        timer.start();
    }

    void updateTimerLabel() {
        timerLabel.setText("Time left: " + timeLeft + " seconds");
    }

    void submitAnswer() {
        if (timer != null) {
            timer.stop();
        }

        for (int i = 0; i < 4; i++) {
            if (buttons[i].isSelected() && i == answers[currentQuestion]) {
                score++;
            }
        }

        currentQuestion++;
        showQuestion();
    }

    void showResult() {
        if (timer != null) {
            timer.stop();
        }

        getContentPane().removeAll();

        JLabel result = new JLabel(
            "Quiz completed! Score: " + score
            + " / " + questions.length,
            SwingConstants.CENTER
        );

        result.setFont(new Font("Arial", Font.BOLD, 20));
        add(result, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApp::new);
    }
}
