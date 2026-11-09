package com.example.simulationtelechargement

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var txtStatus: TextView
    private lateinit var btnDownload: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Initialisation des composants
        progressBar = findViewById(R.id.progressBar)
        txtStatus = findViewById(R.id.txtStatus)
        btnDownload = findViewById(R.id.btnDownload)

        // 2. Initialisation de la ProgressBar
        progressBar.max = 100
        progressBar.progress = 0

        // 3. Action du bouton
        btnDownload.setOnClickListener {

            btnDownload.isEnabled = false

            progressBar.progress = 0

            txtStatus.text =
                "Téléchargement en cours : 0%"

            // 4. Création d'un thread secondaire
            Thread {

                // 5. Boucle de 10 étapes
                for (i in 1..10) {

                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        Thread.currentThread().interrupt()
                        return@Thread
                    }

                    // Calcul du pourcentage
                    val pourcentage = i * 10

                    // 6. Mise à jour de l'interface
                    runOnUiThread {

                        progressBar.progress = pourcentage

                        txtStatus.text =
                            "Téléchargement en cours : $pourcentage%"

                        // 7. Vérification de la fin
                        if (pourcentage == 100) {

                            txtStatus.text =
                                "Téléchargement terminé !"

                            btnDownload.isEnabled = true
                        }
                    }
                }

            }.start()
        }
    }
}
