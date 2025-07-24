import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ZiBenZuoJuQi {
    private JFrame frame;
    private JButton startButton;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private Timer timer;
    private int remainingSeconds;
    private Random random = new Random();

    public ZiBenZuoJuQi() {
        // 初始化主窗口
        frame = new JFrame("资本做局器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new BorderLayout());

        // 创建组件
        startButton = new JButton("开始做局");
        statusLabel = new JLabel("点击按钮开始一键资本做局", JLabel.CENTER);
        progressBar = new JProgressBar();
        progressBar.setVisible(false);

        // 添加组件窗口
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(startButton);
        panel.add(statusLabel);
        panel.add(progressBar);

        frame.add(panel, BorderLayout.CENTER);

        // 按钮点击事件
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startZiBenZuoJu();
            }
        });
        frame.setVisible(true);
    }
    public void startZiBenZuoJu() {
        // 禁用按钮，防止重复点击
        startButton.setEnabled(false);

        // 随机生成等待时间
        remainingSeconds = 3 + random.nextInt(8);
        statusLabel.setText("正在做局...");

        // 设置进度条
        progressBar.setVisible(true);
        progressBar.setMaximum(remainingSeconds);
        progressBar.setValue(0);

        // 创建计时器，每秒更新一次
        timer = new Timer(remainingSeconds * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                progressBar.setValue(progressBar.getMaximum() - remainingSeconds);

                if (remainingSeconds <= 0) {
                    timer.stop();
                    statusLabel.setText("被资本做局了，浪费了 " + progressBar.getMaximum() + "秒");
                    startButton.setEnabled(true);
                    progressBar.setVisible(false);
                }
            }
        });
        timer.start();
    }
    public static void main(String[] args) {
        // 使用SwingUtilities确保GUI线程安全
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ZiBenZuoJuQi();
            }
        });
    }
}
