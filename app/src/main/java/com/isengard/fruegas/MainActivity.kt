package com.isengard.fruegas

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "FraguasIsengard"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //En esta variable tomamos el editText para ponerle el foco al iniciar la aplicacion
        val textId = findViewById<EditText>(R.id.idUruk)
        textId.requestFocus()

        //Con este evento, detecto cuando el EditText gana o pierde el foco. De esta manera obligo al usuario a poner un nombre del soldado
        textId.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val identificador = textId.text.toString().trim()

                if (identificador.isEmpty()) {
                    textId.error = "El ejército no acepta soldados anónimos"
                }
            }
        }

        //Creo variables para obtener la informacion de todos los elementos del formulario
        val spinnerUnidad = findViewById<Spinner>(R.id.spinnerUnidad)
        val rgArmamento = findViewById<RadioGroup>(R.id.rgArmamento)
        val chkAntorcha = findViewById<CheckBox>(R.id.chkAntorcha)
        val btnEnviar = findViewById<ImageButton>(R.id.btnRegister)

        //Creo el evento, para cuando se pulse el ImageButton tomar los valores de elementos deñ fprmulario y mostarlos en pantalla con el toast
        btnEnviar.setOnClickListener {
            //Tomo los valores del EditText y Spinner
            val identificador = textId.text.toString().trim()
            val unidad = spinnerUnidad.selectedItem.toString().trim()

            //Extraigo el id del radiobutton seleccionado y lo transformo en el texto perteneciente
            val idArmamento = rgArmamento.checkedRadioButtonId
            val armamento = when (idArmamento) {
                R.id.rbArmadura -> "Armadura de hierro"
                R.id.rbEscudo -> "Escudo de Isengard"
                else -> "Sin armamento"
            }

            //Creo una variable vacia que mostrara si el usuario esccoge la antorcha o no (checkbox)
            var complemento =""
            if(chkAntorcha.isChecked){
                complemento= " Lleva la antorcha de polvorin"
            } else{
                complemento = "No lleva ningun complemento"
            }

            //Validacion de que el usuario ha escrito el nombre del soldado en el editText
            if (identificador.isEmpty()) {
                textId.error = "El ejército no acepta soldados anónimos"
                textId.requestFocus()
            } else {
                //Comprobamos si se intenta enviar sin la antorcha marcada
                if (!chkAntorcha.isChecked) {
                    Log.e(TAG, "¡Peligro! Unidad enviada sin fuego")
                }
                // Si hay texto, mostramos en el toast todo lo que ha rellenado el usuario
                Toast.makeText(
                    this,
                    "¡Unidad $identificador, de la unidad: $unidad. Que lleva equipado de armamento: $armamento y $complemento ha sido reclutado!",
                    Toast.LENGTH_LONG
                ).show()
            }

        }


        }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Buscamos el valor de los elementos para leer su estado actual
        val textId = findViewById<EditText>(R.id.idUruk)
        val spinnerUnidad = findViewById<Spinner>(R.id.spinnerUnidad)
        val rgArmamento = findViewById<RadioGroup>(R.id.rgArmamento)
        val chkAntorcha = findViewById<CheckBox>(R.id.chkAntorcha)

        outState.putString("KEY_ID", textId.text.toString())
        outState.putInt("KEY_SPINNER", spinnerUnidad.selectedItemPosition)
        outState.putInt("KEY_ARMAMENTO", rgArmamento.checkedRadioButtonId)
        outState.putBoolean("KEY_ANTORCHA", chkAntorcha.isChecked)
    }

    //Metodos de ciclo de vida
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Las fraguas se encienden")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Los orcos marchan a toda máquina en las líneas de producción")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Saruman detiene la producción temporalmente")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Las puertas de la torre se cierran y la actividad cesa")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Las cavernas de Isengard se desmantelan por completo")
    }

    }



