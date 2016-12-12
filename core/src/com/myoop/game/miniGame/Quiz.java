package com.myoop.game.miniGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.myoop.game.sprites.Horse;

/**
 * Created by Champ on 9/12/2559.
 */
public class Quiz {
    private Texture quiz, ans1, ans2, ans3, ans4;
    private int rightChoice=0;

    private final Texture[] quiz1 = {new Texture("fun13.png"), new Texture("fun5.png")};
    private final Texture[] quiz2 = {new Texture("fun15.png"), new Texture("fun3.png")};
    private final Texture[] quiz3 = {new Texture("fun16.png"), new Texture("fun4.png")};
    private final Texture[] quiz4 = {new Texture("fun18.png"), new Texture("fun7.png")};
    private final Texture[] quiz5 = {new Texture("fun12.png"), new Texture("fun1.png")};

    private final Texture[][] allQuiz = {quiz1, quiz2, quiz3, quiz4,quiz5};

    private Vector2 posQuiz = new Vector2(-30, 350);
    private Vector2 posAns1 = new Vector2(-250, 150);
    private Vector2 posAns2 = new Vector2(-100, 150);
    private Vector2 posAns3 = new Vector2(50, 150);
    private Vector2 posAns4 = new Vector2(150, 150);

    public Quiz() {
        generate();
    }

    private void generate() {
        int randQuiz = (int) (Math.random() * 4 + 0);
        int randAns = (int) (Math.random() * 4 + 1);
        System.out.println("\nQuiz : " +randQuiz);
        System.out.println("RIGHT ANS : "+randAns);
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
        int randQuiz = (int) (Math.random() * 4 + 0);
        for (int i = 1; i <= 4; i++) {
            if (i != n) {
                while (allQuiz[randQuiz][1].equals(ans1) || allQuiz[randQuiz][1].equals(ans2) || allQuiz[randQuiz][1].equals(ans3) || allQuiz[randQuiz][1].equals(ans4)) {
                    randQuiz = (int) (Math.random() * 4 + 0);
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

    public void update(float dt){
        posQuiz.add(Horse.MOVEMENT*dt,0);
        posAns1.add(Horse.MOVEMENT*dt,0);
        posAns2.add(Horse.MOVEMENT*dt,0);
        posAns3.add(Horse.MOVEMENT*dt,0);
        posAns4.add(Horse.MOVEMENT*dt,0);
    }

    public boolean choose1(){
        System.out.println("c1");
        if(rightChoice==1){
            return true;
        }return false;
    }

    public boolean choose2(){
        System.out.println("c2");
        if(rightChoice==2){
            return true;
        }return false;
    }

    public boolean choose3(){
        System.out.println("c3");
        if(rightChoice==3){
            return true;
        }return false;
    }

    public boolean choose4(){
        System.out.println("c4");
        if(rightChoice==4){
            return true;
        }return false;
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

    public int getRightChoice() {
        return rightChoice;
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

}
