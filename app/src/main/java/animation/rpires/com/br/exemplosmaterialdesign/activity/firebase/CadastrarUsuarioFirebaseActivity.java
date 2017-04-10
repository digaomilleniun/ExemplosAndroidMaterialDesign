package animation.rpires.com.br.exemplosmaterialdesign.activity.firebase;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import animation.rpires.com.br.exemplosmaterialdesign.R;
import animation.rpires.com.br.exemplosmaterialdesign.domain.User;
import animation.rpires.com.br.exemplosmaterialdesign.CommonActivity;
import animation.rpires.com.br.exemplosmaterialdesign.service.FirebaseService;

public class CadastrarUsuarioFirebaseActivity extends CommonActivity {

    private Toolbar toolbar;
    private AutoCompleteTextView txtNome;
    private AutoCompleteTextView txtEmail;
    private EditText txtPassword;
    private FirebaseService firebaseService;

    @Override
    protected int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    protected String getTitleToolbar() {
        return "Cadastrar Usuário";
    }

    @Override
    protected boolean isHomeAsUpEnabled() {
        return true;
    }

    @Override
    protected boolean isTitleEnabled() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario_firebase);
        setupToolbar();
        setupView();
        firebaseService = new FirebaseService(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseService.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseService.onStop();
    }

    private void setupView() {
        txtNome = (AutoCompleteTextView) findViewById(R.id.name);
        txtEmail = (AutoCompleteTextView) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.sign_up_progress);
    }

    private User newUser() {
        User user = new User();
        user.setName(txtNome.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        return user;
    }

    public void cadastrar(View view) {
        User user = newUser();
        firebaseService.createUser(user);
    }

}
