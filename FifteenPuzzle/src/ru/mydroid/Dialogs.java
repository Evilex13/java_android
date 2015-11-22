package ru.mydroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by a.tsoy on 12.11.2015.
 */
public class Dialogs {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Dialog setTheme(Activity activity) {
        CharSequence[] items = {"Theme15", "Theme14", "Theme13"};

        return new AlertDialog.Builder(activity, R.style.UserAlertDlg)
                .setTitle("Menu")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i) {
                            case 0:
                                Utils.changeToTheme(activity, R.style.Theme15);
                                break;
                            case 1:
                                Utils.changeToTheme(activity, R.style.Theme14);
                                break;
                            case 2:
                                Utils.changeToTheme(activity, R.style.Theme13);
                                break;
                        }
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
    }

    public static Dialog showAboutDialog(Context c, View view){
        return new AlertDialog.Builder(c)
                .setTitle("ABOUT")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
    }

    public static Dialog showHelpDialog(Context c){
        return new AlertDialog.Builder(c)
                .setTitle("NOTICE")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("You'll lose your data if you exit app.")
                .setPositiveButton("ОК!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
    }

    public static Dialog setExitDialog(Activity activity) {
        return new AlertDialog.Builder(activity)
                .setTitle("Exit?")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dialogs.showHelpDialog(activity).show();
                    }
                })
                .create();
    }

    public static Dialog showWinDialog(Activity activity){
        EditText input = new EditText(activity);
        return new AlertDialog.Builder(activity)
                .setTitle("YOU WIN!")
                .setMessage("Input button name")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView = (TextView) activity.findViewById(R.id.textView2);
                        textView.setText(input.getText() + ":" + FifteenActivity.moves);
                    }
                })
                .create();
    }
}
