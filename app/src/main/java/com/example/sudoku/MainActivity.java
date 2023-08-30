package com.example.sudoku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int col;
    int row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long start = System.currentTimeMillis();
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        table.setBackgroundColor(Color.GRAY);
        TableRow[] tableRow = new TableRow[9];

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.MATCH_PARENT, 1.0f);
        CustomButton[][] buttons = new CustomButton[9][9];
        BoardGenerator boardGenerator = new BoardGenerator();

        for (int i = 0; i < 9; i++) {
            tableRow[i] = new TableRow(this);
            tableRow[i].setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            table.addView(tableRow[i]);
            for (int j = 0; j < 9; j++) {
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_memo, null);
                LayoutInflater layoutInflater =
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TableLayout memo = (TableLayout) layoutInflater.inflate(R.layout.layout_memo, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                TextView[] memos = new TextView[9];
                int k = 0;
                for(int z = 0; z < 3; z++) {
                    TableRow tableRows = (TableRow) memo.getChildAt(z);
                    for(int x = 0; x < 3; x++, k++) {
                        memos[k] = (TextView) tableRows.getChildAt(x);
                    }
                }
                buttons[i][j] = new CustomButton(this, i, j, tableRow[i], layoutParams, dialogView, memo, builder, memos);
                int random = (int) (Math.random() * 100) + 1;
                if(random <= 70) {
                    int number = boardGenerator.get(i, j);
                    buttons[i][j].set(number);
                }
            }
        }
        CustomButton[] buttons2 = new CustomButton[12];
        TableLayout table2 = (TableLayout) findViewById(R.id.tableLayout2);
        table2.setLayoutParams(new ConstraintLayout.LayoutParams(1080,650));
        table2.setBackgroundColor(Color.WHITE);
        TableRow[] tableRow2 = new TableRow[4];

        TextView textView = new TextView(this);
        textView.setText("Input Number");
        textView.setTextSize(32);
        textView.setGravity(1);

        table2.addView(textView);

        int count = 0;
        for (int i = 0; i < 4; i++) {
            tableRow2[i] = new TableRow(this);
            tableRow2[i].setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            table2.addView(tableRow2[i]);
            for (int j = 0; j < 3; j++) {
                buttons2[count] = new CustomButton(this, count, tableRow2[i], layoutParams);
                if (count != 10 && count != 11) {
                    buttons2[count].set(count + 1);
                }
                else if (count == 10)
                    buttons2[count].textView.setText("취소");
                else if (count == 11)
                    buttons2[count].textView.setText("삭제");
                count++;
            }
        }
        table2.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp1 = i;
                int tmp2 = j;
                buttons[i][j].textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        col = tmp1;
                        row = tmp2;
                        table2.setVisibility(View.VISIBLE);
                        table.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }
        TableLayout table3 = (TableLayout) findViewById(R.id.tableLayout3);
        table3.setBackgroundColor(Color.WHITE);
        table3.setLayoutParams(new ConstraintLayout.LayoutParams(1080,1080));
        TableRow[] tableRow3 = new TableRow[2];
        tableRow3[0] = new TableRow(this);
        table3.addView(tableRow3[0]);
        TextView textView2 = new TextView(this);
        textView2.setText("Congratulation!!");
        textView2.setTextSize(32);
        textView2.setGravity(1);
        tableRow3[0].addView(textView2);
        tableRow3[1] = new TableRow(this);
        table3.addView(tableRow3[1]);
        TextView textView3 = new TextView(this);
        textView3.setTextSize(32);
        textView3.setGravity(1);
        tableRow3[1].addView(textView3);
        table3.setVisibility(View.INVISIBLE);


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp1 = i;
                int tmp2 = j;
                buttons[i][j].textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        col = tmp1;
                        row = tmp2;
                        View dialogView = buttons[col][row].dialogView;
                        AlertDialog.Builder builder = buttons[col][row].builder;
                        TableLayout memo = buttons[col][row].memo;
                        if (dialogView.getParent() != null)
                            ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                        builder.setView(dialogView);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        ArrayList<Integer> tmp = new ArrayList<>();
                        buttons[col][row].replaceView(memo);
                        ToggleButton toggleButton1 = dialogView.findViewById(R.id.toggleButton);
                        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[0].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[0].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[0].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[0].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton2 = dialogView.findViewById(R.id.toggleButton2);
                        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[1].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[1].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[1].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[1].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton3 = dialogView.findViewById(R.id.toggleButton3);
                        toggleButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[2].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[2].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[2].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[2].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton4 = dialogView.findViewById(R.id.toggleButton4);
                        toggleButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[3].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[3].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[3].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[3].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton5 = dialogView.findViewById(R.id.toggleButton5);
                        toggleButton5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[4].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[4].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[4].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[4].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton6 = dialogView.findViewById(R.id.toggleButton6);
                        toggleButton6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[5].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[5].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[5].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[5].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton7 = dialogView.findViewById(R.id.toggleButton7);
                        toggleButton7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[6].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[6].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[6].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[6].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton8 = dialogView.findViewById(R.id.toggleButton8);
                        toggleButton8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[7].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[7].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[7].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[7].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton9 = dialogView.findViewById(R.id.toggleButton9);
                        toggleButton9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[8].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[8].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[8].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[8].setVisibility(View.INVISIBLE);
                            }
                        });
                        TextView textView_del = dialogView.findViewById(R.id.textView_del);
                        textView_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                toggleButton1.setChecked(false);
                                buttons[col][row].memos[0].setVisibility(View.INVISIBLE);
                                toggleButton2.setChecked(false);
                                buttons[col][row].memos[1].setVisibility(View.INVISIBLE);
                                toggleButton3.setChecked(false);
                                buttons[col][row].memos[2].setVisibility(View.INVISIBLE);
                                toggleButton4.setChecked(false);
                                buttons[col][row].memos[3].setVisibility(View.INVISIBLE);
                                toggleButton5.setChecked(false);
                                buttons[col][row].memos[4].setVisibility(View.INVISIBLE);
                                toggleButton6.setChecked(false);
                                buttons[col][row].memos[5].setVisibility(View.INVISIBLE);
                                toggleButton7.setChecked(false);
                                buttons[col][row].memos[6].setVisibility(View.INVISIBLE);
                                toggleButton8.setChecked(false);
                                buttons[col][row].memos[7].setVisibility(View.INVISIBLE);
                                toggleButton9.setChecked(false);
                                buttons[col][row].memos[8].setVisibility(View.INVISIBLE);
                                alertDialog.dismiss();
                            }
                        });
                        TextView textView_ok = dialogView.findViewById(R.id.textView_ok);
                        textView_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        TextView textView_can = dialogView.findViewById(R.id.textView_can);
                        textView_can.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                for (int i = 0; i < tmp.size(); i++) {
                                    switch (tmp.get(i)) {
                                        case 1:
                                            toggleButton1.setChecked(false);
                                            buttons[col][row].memos[0].setVisibility(View.INVISIBLE);
                                            break;
                                        case 2:
                                            toggleButton2.setChecked(false);
                                            buttons[col][row].memos[1].setVisibility(View.INVISIBLE);
                                            break;
                                        case 3:
                                            toggleButton3.setChecked(false);
                                            buttons[col][row].memos[2].setVisibility(View.INVISIBLE);
                                            break;
                                        case 4:
                                            toggleButton4.setChecked(false);
                                            buttons[col][row].memos[3].setVisibility(View.INVISIBLE);
                                            break;
                                        case 5:
                                            toggleButton5.setChecked(false);
                                            buttons[col][row].memos[4].setVisibility(View.INVISIBLE);
                                            break;
                                        case 6:
                                            toggleButton6.setChecked(false);
                                            buttons[col][row].memos[5].setVisibility(View.INVISIBLE);
                                            break;
                                        case 7:
                                            toggleButton7.setChecked(false);
                                            buttons[col][row].memos[6].setVisibility(View.INVISIBLE);
                                            break;
                                        case 8:
                                            toggleButton8.setChecked(false);
                                            buttons[col][row].memos[7].setVisibility(View.INVISIBLE);
                                            break;
                                        case 9:
                                            toggleButton9.setChecked(false);
                                            buttons[col][row].memos[8].setVisibility(View.INVISIBLE);
                                            break;
                                        default:
                                            break;

                                    }
                                }
                                alertDialog.dismiss();
                            }
                        });
                        return true;
                    }
                });
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp1 = i;
                int tmp2 = j;
                buttons[i][j].memo.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        col = tmp1;
                        row = tmp2;
                        View dialogView = buttons[col][row].dialogView;
                        AlertDialog.Builder builder = buttons[col][row].builder;
                        TableLayout memo = buttons[col][row].memo;
                        if (dialogView.getParent() != null)
                            ((ViewGroup) dialogView.getParent()).removeView(dialogView);
                        builder.setView(dialogView);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        ArrayList<Integer> tmp = new ArrayList<>();
                        buttons[col][row].replaceView(memo);
                        ToggleButton toggleButton1 = dialogView.findViewById(R.id.toggleButton);
                        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[0].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[0].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[0].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[0].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton2 = dialogView.findViewById(R.id.toggleButton2);
                        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[1].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[1].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[1].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[1].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton3 = dialogView.findViewById(R.id.toggleButton3);
                        toggleButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[2].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[2].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[2].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[2].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton4 = dialogView.findViewById(R.id.toggleButton4);
                        toggleButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[3].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[3].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[3].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[3].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton5 = dialogView.findViewById(R.id.toggleButton5);
                        toggleButton5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[4].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[4].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[4].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[4].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton6 = dialogView.findViewById(R.id.toggleButton6);
                        toggleButton6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[5].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[5].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[5].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[5].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton7 = dialogView.findViewById(R.id.toggleButton7);
                        toggleButton7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[6].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[6].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[6].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[6].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton8 = dialogView.findViewById(R.id.toggleButton8);
                        toggleButton8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[7].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[7].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[7].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[7].setVisibility(View.INVISIBLE);
                            }
                        });
                        ToggleButton toggleButton9 = dialogView.findViewById(R.id.toggleButton9);
                        toggleButton9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    buttons[col][row].memos[8].setTextColor(Color.BLUE);
                                    buttons[col][row].memos[8].setVisibility(View.VISIBLE);
                                    tmp.add(Integer.parseInt(buttons[col][row].memos[8].getText().toString()));
                                }
                                else
                                    buttons[col][row].memos[8].setVisibility(View.INVISIBLE);
                            }
                        });
                        TextView textView_del = dialogView.findViewById(R.id.textView_del);
                        textView_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                toggleButton1.setChecked(false);
                                buttons[col][row].memos[0].setVisibility(View.INVISIBLE);
                                toggleButton2.setChecked(false);
                                buttons[col][row].memos[1].setVisibility(View.INVISIBLE);
                                toggleButton3.setChecked(false);
                                buttons[col][row].memos[2].setVisibility(View.INVISIBLE);
                                toggleButton4.setChecked(false);
                                buttons[col][row].memos[3].setVisibility(View.INVISIBLE);
                                toggleButton5.setChecked(false);
                                buttons[col][row].memos[4].setVisibility(View.INVISIBLE);
                                toggleButton6.setChecked(false);
                                buttons[col][row].memos[5].setVisibility(View.INVISIBLE);
                                toggleButton7.setChecked(false);
                                buttons[col][row].memos[6].setVisibility(View.INVISIBLE);
                                toggleButton8.setChecked(false);
                                buttons[col][row].memos[7].setVisibility(View.INVISIBLE);
                                toggleButton9.setChecked(false);
                                buttons[col][row].memos[8].setVisibility(View.INVISIBLE);
                                alertDialog.dismiss();
                            }
                        });
                        TextView textView_ok = dialogView.findViewById(R.id.textView_ok);
                        textView_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        TextView textView_can = dialogView.findViewById(R.id.textView_can);
                        textView_can.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                for (int i = 0; i < tmp.size(); i++) {
                                    switch (tmp.get(i)) {
                                        case 1:
                                            toggleButton1.setChecked(false);
                                            buttons[col][row].memos[0].setVisibility(View.INVISIBLE);
                                            break;
                                        case 2:
                                            toggleButton2.setChecked(false);
                                            buttons[col][row].memos[1].setVisibility(View.INVISIBLE);
                                            break;
                                        case 3:
                                            toggleButton3.setChecked(false);
                                            buttons[col][row].memos[2].setVisibility(View.INVISIBLE);
                                            break;
                                        case 4:
                                            toggleButton4.setChecked(false);
                                            buttons[col][row].memos[3].setVisibility(View.INVISIBLE);
                                            break;
                                        case 5:
                                            toggleButton5.setChecked(false);
                                            buttons[col][row].memos[4].setVisibility(View.INVISIBLE);
                                            break;
                                        case 6:
                                            toggleButton6.setChecked(false);
                                            buttons[col][row].memos[5].setVisibility(View.INVISIBLE);
                                            break;
                                        case 7:
                                            toggleButton7.setChecked(false);
                                            buttons[col][row].memos[6].setVisibility(View.INVISIBLE);
                                            break;
                                        case 8:
                                            toggleButton8.setChecked(false);
                                            buttons[col][row].memos[7].setVisibility(View.INVISIBLE);
                                            break;
                                        case 9:
                                            toggleButton9.setChecked(false);
                                            buttons[col][row].memos[8].setVisibility(View.INVISIBLE);
                                            break;
                                        default:
                                            break;

                                    }
                                }
                                alertDialog.dismiss();
                            }
                        });
                        return true;
                    }
                });
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int tmp1 = i;
                int tmp2 = j;
                buttons[i][j].memo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        col = tmp1;
                        row = tmp2;

                        table2.setVisibility(View.VISIBLE);
                        table.setVisibility(View.INVISIBLE);
                        buttons[col][row].replaceView2();
                        buttons[col][row].set(0);
                    }
                });
            }
        }
        for (int i = 0; i < 12; i++) {
            int tmp3 = i;
            buttons2[i].textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = buttons2[tmp3].textView.getText().toString();
                    if (s.equals("삭제")) {
                        s = "0";
                    }
                    else if (s.equals("취소")) {
                        table2.setVisibility(View.INVISIBLE);
                        table.setVisibility(View.VISIBLE);
                        return;
                    }
                    buttons[col][row].set(Integer.parseInt(s));
                    buttons[col][row].setConflict(buttons);
                    buttons[col][row].unsetConflict(buttons);
                    boolean tf = buttons[col][row].check_End(buttons);
                    if (tf == true) {
                        long end = System.currentTimeMillis();
                        long cal_time = (end - start)/1000;
                        textView3.setText("소요시간 : "+String.valueOf(cal_time)+"초");
                        table3.setVisibility(View.VISIBLE);
                    }
                    table2.setVisibility(View.INVISIBLE);
                    table.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}