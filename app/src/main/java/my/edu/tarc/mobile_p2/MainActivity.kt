package my.edu.tarc.mobile_p2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener {
            if(editTextWeight.text.isBlank()){
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            if(editTextHeight.text.isBlank()){
                editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()
            var bmi = weight / (height/100).pow(2)

            if(bmi < 18.5){ //underweight
                imageViewBMI.setImageResource(R.drawable.under)
                //Body Mass Index : 18.42
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.underweight))
            } else if(bmi > 25){
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.overweight))
            } else{
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
            }
        }

        buttonReset.setOnClickListener {
            imageViewBMI.setImageResource(R.drawable.empty)
            editTextHeight.text.clear()
            editTextWeight.text.clear()
            textViewBMI.text = String.format("%s", getString(R.string.bmi))
            textViewStatus.text = String.format("%s", getString(R.string.status))
        }
    }
}