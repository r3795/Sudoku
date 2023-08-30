package com.example.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

public class CustomButton extends AppCompatButton {
    int row;
    int col;
    int value;
    TextView textView;
    TableRow tableRow;
    TableRow.LayoutParams layoutParams;
    TableLayout memo;
    View dialogView;
    AlertDialog.Builder builder;
    TextView[] memos;

    public CustomButton(Context context, int row, int col, TableRow tableRow, TableRow.LayoutParams layoutParams , View dialogView, TableLayout memo, AlertDialog.Builder builder, TextView[] memos) {
        super(context);
        textView = new TextView(context);

        this.row = row;
        this.col = col;
        this.tableRow = tableRow;
        this.layoutParams = layoutParams;
        this.memo = memo;
        this.dialogView = dialogView;
        this.builder = builder;
        this.memos = memos;
        layoutParams.setMargins(15,15,15,15);
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundResource(R.drawable.button_selector);
        textView.setClickable(true);
        textView.setPadding(40,55,0,0);
        textView.setTextSize(20);
        tableRow.addView(textView, col);
    }

    public CustomButton(Context context, int row, TableRow tableRow, TableRow.LayoutParams layoutParams) {
        super(context);
        textView = new TextView(context);

        this.row = row;
        layoutParams.setMargins(5,5,5,5);
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundResource(R.drawable.button_selector2);
        textView.setClickable(true);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(1);
        textView.setTextSize(20);
        tableRow.addView(textView);
        if (row == 9) {
            textView.setVisibility(INVISIBLE);
        }
    }

    public void set(int a) {
        if (a != 0) {
            textView.setText(String.valueOf(a));
        }
        else {
            textView.setText("");
        }
    }

    public void setConflict(CustomButton[][] customButton) {
        String origin = textView.getText().toString();
        String compat = null;
        for (int i = row + 1; i < 9; i++) {
            compat = customButton[i][col].textView.getText().toString();
            if (origin.equals(compat) && !origin.equals("")) {
                textView.setBackgroundColor(Color.RED);
            }
        }
        for (int i = 0; i < row; i++) {
            compat = customButton[i][col].textView.getText().toString();
            if (origin.equals(compat) && !origin.equals("")) {
                textView.setBackgroundColor(Color.RED);
            }
        }
        for (int j = col + 1; j < 9; j++) {
            compat = customButton[row][j].textView.getText().toString();
            if (origin.equals(compat) && !origin.equals("")) {
                textView.setBackgroundColor(Color.RED);
            }
        }
        for (int j = 0; j < col; j++) {
            compat = customButton[row][j].textView.getText().toString();
            if (origin.equals(compat) && !origin.equals("")) {
                textView.setBackgroundColor(Color.RED);
            }
        }
        if (row < 3 && col < 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row >= 3 && row < 6 && col < 3) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row >= 6 && row < 9 && col < 3) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row < 3 && col >= 3 && col < 6) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row >= 3 && row < 6 && col >= 3 && col < 6) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row >= 6 && row < 9 && col >= 3 && col < 6) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row < 3 && col >= 6 && col < 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row >= 3 && row < 6 && col >= 6 && col < 9) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
        if (row >= 6 && row < 9 && col >= 6 && col < 9) {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat) && !origin.equals("")) {
                        textView.setBackgroundColor(Color.RED);
                    }
                }
            }
        }
    }

    public void unsetConflict(CustomButton[][] customButton) {
        String origin = textView.getText().toString();
        String compat = null;
        int count = 0;
        for (int i = row + 1; i < 9; i++) {
            compat = customButton[i][col].textView.getText().toString();
            if (origin.equals(compat)) {
                count++;
            }
        }
        for (int i = 0; i < row; i++) {
            compat = customButton[i][col].textView.getText().toString();
            if (origin.equals(compat)) {
                count++;
            }
        }
        for (int j = col + 1; j < 9; j++) {
            compat = customButton[row][j].textView.getText().toString();
            if (origin.equals(compat)) {
                count++;
            }
        }
        for (int j = 0; j < col; j++) {
            compat = customButton[row][j].textView.getText().toString();
            if (origin.equals(compat)) {
                count++;
            }
        }
        if (row < 3 && col < 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row >= 3 && row < 6 && col < 3) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row >= 6 && row < 9 && col < 3) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row < 3 && col >= 3 && col < 6) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row >= 3 && row < 6 && col >= 3 && col < 6) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row >= 6 && row < 9 && col >= 3 && col < 6) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row < 3 && col >= 6 && col < 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row >= 3 && row < 6 && col >= 6 && col < 9) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (row >= 6 && row < 9 && col >= 6 && col < 9) {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    if (i == row && j == col)
                    {
                        continue;
                    }
                    compat = customButton[i][j].textView.getText().toString();
                    if (origin.equals(compat)) {
                        count++;
                    }
                }
            }
        }
        if (count == 0 || textView.getText().toString().equals("")) {
            textView.setBackgroundResource(R.drawable.button_selector);
        }
    }

    public boolean check_End(CustomButton[][] customButton) {
        int count = 0;
        String origin = textView.getText().toString();
        String compat = null;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!customButton[i][j].textView.getText().toString().equals("")) {
                    count++;
                }
            }
        }
        for (int i = row + 1; i < 9; i++) {
            compat = customButton[i][col].textView.getText().toString();
            if (origin.equals(compat)) {
                count--;
            }
        }
        for (int i = 0; i < row; i++) {
            compat = customButton[i][col].textView.getText().toString();
            if (origin.equals(compat)) {
                count--;
            }
        }
        for (int j = col + 1; j < 9; j++) {
            compat = customButton[row][j].textView.getText().toString();
            if (origin.equals(compat)) {
                count--;
            }
        }
        for (int j = 0; j < col; j++) {
            compat = customButton[row][j].textView.getText().toString();
            if (origin.equals(compat)) {
                count--;
            }
        }
        if (count == 81)
        {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    customButton[i][j].textView.setBackgroundColor(Color.GRAY);
                    customButton[i][j].textView.setEnabled(false);
                }
            }
            return true;
        }
        return false;
    }
    public void replaceView(TableLayout memo) {
        tableRow.removeViewAt(col);
        memo.setLayoutParams(layoutParams);
        memo.setBackgroundResource(R.drawable.button_selector);
        memo.setClickable(true);
        memo.setPadding(23,23,-20,-20);
        tableRow.addView(memo, col);

    }
    public void replaceView2() {
        tableRow.removeViewAt(col);
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundResource(R.drawable.button_selector);
        textView.setClickable(true);
        textView.setPadding(40,55,0,0);
        textView.setTextSize(20);
        tableRow.addView(textView, col);

    }
}
