package animation.rpires.com.br.exemplosmaterialdesign.activity.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.domain.User;
import animation.rpires.com.br.exemplosmaterialdesign.CommonActivity;
import animation.rpires.com.br.exemplosmaterialdesign.service.FirebaseEmailLoginService;
import animation.rpires.com.br.exemplosmaterialdesign.service.LoginService;

public class EscolhaLoginFireBaseActivity extends CommonActivity {

    private User user;
    private Toolbar toolbar;
//    private ProgressBar progressBar;
    private AutoCompleteTextView txtEmail;
    private EditText txtSenha;
    private Button btnSignIn;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_login_fire_base);
        setupToolbar();
        setupView();
        loginService = new LoginService(this, new FirebaseEmailLoginService(this, myBase, new FirebaseEmailLoginService.UsuarioListner() {
            @Override
            public User getUser() {
                return initUser();
            }
        }));
    }

    @Override
    protected int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    protected String getTitleToolbar() {
        return "Logins com FireBase";
    }

    @Override
    protected boolean isHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected boolean isTitleEnabled() {
        return false;
    }

    private void setupView() {
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        txtEmail = (AutoCompleteTextView) findViewById(R.id.email);
        txtSenha = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.email_sign_in_button);
    }

    public void callReset(View view) {

    }

    public void callSignUp(View view) {
        Intent it = new Intent(this, CadastrarUsuarioFirebaseActivity.class);
        startActivity(it);
    }

    public void signIn(View view) {
//        showProgressDialog();
//        initUser();

        loginService.signIn();
        hideProgressDialog();
    }

    public User initUser() {
        user = new User();
        user.setName(txtEmail.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        user.setPassword(txtSenha.getText().toString());
        return user;
    }
}
