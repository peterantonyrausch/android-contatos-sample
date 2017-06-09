package com.supero.rausch.contatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.supero.rausch.contatos.adapters.ContatoAdapter;
import com.supero.rausch.contatos.db.ContatoRepository;
import com.supero.rausch.contatos.db.SQLiteHelper;
import com.supero.rausch.contatos.models.Contato;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contato> contatos;
    ContatoAdapter adapter;
    int posicao = 0;
    private ContatoRepository repository = new ContatoRepository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        contatos = repository.getAll();
        adapter = new ContatoAdapter(this, contatos);
        ListView listView = (ListView) findViewById(R.id.listContatos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editarContato(position);
            }
        });
        super.onResume();

        
    }

    public void editarContato(int posicao) {
        Contato contato = contatos.get(posicao);
        Intent it = new Intent(this, CadastroActivity.class);
        it.putExtra(SQLiteHelper.CAMPO_ID, contato.getId());
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cadastro) {
            startActivity(new Intent(MainActivity.this, CadastroActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
