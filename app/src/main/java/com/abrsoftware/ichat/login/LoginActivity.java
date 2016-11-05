package com.abrsoftware.ichat.login;

import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abrsoftware.ichat.R;

public class LoginActivity extends AppCompatActivity implements LoginMvp.View {

    private Button loginBtn;
    private Button registerBtn;
    private TextView textTitle;
    private LinearLayout mLoginContent;
    private ProgressBar mProgressBar;
    private TextInputEditText inputEmail;
    private TextInputEditText inputPassword;
    private TextInputLayout mMailError;
    private TextInputLayout mPasswordError;
    private LoginMvp.Presenter logiPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textTitle = (TextView) findViewById(R.id.textTittle);
        loginBtn = (Button) findViewById(R.id.btn_login);
        registerBtn = (Button) findViewById(R.id.btn_login);

        mLoginContent = (LinearLayout) findViewById(R.id.login_content);
        mProgressBar = (ProgressBar) findViewById(R.id.login_progress);

        inputEmail = (TextInputEditText) findViewById(R.id.tv_mail);
        inputPassword = (TextInputEditText) findViewById(R.id.tv_password);
        mMailError = (TextInputLayout) findViewById(R.id.til_email_error);
        mPasswordError = (TextInputLayout) findViewById(R.id.til_password_error);

        textTitle.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/fjalla_on.otf"));
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_login));
        setSupportActionBar(toolbar);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableImputs() {
        setInputs(false);
    }

    @Override
    public void showProgressbar(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void handleSignUp() {
        logiPresenter.registerNewUser(inputEmail.getText().toString(), inputPassword.getText().toString());

    }

    @Override
    public void handleSignIn() {
        logiPresenter.validateLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void navigateMainScree() {
        //Go to HomeScreen
    }

    @Override
    public void loginError(String error) {
        inputEmail.setText("");
        mMailError.setError(error);
    }

    @Override
    public void newUserSucces() {
        Snackbar.make(mLoginContent, getString(R.string.register_user_successful), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        inputEmail.setText("");
        mMailError.setError(error);
    }

    private void setInputs(boolean enabled) {
        loginBtn.setEnabled(enabled);
        registerBtn.setEnabled(enabled);
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(true);
    }
}
