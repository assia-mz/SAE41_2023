package com.example.saemorpionsolitaire;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.LinearLayout.LayoutParams;


// TODO : JAVADOC

public class Game extends View {
    private final int BACKGROUND = ContextCompat.getColor(this.getContext(), R.color.white);
    private final int GRID = ContextCompat.getColor(this.getContext(), R.color.gris);
    private final int LINE = ContextCompat.getColor(this.getContext(), R.color.rose);
    private final int CROSS = ContextCompat.getColor(this.getContext(), R.color.black);
    private final static int spaceBetween = 90;

    private int crossesPerLine;

    private Point emplacementCarte;
    private List<Point> dotList;
    private List<Line> lineList;
    private Point center;
    private Paint paint;
    HashMap<Point,List<Line>> hashMapDotLine;
    private Line currentLine;
    private int score;
    private boolean rule2;

    private boolean isTxtFinishWritten = false;

    private boolean isGameRN = false;

    public Game(Context context, AttributeSet attrs){
        super(context, attrs);

        //this.nbCroixParLigne = nbCroixParLigne;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(10);
        this.paint.setTextSize(100);
        this.paint.setFakeBoldText(true);
        this.center = new Point(0,0);
        if (!isGameRN){
            this.emplacementCarte = null;
            this.lineList = new ArrayList<>();
            this.hashMapDotLine = new HashMap<>();
            //this.createCroix(4);
            this.score = 0;
        }
        else{
            this.fillList();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setupButton();
    }

    private void setupButton() {
        Button button = new Button(getContext());
        button.setText("Recentrer");

        // Set layout parameters to align the button to the bottom right corner
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.setMargins(16, 16, 16, 16); // Add margins if needed

        button.setLayoutParams(params);

        // Add the button to the layout
        ((RelativeLayout) getParent()).addView(button);

        // Set click listener for the button
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event
                //Toast.makeText(getContext(), "On recentre", Toast.LENGTH_SHORT).show();
                recenterMap();
            }
        });
    }



    public void moveMap(Point vecteur){
        this.emplacementCarte.addVecteur(vecteur);
        this.invalidate();
    }

    public void recenterMap(){
        this.emplacementCarte = new Point(this.getWidth()/2+this.center.getX(), this.getHeight()/2+this.center.getY());
        this.invalidate();
    }

    public void setIsGameRN(boolean isGameRN){
        this.isGameRN = isGameRN;
    }

    public boolean getIsGameRN(){
        return this.isGameRN;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public void setPositionMap(Point position){
        this.emplacementCarte = position;
        this.invalidate();
    }

    public Point getPositionMap(){
        return this.emplacementCarte;
    }

    public void setHashMapDotLine(HashMap<Point, List<Line>> hashMapDotLine){
        this.hashMapDotLine = hashMapDotLine;
        this.fillList();
    }

    public void setRule2(boolean rule2){
        this.rule2 = rule2;
    }

    public HashMap<Point, List<Line>> getHashMapDotLine(){
        return this.hashMapDotLine;
    }

    public void traceStartLine(Point start){
        Point beginningC = this.closestDot(start);
        this.currentLine = new Line(beginningC);
    }

    public void traceEndLine(Point end){
        Point endCroisement = this.closestDot(end);
        if (this.currentLine != null){
            this.currentLine.setArrivee(endCroisement);
            Log.v("test tracer line", this.currentLine.toString());
        }
        this.invalidate();
    }

    public void validateLine(){
        Point newDot = LineGestion.isValide(this.currentLine, this.hashMapDotLine, rule2);
        if (newDot != null){
            this.score ++;
            this.lineList.add(this.currentLine);
            this.dotList.add(newDot);
        }
        this.currentLine = null;
        if (EndGame.endGame(this.hashMapDotLine, 4)&&(isTxtFinishWritten==true)){
            Toast.makeText(getContext(), "Aucun mouvement n'est possible.", Toast.LENGTH_SHORT).show();
        } else if (EndGame.endGame(this.hashMapDotLine, 4)){
            Toast.makeText(getContext(), "Aucun mouvement n'est disponible ! Votre score est de " + score + ". Félicitation !", Toast.LENGTH_LONG).show();
            isTxtFinishWritten=true;
        }
        this.invalidate();
    }

    public void cancelLine(){
        this.currentLine = null;
    }

    private Point closestDot(Point point){

        float coordonneeX = point.getX()-this.emplacementCarte.getX();
        float coordonneeY = point.getY()-this.emplacementCarte.getY();

        float xFinal = (float) ((int)coordonneeX / Game.spaceBetween);
        float yFinal = (float) ((int)coordonneeY / Game.spaceBetween);

        float resteX = coordonneeX % Game.spaceBetween;
        float resteY = coordonneeY % Game.spaceBetween;

        if (Math.abs(resteX) > Game.spaceBetween/2) {
            if (resteX > 0){
                xFinal += 1f;
            }
            else{
                xFinal -= 1f;
            }
        }
        if (Math.abs(resteY) > Game.spaceBetween/2) {
            if (resteY > 0){
                yFinal += 1f;
            }
            else{
                yFinal -= 1f;
            }
        }
        return new Point(xFinal, yFinal);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Point screenSize = new Point(canvas.getWidth(), canvas.getHeight());
        if (this.emplacementCarte == null){
            this.recenterMap();
        }

        //setupButton();

        this.paint.setColor(this.BACKGROUND);
        canvas.drawRect(0,0, screenSize.getX(), screenSize.getY(), this.paint);

        this.paint.setColor(this.GRID);
        float x,y;
        for (x=this.emplacementCarte.getX()%Game.spaceBetween; x<=screenSize.getX(); x+=Game.spaceBetween){
            canvas.drawLine(x,0, x, screenSize.getY(), this.paint);
        }
        for (y=this.emplacementCarte.getY()%Game.spaceBetween; y<=screenSize.getY(); y+=Game.spaceBetween){
            canvas.drawLine(0,y, screenSize.getX(), y, this.paint);
        }

        this.paint.setColor(this.LINE);
        for (Line line : this.lineList){
            if (line != null){
                Point start = this.getCoordoneeInScreen(line.getDepart());
                Point end = this.getCoordoneeInScreen(line.getArrivee());
                if (start != null && end != null) {
                    canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), this.paint);
                }
            }
        }
        if (this.currentLine != null){
            Point start = this.currentLine.getDepart();
            Point end = this.currentLine.getArrivee();
            if (start != null && end != null){
                start = this.getCoordoneeInScreen(start);
                end = this.getCoordoneeInScreen(end);
                canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), this.paint);
            }
        }

        this.paint.setColor(this.CROSS);
        for (Point croix : this.dotList){
            Point coordonneeCroix = this.getCoordoneeInScreen(croix);
            canvas.drawLine(coordonneeCroix.getX()-20, coordonneeCroix.getY(), coordonneeCroix.getX()+20, coordonneeCroix.getY(), this.paint);
            canvas.drawLine(coordonneeCroix.getX(), coordonneeCroix.getY()-20, coordonneeCroix.getX(), coordonneeCroix.getY()+20, this.paint);
        }

        // Calculate the width of the score text
        float textWidth = this.paint.measureText(String.valueOf(this.score));

        // Calculate the x-coordinate for centering the score text horizontally
        float xCoordinate = (canvas.getWidth() - textWidth) / 2;

        this.paint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));

        // Draw the score text at the calculated coordinates
        canvas.drawText(String.valueOf(this.score), xCoordinate, 150, this.paint);
    }

    private Point getCoordoneeInScreen(Point point){
        float x = point.getX()*Game.spaceBetween+this.emplacementCarte.getX();
        float y = point.getY()*Game.spaceBetween+this.emplacementCarte.getY();
        return new Point(x, y);
    }

    public void setCrossesPerLine(int crossesPerLine) {
        this.crossesPerLine = crossesPerLine;
        createCroix(crossesPerLine);
    }


    private void createCroix(int nbCroixParLigne){
        if (nbCroixParLigne<2){
            nbCroixParLigne = 2;
        }
        float px, py;
        if (nbCroixParLigne%2 == 0){
            this.center = new Point(Game.spaceBetween/2f, Game.spaceBetween/2f);
            px = 1f-(float)(3*nbCroixParLigne/2);
            py = -(float)(nbCroixParLigne/2);
        }
        else{
            px = 1f-(float)(3*nbCroixParLigne/2);
            py = -(float)(nbCroixParLigne/2);
        }

        this.dotList = new ArrayList<>();
        float x,y1=0, y2=0;
        Point newDot;
        for (x=0;x<=(3*(nbCroixParLigne-1)); x++){
            if (x==0 || x==(3*(nbCroixParLigne-1))){
                for(y2=0; y2<nbCroixParLigne; y2++){
                    this.addDot(x+px,y2+py);
                }
                y2 --;
            }
            else if (x==(nbCroixParLigne-1)){
                for(;y1>-nbCroixParLigne;y1--, y2++){
                    this.addDot(x+px,y1+py);
                    this.addDot(x+px,y2+py);
                }
                y1++;
                y2--;
            }
            else if (x==(nbCroixParLigne-1)*2){
                for(;y1<=0;y1++, y2--){
                    this.addDot(x+px,y1+py);
                    this.addDot(x+px,y2+py);
                }
                y1--;
                y2++;
            }
            else{
                this.addDot(x+px,y1+py);
                this.addDot(x+px,y2+py);
            }
        }
        for (Point croix : this.dotList){
            Log.v("test croix debut", croix.toString());
        }
    }

    private void addDot(float x, float y){
        Point newDot = new Point(x,y);
        this.dotList.add(newDot);
        this.hashMapDotLine.put(newDot, new ArrayList<>());
    }


    private void fillList(){
        this.dotList = new ArrayList<>();
        this.lineList = new ArrayList<>();
        for (Point croix : this.hashMapDotLine.keySet()) {
            List<Line> lineList = this.hashMapDotLine.get(croix);
            this.dotList.add(croix);
            this.ajouterListeLine(lineList);
        }
    }

    private void ajouterListeLine(List<Line> lineList){
        for (Line line : lineList){
            if (!this.lineList.contains(line)){
                this.lineList.add(line);
            }
        }
    }
}
