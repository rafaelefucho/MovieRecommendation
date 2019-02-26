package com.company.View;

import com.company.Controller.MovieController;
import com.company.Model.Movie;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

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
    private JTable jtable_moviesAlreadyRated;
    private Movie currentMovie;


    public MovieView() {
        b_rateByUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                makeSomething();

            }
        });
    }

    private void makeSomething() {

        movieController.addUserRating(currentMovie.getMovieId(),s_sliderRatings.getValue());

        updateTableOfMoviesAlreadyRated();
        currentMovie = movieController.getMovieFromId(movieController.nextRecomendation());
        setMovieCentralPanel();

    }

    private void updateTableOfMoviesAlreadyRated() {

        Map<Integer, Integer> currentMoviesRated = movieController.getCurrentMoviesRated();

        String[] columnNames = {"Movie Name", "Rating"};

        Object[][] data = new Object[currentMoviesRated.size()][2];

        int index = 0;
        for(int movieId:currentMoviesRated.keySet()) {

            data[index][0] = movieController.getMovieFromId(movieId).getTitle();
            data[index][1] = currentMoviesRated.get(movieId);
            index++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        jtable_moviesAlreadyRated.setModel(model);


    }

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

        s_sliderRatings.setMinimum(1);
        s_sliderRatings.setMaximum(10);
        s_sliderRatings.setPaintLabels(true);
        s_sliderRatings.setMajorTickSpacing(1);
        s_sliderRatings.setMinorTickSpacing(1);
        s_sliderRatings.setValue(5);

        currentMovie = movieController.getMovieFromId(1343092);
        setMovieCentralPanel();


    }

    private void setMovieCentralPanel() {

        Movie movieToShow = currentMovie;

        l_movieTitle.setText(movieToShow.getTitle());

        l_posterImage.setIcon( new ImageIcon(movieController.getImageFromMovieCode(movieToShow.getMovieId())));
        l_posterImage.setText("");

    }
}
