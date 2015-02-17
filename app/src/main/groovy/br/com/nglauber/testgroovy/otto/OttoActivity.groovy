package br.com.nglauber.testgroovy.otto
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.View
import br.com.nglauber.testgroovy.App
import br.com.nglauber.testgroovy.R

public class OttoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate savedInstanceState
        setContentView R.layout.activity_otto;
    }

    public void sendOttoMessage(View view) {
        App app = getApplication();
        app.bus.post(new Qualquer(nome: "nelson"));
        finish()
    }

    class Qualquer {
        String nome;
    }
}
