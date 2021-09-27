package com.helicoptera.cga.viewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import com.helicoptera.cga.viewer.parser.ObjParser
import com.helicoptera.cga.viewer.view.ObjView
import com.helicoptera.cga.viewer.viewer.Viewer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val assets = assets
        val inputStream = assets.open("gourd.obj")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        val source = String(buffer)
        val parser = ObjParser()
        val obj = parser.parseObj(source)

        val objView = findViewById<ObjView>(R.id.obj_view)
        val viewer = Viewer(obj)
        objView.viewer = viewer

        val xSeek = findViewById<AppCompatSeekBar>(R.id.rotation_x)
        val ySeek = findViewById<AppCompatSeekBar>(R.id.rotation_y)
        val zSeek = findViewById<AppCompatSeekBar>(R.id.rotation_z)

        val xSeekValue = findViewById<TextView>(R.id.rotation_x_value)
        val ySeekValue = findViewById<TextView>(R.id.rotation_y_value)
        val zSeekValue = findViewById<TextView>(R.id.rotation_z_value)

        xSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                xSeekValue.text = progress.toString()
                viewer.rx = progress.toFloat()
                objView.invalidate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        ySeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                ySeekValue.text = progress.toString()
                viewer.ry = progress.toFloat()
                objView.invalidate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        zSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                zSeekValue.text = progress.toString()
                viewer.rz = progress.toFloat()
                objView.invalidate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}