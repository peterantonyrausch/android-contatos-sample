package com.supero.rausch.contatos;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.supero.rausch.contatos.db.ContatoRepository;
import com.supero.rausch.contatos.db.SQLiteHelper;
import com.supero.rausch.contatos.models.Contato;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroActivity extends BaseAppCompatActivity {

    private ContatoRepository repository = new ContatoRepository(this);
    private long idEdicao = 0;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bindCancelar();
        bindSalvar();
        bindConsultaCep();
        verificarSeEhEdicao();
    }

    private void verificarSeEhEdicao() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long id = extras.getLong(SQLiteHelper.CAMPO_ID);
            if (id != 0) {
                Contato contato = repository.getById(id);
                if (contato != null) {
                    preencherTelaFromContato(contato);
                }
            }
        }
    }

    private void preencherTelaFromContato(Contato contato) {
        idEdicao = contato.getId();
        setEditTextValue(R.id.inputNome, contato.getNome());
        setEditTextValue(R.id.inputEmail, contato.getEmail());
        setEditTextValue(R.id.inputCep, contato.getCep());
        setEditTextValue(R.id.inputLogradouro, contato.getLogradouro());
        setEditTextValue(R.id.inputNumero, contato.getNumero());
        setEditTextValue(R.id.inputBairro, contato.getBairro());
        setEditTextValue(R.id.inputCidade, contato.getCidade());
        setEditTextValue(R.id.inputUf, contato.getUf());
        setEditTextValue(R.id.inputTelefone, contato.getTelefone());
        setEditTextValue(R.id.inputComplemento, contato.getComplemento());
    }

    private void setEditTextValue(int id, String value) {
        EditText input = (EditText) findViewById(id);
        input.setText(value);
    }

    private void bindConsultaCep() {
        final EditText inputCep = (EditText) findViewById(R.id.inputCep);

        inputCep.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (hasConnectivity()) {
                        CepService task = new CepService();
                        task.execute(inputCep.getText().toString());
                    }
                }
            }
        });
    }

    private void bindCancelar() {
        Button buttonCancelar = (Button) findViewById(R.id.buttonCancelar);

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void bindSalvar() {
        Button buttonSalvar = (Button) findViewById(R.id.buttonSalvar);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = contatoFromTela();
                if (contato.isValid()) {
                    repository.save(contato);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), contato.getValidationErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }

            private Contato contatoFromTela() {
                return new Contato(
                        idEdicao,
                        getEditTextValue(R.id.inputNome),
                        getEditTextValue(R.id.inputEmail),
                        getEditTextValue(R.id.inputCep),
                        getEditTextValue(R.id.inputLogradouro),
                        getEditTextValue(R.id.inputNumero),
                        getEditTextValue(R.id.inputBairro),
                        getEditTextValue(R.id.inputCidade),
                        getEditTextValue(R.id.inputUf),
                        getEditTextValue(R.id.inputTelefone),
                        getEditTextValue(R.id.inputComplemento)
                );
            }

            private String getEditTextValue(int id) {
                EditText input = (EditText) findViewById(id);
                return input.getText().toString();
            }
        });
    }

    public class CepService extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(CadastroActivity.this, "Por favor Aguarde ...",
                    "Buscando endereço ...");
        }

        @Override
        protected void onPostExecute(String jsonText) {
            if (jsonText != null) {
                try {
                    JSONObject jsonResult = new JSONObject(jsonText);
                    completarEndereco(jsonResult);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            load.dismiss();
        }

        private void completarEndereco(JSONObject jsonResult) throws JSONException {
            setEditTextValue(R.id.inputLogradouro, jsonResult.getString("logradouro"));
            setEditTextValue(R.id.inputBairro, jsonResult.getString("bairro"));
            setEditTextValue(R.id.inputCidade, jsonResult.getString("localidade"));
            setEditTextValue(R.id.inputUf, jsonResult.getString("uf"));
        }

        @Override
        protected String doInBackground(String... params) {
            String json = "UNDEFINED";

            try {
                String urlSpec = "https://viacep.com.br/ws/" + params[0] + "/json";
                URL url = new URL(urlSpec);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                json = builder.toString();

                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }
    }
}

