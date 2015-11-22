package ru.mydroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class FifteenActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "Activity";
    static int moves;
    private Button[][] buttons = new Button[4][4];
    private int[][] array = new int[4][4];
    private Point emptySpace = new Point();
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("Счет: " + moves);

        initArray();
        generateArray();
        paintTable();
        setListenersOnButtons();
    }

    private void setListenersOnButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = buttons[i][j];
                button.setOnClickListener(this);
            }
        }
    }

    //
    private Point getClickedPoint(Button clickedButton) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (clickedButton == buttons[i][j]) {
                    Point point = new Point();
                    point.x = i;
                    point.y = j;
                    return point;
                }
            }
        }
        return null;
    }

    private boolean canMove(Point clicked) {
        if (clicked.equals(emptySpace)) {
            return false;
        }
        if (clicked.x == emptySpace.x) {
            int diff = Math.abs(clicked.y - emptySpace.y);
            if (diff == 1) {
                return true;
            }
        } else if (clicked.y == emptySpace.y) {
            int diff = Math.abs(clicked.x - emptySpace.x);
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }

    private void generateArray() {
        int k = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (k >= 16) {
                    emptySpace.x = i;
                    emptySpace.y = j;
                    array[i][j] = -1;
                } else {
                    array[i][j] = k;
                }
                k++;
            }
        }
    }

    //метод для кнопки RESET
    private void rePaintTable() {
        ArrayList<Integer> randNumb = generateRndArray();
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = buttons[i][j];
                if (button.getVisibility() == View.VISIBLE) {
                    button.setText("" + randNumb.get(k));
                    k++;
                }
            }
        }
    }

    private void paintTable() {
        ArrayList<Integer> randNumb = generateRndArray();

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = buttons[i][j];
                if (array[i][j] > -1) {
                    button.setText("" + randNumb.get(k));
                    k++;
                } else {
                    button.setText(" ");
                    button.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    //генерация решаемого варианта случайной генерации чисел
    private ArrayList<Integer> generateRndArray() {
        ArrayList<Integer> randNumb;
        do {
            randNumb = new ArrayList<>();
            while (randNumb.size() < 15) {
                Integer n = new Random(System.currentTimeMillis()).nextInt(15) + 1;
                if (!randNumb.contains(n)) {
                    randNumb.add(n);
                }
            }
        } while (!hasSolution(randNumb));
        return randNumb;
    }

    private void initArray() {
        buttons[0][0] = (Button) findViewById(R.id.button11);
        buttons[0][1] = (Button) findViewById(R.id.button12);
        buttons[0][2] = (Button) findViewById(R.id.button13);
        buttons[0][3] = (Button) findViewById(R.id.button14);

        buttons[1][0] = (Button) findViewById(R.id.button21);
        buttons[1][1] = (Button) findViewById(R.id.button22);
        buttons[1][2] = (Button) findViewById(R.id.button23);
        buttons[1][3] = (Button) findViewById(R.id.button24);


        buttons[2][0] = (Button) findViewById(R.id.button31);
        buttons[2][1] = (Button) findViewById(R.id.button32);
        buttons[2][2] = (Button) findViewById(R.id.button33);
        buttons[2][3] = (Button) findViewById(R.id.button34);


        buttons[3][0] = (Button) findViewById(R.id.button41);
        buttons[3][1] = (Button) findViewById(R.id.button42);
        buttons[3][2] = (Button) findViewById(R.id.button43);
        buttons[3][3] = (Button) findViewById(R.id.button44);
    }

    @Override
    public void onClick(View myView) {
        Button clickedButton = (Button) myView;
        Point clickedPoint = getClickedPoint(clickedButton);
        if (clickedPoint != null && canMove(clickedPoint)) {
            clickedButton.setVisibility(View.INVISIBLE);
            String numberStr = clickedButton.getText().toString();
            clickedButton.setText(" ");
            Button button = buttons[emptySpace.x][emptySpace.y];
            button.setVisibility(View.VISIBLE);
            button.setText(numberStr);

            emptySpace.x = clickedPoint.x;
            emptySpace.y = clickedPoint.y;
            moves++;
            textView.setText("Счет: " + moves);
            if (isOver(buttons)) {
                Dialogs.showWinDialog(this).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_theme:
                Dialogs.setTheme(this).show();
                break;
            case R.id.menu_reset:
                rePaintTable();
                moves = 0;
                textView.setText("Счет: " + moves);
                break;
            case R.id.menu_about:
                View aboutView = getLayoutInflater().inflate(R.layout.about, null);
                Dialogs.showAboutDialog(this, aboutView).show();
                break;
            case R.id.menu_exit:
                Dialogs.setExitDialog(this).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //проверка на решаемость комбинации
    private boolean hasSolution(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(j) != 0) && (current > list.get(j))) {
                    sum++;
                }
            }
        }
        return sum % 2 == 0;
    }

    //проверка на конец игры
    private boolean isOver(Button[][] buttons) {
        try {
            return (Integer.parseInt(buttons[0][0].getText().toString()) == 1) &&
                    (Integer.parseInt(buttons[0][1].getText().toString()) == 2) &&
                    (Integer.parseInt(buttons[0][2].getText().toString()) == 3) &&
                    (Integer.parseInt(buttons[0][3].getText().toString()) == 4) &&
                    (Integer.parseInt(buttons[1][0].getText().toString()) == 5) &&
                    (Integer.parseInt(buttons[1][1].getText().toString()) == 6) &&
                    (Integer.parseInt(buttons[1][2].getText().toString()) == 7) &&
                    (Integer.parseInt(buttons[1][3].getText().toString()) == 8) &&
                    (Integer.parseInt(buttons[2][0].getText().toString()) == 9) &&
                    (Integer.parseInt(buttons[2][1].getText().toString()) == 10) &&
                    (Integer.parseInt(buttons[2][2].getText().toString()) == 11) &&
                    (Integer.parseInt(buttons[2][3].getText().toString()) == 12) &&
                    (Integer.parseInt(buttons[3][0].getText().toString()) == 13) &&
                    (Integer.parseInt(buttons[3][1].getText().toString()) == 14) &&
                    (Integer.parseInt(buttons[3][2].getText().toString()) == 15);
        } catch (Exception e) {
            return false;
        }
    }
}