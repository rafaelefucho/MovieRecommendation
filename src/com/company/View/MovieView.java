package com.company.View;

import com.company.Controller.MovieController;
import com.company.Model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Random;

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
    private JButton b_reset;
    private Movie currentMovie;


    public MovieView() {
        b_rateByUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                makeSomething();

            }
        });
        b_reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                movieController.setUserRatingsToZero();
                currentMovie = movieController.getMovieFromId(993846);

                setMovieCentralPanel();

            }
        });
    }

    private void makeSomething() {


        movieController.addUserRating(currentMovie.getMovieId(), s_sliderRatings.getValue());
        updateTableOfMoviesAlreadyRated();

        if (s_sliderRatings.getValue() > 7) {
            currentMovie = movieController.getMovieFromId(movieController.nextNeighbourh());
        } else {
            currentMovie = movieController.getMovieFromId(movieController.nextRecomendation());
        }

        setMovieCentralPanel();
        System.out.println(currentMovie);

    }

    private void updateTableOfMoviesAlreadyRated() {

        Map<Integer, Integer> currentMoviesRated = movieController.getCurrentMoviesRated();

        String[] columnNames = {"Movie Name", "Rating"};

        Object[][] data = new Object[currentMoviesRated.size()][2];

        int index = 0;
        for (int movieId : currentMoviesRated.keySet()) {

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
        window.setSize(1024, 640);
        window.setLocationRelativeTo(null);

        s_sliderRatings.setMinimum(1);
        s_sliderRatings.setMaximum(10);
        s_sliderRatings.setPaintLabels(true);
        s_sliderRatings.setMajorTickSpacing(1);
        s_sliderRatings.setMinorTickSpacing(1);
        s_sliderRatings.setValue(5);


        int[] randomMovieToStart = new int[]{993846, 1454468, 770828, 1670345, 816692, 1535109, 2267998, 816711, 1045658, 1343092};
        Random random = new Random();


//        currentMovie = movieController.getMovieFromId(randomMovieToStart[random.nextInt(10)]);

        currentMovie = movieController.getMovieFromId(993846);
        setMovieCentralPanel();


    }

    private void setMovieCentralPanel() {

        Movie movieToShow = currentMovie;

        l_movieTitle.setText(movieToShow.getTitle());

        l_posterImage.setIcon(new ImageIcon(movieController.getImageFromMovieCode(movieToShow.getMovieId())));
        l_posterImage.setText("");

    }
}
