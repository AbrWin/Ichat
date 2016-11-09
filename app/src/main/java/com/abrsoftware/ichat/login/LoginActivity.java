package com.abrsoftware.ichat.login;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

import com.abrsoftware.ichat.R;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginMvp.View {
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1;

    @BindView(R.id.btn_login)
    public Button loginBtn;
    @BindView(R.id.btn_register)
    public Button registerBtn;

    @BindView(R.id.textTittle)
    public TextView textTitle;

    @BindView(R.id.login_content)
    public LinearLayout mLoginContent;
    @BindView(R.id.login_progress)
    public ProgressBar mProgressBar;

    @BindView(R.id.tv_mail)
    public TextInputEditText inputEmail;
    @BindView(R.id.tv_password)
    public TextInputEditText inputPassword;

    @BindView(R.id.til_email_error)
    public TextInputLayout mMailError;
    @BindView(R.id.til_password_error)
    public TextInputLayout mPasswordError;

    private LoginMvp.Presenter loginPresenter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = getApplicationContext();
        mMailError = (TextInputLayout) findViewById(R.id.til_email_error);
        mPasswordError = (TextInputLayout) findViewById(R.id.til_password_error);

        loginPresenter = new LoginPresenterImp(this);
        //Register the event from presenter implementation
        loginPresenter.oncreate();
        //Check if user is authenticated
        loginPresenter.checkForAuthenticatedUser();
        textTitle.setText(getString(R.string.appTittle));
        textTitle.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/fjalla_on.otf"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_login));
        setSupportActionBar(toolbar);
    }

    @Override
    public void showProgressbar(boolean show) {
        textTitle.setText(getString(R.string.init_session));
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginContent.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.btn_register)
    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(inputEmail.getText().toString(), inputPassword.getText().toString(), context);

    }

    @OnClick(R.id.btn_login)
    @Override
    public void handleSignIn() {
        loginPresenter.validateLogin(inputEmail.getText().toString(), inputPassword.getText().toString(), context);
    }

    @Override
    public void navigateMainScree() {
        //Go to HomeScreen
    }

    @Override
    public void setMailError(String error) {
        inputEmail.setText("");
    }

    @Override
    public void setPasswordError(String error) {
        mPasswordError.setError(error);
    }

    @Override
    public void newUserSucces() {
        Snackbar.make(mLoginContent, getString(R.string.register_user_successful), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSingInError(String error) {
        inputEmail.setText("");
        inputPassword.setText("");
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSingUpError(String error) {
        inputEmail.setText("");
        inputPassword.setText("");
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorConnection(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGooglePlayServicesError() {
        Toast.makeText(context,
                "Se requiere Google Play Services para usar la app", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showGooglePlayServicesDialog(int statusCode) {
        showPlayServicesErrorDialog(statusCode);
    }

    private void showPlayServicesErrorDialog(int codeError){
        Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(LoginActivity.this, codeError,REQUEST_GOOGLE_PLAY_SERVICES );
        dialog.show();
    }
}
