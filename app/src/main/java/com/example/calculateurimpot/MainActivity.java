package com.example.calculateurimpot;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText surfaceInput, piecesInput;
    private CheckBox piscineCheckbox;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison XML <-> Java
        surfaceInput = findViewById(R.id.input_surface);
        piecesInput = findViewById(R.id.input_pieces);
        piscineCheckbox = findViewById(R.id.checkbox_piscine);
        resultView = findViewById(R.id.result);

        // Écouteur du bouton "Calculer"
        Button buttonCalcul = findViewById(R.id.button_calcul);
        buttonCalcul.setOnClickListener(v -> calculer());
    }

    private void calculer() {
        try {
            // Vérification que les champs ne sont pas vides
            if (surfaceInput.getText().toString().isEmpty() ||
                    piecesInput.getText().toString().isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lecture des valeurs saisies
            double surface = Double.parseDouble(surfaceInput.getText().toString());
            int pieces = Integer.parseInt(piecesInput.getText().toString());
            boolean piscine = piscineCheckbox.isChecked();

            // Validation des valeurs positives
            if (surface <= 0 || pieces <= 0) {
                Toast.makeText(this, "Les valeurs doivent être positives", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calcul des impôts selon la formule
            double impotBase = surface * 2;           // 2 DH par m²
            double supplementPieces = pieces * 50;    // 50 DH par pièce
            double supplementPiscine = piscine ? 100 : 0;  // 100 DH si piscine
            double total = impotBase + supplementPieces + supplementPiscine;

            // Affichage du résultat formaté
            String resultat = String.format("Impôt total : %.2f DH\n\nDétail :\n- Surface : %.2f DH\n- Pièces : %.2f DH\n- Piscine : %.2f DH",
                    total, impotBase, supplementPieces, supplementPiscine);

            resultView.setText(resultat);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valeurs saisies invalides", Toast.LENGTH_SHORT).show();
        }
    }
}