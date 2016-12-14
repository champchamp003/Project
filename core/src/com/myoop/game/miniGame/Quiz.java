package com.myoop.game.miniGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.myoop.game.sprites.Horse;

/**
 * Created by Champ on 9/12/2559.
 */
public class Quiz {
    private Texture quiz, ans1, ans2, ans3, ans4;
    private int rightChoice = 0;

    private final Texture correct = new Texture("correct.png");
    private final Texture wrong = new Texture("wrong.png");
    private final Texture empty = new Texture("empty.png");

    private final Texture[] quiz1 = {new Texture("quiz1.png"), new Texture("ans1.png")};
    private final Texture[] quiz2 = {new Texture("quiz2.png"), new Texture("ans2.png")};
    private final Texture[] quiz3 = {new Texture("quiz3.png"), new Texture("ans3.png")};
    private final Texture[] quiz4 = {new Texture("quiz4.png"), new Texture("ans4.png")};
    private final Texture[] quiz5 = {new Texture("quiz5.png"), new Texture("ans5.png")};
    private final Texture[] quiz6 = {new Texture("quiz6.png"), new Texture("ans6.png")};
    private final Texture[] quiz7 = {new Texture("quiz7.png"), new Texture("ans7.png")};
    private final Texture[] quiz8 = {new Texture("quiz8.png"), new Texture("ans8.png")};
    private final Texture[] quiz9 = {new Texture("quiz9.png"), new Texture("ans9.png")};
    private final Texture[] quiz10 = {new Texture("quiz10.png"), new Texture("ans10.png")};
    private final Texture[] quiz11 = {new Texture("quiz11.png"), new Texture("ans11.png")};
    private final Texture[] quiz12 = {new Texture("quiz12.png"), new Texture("ans12.png")};
    private final Texture[] quiz13 = {new Texture("quiz13.png"), new Texture("ans13.png")};
    private final Texture[] quiz14 = {new Texture("quiz14.png"), new Texture("ans14.png")};
    private final Texture[] quiz15 = {new Texture("quiz15.png"), new Texture("ans15.png")};
    private final Texture[] quiz16 = {new Texture("quiz16.png"), new Texture("ans16.png")};
    private final Texture[] quiz17 = {new Texture("quiz17.png"), new Texture("ans17.png")};
    private final Texture[] quiz18 = {new Texture("quiz18.png"), new Texture("ans18.png")};
    private final Texture[] quiz19 = {new Texture("quiz19.png"), new Texture("ans19.png")};
    private final Texture[] quiz20 = {new Texture("quiz20.png"), new Texture("ans20.png")};

    private final Texture[][] allQuiz = {quiz1, quiz2, quiz3, quiz4, quiz5, quiz6, quiz7, quiz8, quiz9, quiz10, quiz11, quiz12, quiz13, quiz14, quiz15, quiz16, quiz17, quiz18, quiz19, quiz20};

    private Vector2 posQuiz = new Vector2(57, 255);
    private Vector2 posAns1 = new Vector2(80, 300);
    private Vector2 posAns2 = new Vector2(162, 300);
    private Vector2 posAns3 = new Vector2(242, 300);
    private Vector2 posAns4 = new Vector2(316, 300);

    public Quiz() {
        generate();
    }

    private void generate() {
        int randQuiz = (int) (Math.random() * 19 + 0);
        int randAns = (int) (Math.random() * 4 + 1);
        System.out.println("\nQuiz : " + randQuiz);
        System.out.println("RIGHT ANS : " + randAns);
        setQuiz(allQuiz[randQuiz][0]);
        setChoice(randAns, allQuiz[randQuiz][1]);


    }

    private void setChoice(int choice, Texture ans) {
        switch (choice) {
            case 1:
                setAns1(ans);
                setOtherChoice(1);
                break;
            case 2:
                setAns2(ans);
                setOtherChoice(2);
                break;
            case 3:
                setAns3(ans);
                setOtherChoice(3);
                break;
            case 4:
                setAns4(ans);
                setOtherChoice(4);
        }

    }

    private void setOtherChoice(int n) {
        setRightChoice(n);
        int randQuiz = (int) (Math.random() * 19 + 0);
        for (int i = 1; i <= 4; i++) {
            if (i != n) {
                while (allQuiz[randQuiz][1].equals(ans1) || allQuiz[randQuiz][1].equals(ans2) || allQuiz[randQuiz][1].equals(ans3) || allQuiz[randQuiz][1].equals(ans4)) {
                    randQuiz = (int) (Math.random() * 19 + 0);
                }
                switch (i) {
                    case 1:
                        setAns1(allQuiz[randQuiz][1]);
                        System.out.println(randQuiz);
                        break;
                    case 2:
                        setAns2(allQuiz[randQuiz][1]);
                        System.out.println(randQuiz);
                        break;
                    case 3:
                        setAns3(allQuiz[randQuiz][1]);
                        System.out.println(randQuiz);
                        break;
                    case 4:
                        setAns4(allQuiz[randQuiz][1]);
                        System.out.println(randQuiz);
                        break;
                }

            }
        }
    }

    public void update(float dt) {
        posQuiz.add(Horse.MOVEMENT * dt, 0);
        posAns1.add(Horse.MOVEMENT * dt, 0);
        posAns2.add(Horse.MOVEMENT * dt, 0);
        posAns3.add(Horse.MOVEMENT * dt, 0);
        posAns4.add(Horse.MOVEMENT * dt, 0);
    }

    public void reposition(float v) {
        posQuiz.x += v;
        posAns1.x += v;
        posAns2.x += v;
        posAns3.x += v;
        posAns4.x += v;
    }

    public boolean choose(int n) {
        System.out.println(n);
        if (rightChoice == n) {
            return true;
        }
        return false;
    }

    public void dispose(int n) {
        quiz = empty;
        if(n==rightChoice){
            quiz = correct;
            posQuiz.set(posQuiz.x+10,posQuiz.y-45);
        }else if(n!=rightChoice&&n!=0&&n!=-1){
            quiz = wrong;
            posQuiz.set(posQuiz.x+10,posQuiz.y-45);
        }
        ans1 = empty;
        ans2 = empty;
        ans3 = empty;
        ans4 = empty;


    }


    public Texture getQuiz() {
        return quiz;
    }

    private void setQuiz(Texture quiz) {
        this.quiz = quiz;
    }

    public Texture getAns1() {
        return ans1;
    }

    public void setAns1(Texture ans1) {
        this.ans1 = ans1;
    }

    public Texture getAns2() {
        return ans2;
    }

    public void setAns2(Texture ans2) {
        this.ans2 = ans2;
    }

    public Texture getAns3() {
        return ans3;
    }

    public void setAns3(Texture ans3) {
        this.ans3 = ans3;
    }

    public Texture getAns4() {
        return ans4;
    }

    public void setAns4(Texture ans4) {
        this.ans4 = ans4;
    }

    public void setRightChoice(int rightChoice) {
        this.rightChoice = rightChoice;
    }

    public Vector2 getPosQuiz() {
        return posQuiz;
    }

    public Vector2 getPosAns1() {
        return posAns1;
    }

    public Vector2 getPosAns2() {
        return posAns2;
    }

    public Vector2 getPosAns3() {
        return posAns3;
    }

    public Vector2 getPosAns4() {
        return posAns4;
    }

    public int getRightChoice() {
        return rightChoice;
    }

}
