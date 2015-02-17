package br.com.nglauber.testgroovy
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import br.com.nglauber.testgroovy.activeandroid.Palavra
import br.com.nglauber.testgroovy.otto.OttoActivity
import com.raizlabs.android.dbflow.sql.language.Select
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe

public class MainActivity extends ActionBarActivity {

    List mNomes
    Bus mBus
    String mTexto
    ArrayAdapter mAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate savedInstanceState
        setContentView R.layout.activity_main

        mNomes = []

        mAdapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_1, mNomes)

        ListView listView = findViewById R.id.listView
        listView.setAdapter mAdapter
        listView.setOnItemClickListener({ abslistview, view, position, id ->
            Toast.makeText(this, "Clicou em ${mNomes[position]}", Toast.LENGTH_SHORT).show()
        })

        Button btn = findViewById(R.id.button)
        btn.setOnClickListener({
            EditText txt = findViewById(R.id.textView)

            Palavra palavra = new Palavra(texto : txt.text.toString());
            palavra.save()

            refreshList()
        })

        mBus = application.bus
        mBus.register(this)
    }

    private void refreshList(){
        mNomes = new Select()
                .from(Palavra.class)
                .orderBy("Texto")
                .execute();

        mAdapter.notifyDataSetChanged()
    }

    @Override
    protected void onResume() {
        super.onResume()
        if (mTexto){
            Toast.makeText(this, mTexto, Toast.LENGTH_SHORT).show();
            mTexto = null
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy()
        mBus.unregister(this)
    }

    @Subscribe
    public void chegouEvento(def event) {
        mTexto = "Olá ${event.nome}"
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate R.menu.menu_main, menu
        return true
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        def id = item.itemId

        switch (id) {
            case R.id.action_otto:
                startActivity new Intent(this, OttoActivity.class)
                break
        }

        return super.onOptionsItemSelected(item)
    }
}
