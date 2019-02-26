package com.company.View;

import com.company.Controller.MovieController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MovieView {

    MovieController movieController;
    private JPanel p_principalScreen;
    private JPanel p_leftPanel;
    private JPanel p_centerPanel;
    private JPanel p_rightPanel;
    private JLabel l_posterImage;
    private JLabel l_movieTitle;
    private JSlider s_sliderRatings;
    private JButton b_rateByUser;
    private JButton b_createNewUser;

    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    public void initWindow() {

        JFrame window = new JFrame("Movie Recommendation");
        window.setContentPane(p_principalScreen);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setSize(1024,640);
        window.setLocationRelativeTo(null);

        s_sliderRatings.setMinimum(0);
        s_sliderRatings.setMaximum(10);
        s_sliderRatings.setPaintLabels(true);
        s_sliderRatings.setMajorTickSpacing(1);
        s_sliderRatings.setMinorTickSpacing(1);
        s_sliderRatings.setValue(5);

        setMovieCentralPanel(0006414);


    }

    private void setMovieCentralPanel(int i) {



        l_posterImage.setIcon( new ImageIcon(movieController.getImageFromMovieCode(54953)));
        l_posterImage.setText("");

    }
}
