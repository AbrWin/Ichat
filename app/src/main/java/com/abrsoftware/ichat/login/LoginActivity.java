package com.abrsoftware.ichat.login;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.abrsoftware.ichat.R;
import com.abrsoftware.ichat.contact.viewContact.ViewContactFragment;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class LoginActivity extends AppCompatActivity implements LoginMvp.View {
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1;

    @Nullable
    @BindView(R.id.textTittle)
    public TextView textTitle;

    @Nullable
    @BindView(R.id.login_content)
    public LinearLayout mLoginContent;

    @Nullable
    @BindView(R.id.login_progress)
    public ProgressBar mProgressBar;

    @Nullable
    @BindView(R.id.tv_mail)
    public TextInputEditText inputEmail;

    @Nullable
    @BindView(R.id.tv_password)
    public TextInputEditText inputPassword;

    @Nullable
    @BindView(R.id.til_email_error)
    public TextInputLayout mMailError;

    @Nullable
    @BindView(R.id.til_password_error)
    public TextInputLayout mPasswordError;

    @Nullable
    @BindView(R.id.fragment_container)
    public FrameLayout frameLayout;

    private LoginMvp.Presenter loginPresenter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = getApplicationContext();

        loginPresenter = new LoginPresenterImp(this);
        //Register the event from presenter implementation
        loginPresenter.oncreate();
        //Check if user is authenticated
        loginPresenter.checkForAuthenticatedUser();
        textTitle.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/fjalla_on.otf"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    @Override
    public void showProgressbar(boolean show) {
        textTitle.setText(getString(show ? R.string.init_session : R.string.appTittle));
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginContent.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Optional
    @OnClick(R.id.btn_register)
    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(inputEmail.getText().toString(), inputPassword.getText().toString(), context);

    }

    @Optional
    @OnClick(R.id.btn_login)
    @Override
    public void handleSignIn() {
        loginPresenter.validateLogin(inputEmail.getText().toString(), inputPassword.getText().toString(), context);
    }

    @Override
    public void navigateMainScree(String msg) {
        Log.d("msj", msg);
        sendToHome();
    }

    @Override
    public void setMailError(String error) {
        inputEmail.setText("");
        mMailError.setError(error);
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

    private void customizeSnackBar(String txt){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
    }

    private void sendToHome(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Go to HomeScreen
                //Snackbar.make(mLoginContent, msg, Snackbar.LENGTH_SHORT).show();
                frameLayout.setVisibility(View.VISIBLE);
                ViewContactFragment contactFragment = new ViewContactFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.remove(contactFragment);
                fragmentTransaction.add(R.id.fragment_container, contactFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        },1000);
    }
}
